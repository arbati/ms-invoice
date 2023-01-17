package com.ensa.msinvoice.repositories;

import com.ensa.msinvoice.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface InvoiceRepository extends MongoRepository<Invoice,String> {

    Page<Invoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
