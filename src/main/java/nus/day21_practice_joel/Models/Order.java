package nus.day21_practice_joel.Models;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    // private int id;
    // private int employeeId;
    private int customerId;
    private LocalDateTime orderDate;
    private LocalDateTime shippedDate;
}
