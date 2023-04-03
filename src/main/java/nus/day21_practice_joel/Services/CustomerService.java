package nus.day21_practice_joel.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.day21_practice_joel.Models.Customer;
import nus.day21_practice_joel.Models.Order;
import nus.day21_practice_joel.Repositories.CustomerRepositories;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepositories customerRepositories;

    public List<Customer> getFindAllCustomers(int limit, int offset){
        return customerRepositories.findAllCustomers(limit, offset);
    }

    public Customer getFindCustomerById(int id){
        return customerRepositories.findCustomerById(id);
    }

    public List<Order> getFindOrdersByCustomerId(int id){
        return customerRepositories.findOrdersByCustomerId(id);
    }
}
