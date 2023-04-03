package nus.day21_practice_joel.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String company;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String jobTitle;
}
