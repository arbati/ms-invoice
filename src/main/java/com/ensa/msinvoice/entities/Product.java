package com.ensa.msinvoice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private long id;
    private String designation;
    private Double price;
    private String photo;
    private long depositQuantity;
    private String expiryDate;
    private String description;

}
