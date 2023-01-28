package com.ensa.msinvoice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("invoice")
public class Invoice {

    @Id
    private String id;
    private Customer customer;
    private List<Product> products;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate invoiceDate;
    private String paymentType;
    private double discount;

}
