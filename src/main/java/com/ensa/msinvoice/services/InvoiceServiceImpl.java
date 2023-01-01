package com.ensa.msinvoice.services;

import com.ensa.msinvoice.entities.Invoice;
import com.ensa.msinvoice.exceptions.DbException;
import com.ensa.msinvoice.exceptions.EntityNotFoundException;
import com.ensa.msinvoice.exceptions.IdAlreadyExistException;
import com.ensa.msinvoice.repositories.InvoiceRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.w3c.dom.Entity;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

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
    public Page<Invoice> searchInvoices(LocalDate invoiceDate, int page, int size) {

        return invoiceRepository.getInvoicesByCriteria(invoiceDate, PageRequest.of(page, size));
    }

    @Override
    public Invoice addInvoice(Invoice invoice) throws DbException {
      if(!this.getInvoiceById(invoice.getId()).isEmpty()) throw new IdAlreadyExistException("Invoice id already exist!");
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
}
