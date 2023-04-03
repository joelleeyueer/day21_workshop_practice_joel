package nus.day21_practice_joel.Restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nus.day21_practice_joel.Models.Customer;
import nus.day21_practice_joel.Models.Order;
import nus.day21_practice_joel.Services.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers(@RequestParam(defaultValue = "5", required=false) int limit, @RequestParam(defaultValue = "0", required=false) int offset){
        return customerService.getFindAllCustomers(limit, offset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getOneCustomerById(@PathVariable("id") int id ){
        Customer incomingCustomer = customerService.getFindCustomerById(id);

        if (incomingCustomer == null){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "customer "+ id + " not found");
            }

        return new ResponseEntity<Customer>(incomingCustomer, HttpStatus.OK);
    }

    @GetMapping("/{customer_id}/orders")
    public ResponseEntity<List<Order>> getOrdersForACustomer(@PathVariable("customer_id") int id ){
        List<Order> incomingOrderList = customerService.getFindOrdersByCustomerId(id);

        // if (incomingOrderList == null){
        //     throw new ResponseStatusException(
        //         HttpStatus.NOT_FOUND, "customer "+ id + " not found");
        //     }

        return new ResponseEntity<List<Order>>(incomingOrderList, HttpStatus.OK);
    }


}

 // @GetMapping("/{customerId}")
    // public ResponseEntity<?> getCustomerById(@PathVariable("customerId") String customerId) {
    //     // Look up the customer in the data store
    //     Customer customer = customers.get(customerId);

    //     // If the customer doesn't exist, return a 404 error with an error object
    //     if (customer == null) {
    //         Map<String, String> error = new HashMap<>();
    //         error.put("message", "Customer not found");
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    //     }

    //     // If the customer exists, return the customer data
    //     return ResponseEntity.ok(customer);
    // }
