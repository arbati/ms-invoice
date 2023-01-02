package com.ensa.msinvoice.repositories;

import com.ensa.msinvoice.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String> {

    @Query("{'invoice.invoiceDate' : ?0}")
    Page<Invoice> getInvoicesByCriteria(LocalDate invoiceDate, Pageable pageable);

}
