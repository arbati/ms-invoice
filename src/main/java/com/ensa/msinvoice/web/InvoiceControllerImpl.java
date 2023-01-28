package com.ensa.msinvoice.web;

import com.ensa.msinvoice.entities.Invoice;
import com.ensa.msinvoice.services.InvoiceService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("/search")
    public ResponseEntity<Page<Invoice>> search(@RequestParam String startDate, @RequestParam String endDate, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateStart = LocalDate.parse(startDate, formatter);
        LocalDate localDateEnd = LocalDate.parse(endDate, formatter);

        return ResponseEntity.ok(invoiceService.searchInvoices(localDateStart,localDateEnd,page,size));
    }
}
