package com.ensa.msinvoice.services;

import com.ensa.msinvoice.entities.Invoice;
import com.ensa.msinvoice.entities.InvoiceTotal;
import com.ensa.msinvoice.exceptions.DbException;
import com.ensa.msinvoice.exceptions.EntityNotFoundException;
import com.ensa.msinvoice.exceptions.IdAlreadyExistException;
import com.ensa.msinvoice.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;


    @Autowired
    private MongoTemplate reactiveMongoTemplate;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository){
        this.invoiceRepository=invoiceRepository;
    }

    @Override
    public Optional<Invoice> getInvoiceById(String id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()) throw new EntityNotFoundException("Invoice not found");

        return optionalInvoice;
    }

    @Override
    public Page<Invoice> searchInvoices(LocalDate startDate, LocalDate endDate, int page, int size) {

        return invoiceRepository.findByInvoiceDateBetween(startDate, endDate, PageRequest.of(page, size));
    }

    @Override
    public Invoice addInvoice(Invoice invoice) throws DbException {

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoice.getId());

        if(!optionalInvoice.isEmpty()) throw new IdAlreadyExistException("Invoice id already exist!");

        return invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice updateInvoice(String id, Invoice invoice) {
         invoice.setId(id);
         return invoiceRepository.save(invoice);
    }


    @Override
    public List<InvoiceTotal> getTotalInvoiceByDay(){

      return invoiceRepository.findAll().stream().map(invoice -> {
           AtomicReference<Double> subTotal= new AtomicReference<>((double) 0);
           invoice.getProducts().forEach(product -> {
               subTotal.set(subTotal.get()+(product.getPrice() * product.getDepositQuantity()));
           });

           double total = subTotal.get()-((subTotal.get()*invoice.getDiscount())/100);
           return InvoiceTotal.builder().id(invoice.getId()).invoiceDate(invoice.getInvoiceDate()).subtotal(total).build();
       }).collect(Collectors.toList());

    }


    @Override
    public Map<Integer, Double> getTotalInvoiceByMonth(){

       List<InvoiceTotal> list = getTotalInvoiceByDay();

       Map<Integer, Double> result = list.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getInvoiceDate().getMonthValue(),
                        Collectors.summingDouble(item -> item.getSubtotal())
                ));

        return  result;
    }



}
