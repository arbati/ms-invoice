package com.ensa.msinvoice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

private long id;
private String firstName;
private String lastName;
private String email;
private String address;
private String city;

}
