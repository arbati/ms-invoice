package com.ensa.msinvoice.web;

import com.ensa.msinvoice.entities.Invoice;
import com.ensa.msinvoice.services.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("ms-invoice/v1/invoice")
public class InvoiceControllerImpl{

    InvoiceService invoiceService;

    public InvoiceControllerImpl(InvoiceService invoiceService){
        this.invoiceService=invoiceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String id) {
        return ResponseEntity.ok(this.invoiceService.getInvoiceById(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id,invoice));
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable String id) {
      invoiceService.deleteInvoice(id);
    }

    @PostMapping
    ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.addInvoice(invoice));
    }

    @GetMapping("search/{date}")
    public ResponseEntity<Page<Invoice>> search(@PathVariable LocalDate date, int page, int size) {
        return ResponseEntity.ok(invoiceService.searchInvoices(date,page,size));
    }
}
