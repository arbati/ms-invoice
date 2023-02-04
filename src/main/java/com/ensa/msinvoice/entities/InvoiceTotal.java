package com.ensa.msinvoice.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceTotal {
    private String id;
    @DateTimeFormat(style = "yyyy-MM-dd")
    private LocalDate invoiceDate;
    private double subtotal;
}
