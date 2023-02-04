package com.ensa.msinvoice.repositories;

import com.ensa.msinvoice.entities.Invoice;
import com.ensa.msinvoice.entities.InvoiceTotal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice,String> {

    Page<Invoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Aggregation(pipeline = {
            "{ $group: { _id: '$_id',  subtotal: { $sum: {'$map': {'input': '$products', 'as': 'product', 'in': {'$multiply': ['$$product.price', '$$product.quantity']}}} } } }"
    })
    List<InvoiceTotal> findTotalInvoiceByDay0();


}
