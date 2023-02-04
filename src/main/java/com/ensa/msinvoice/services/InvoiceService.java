package com.ensa.msinvoice.services;

import com.ensa.msinvoice.entities.Invoice;
import com.ensa.msinvoice.entities.InvoiceTotal;
import com.ensa.msinvoice.exceptions.IdAlreadyExistException;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface InvoiceService {


    Optional<Invoice> getInvoiceById(String id);
    Page<Invoice> searchInvoices(LocalDate startDate, LocalDate endDate, int page, int size);
    Invoice addInvoice(Invoice invoice) throws IdAlreadyExistException;
    void deleteInvoice(String id);
    Invoice updateInvoice(String id,Invoice invoice);
    public List<InvoiceTotal> getTotalInvoiceByDay();
    public Map<Integer, Double> getTotalInvoiceByMonth();

}
