package com.ensa.msinvoice.web;

import com.ensa.msinvoice.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


public interface InvoiceController {

    @GetMapping("/{id}")
    ResponseEntity<Invoice> getInvoice(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestParam Invoice invoice);

    @DeleteMapping("/{id}")
    void deleteInvoice(@PathVariable String id);

    @PostMapping
    ResponseEntity<Invoice> addInvoice(@RequestParam Invoice invoice);

    @GetMapping("/{date}")
    ResponseEntity<Page<Invoice>> search(@PathVariable LocalDate date, int page, int size);

}
