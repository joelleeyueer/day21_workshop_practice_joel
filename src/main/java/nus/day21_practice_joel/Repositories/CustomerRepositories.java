package nus.day21_practice_joel.Repositories;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.day21_practice_joel.Models.Customer;
import nus.day21_practice_joel.Models.Order;

@Repository
public class CustomerRepositories {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String findAllCustomers_SQL = "SELECT * FROM customers limit ? offset ?";
    final String findCustomerId_SQL = "SELECT * FROM customers WHERE id = ?";
    final String findOrdersForID_SQL = "SELECT o.* FROM orders o JOIN customers c ON o.customer_id = c.id WHERE c.id = ?";
    private final String findOrdersForID2_SQL = "select id, customer_id, order_date, shipped_date, ship_name from orders where customer_id = ?";


    public List<Customer> findAllCustomers(int limit, int offset){

            //         Get a list of all customers
            //   GET /api/customers
            //   Accept: application/json
            // This HTTP endpoint supports the following parameters
            //  offset - return the first result from n records from the first; n is the
            // number given by offset parameter
            //  limit - return the number of records specified by limit
            // The default value for offset is 0 and limit is 5.
        List<Customer> result = new ArrayList<>();

        // SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllCustomers_SQL, limit, offset);

        // while (rs.next()){
        //     Customer incomingCustomer = new Customer();
        //     incomingCustomer.setId(rs.getInt("id"));
        //     incomingCustomer.setCompany(rs.getString("company"));
        //     incomingCustomer.setFirstName(rs.getString("first_name"));
        //     incomingCustomer.setLastName(rs.getString("last_name"));
        //     incomingCustomer.setEmailAddress(rs.getString("email_address"));
        //     incomingCustomer.setJobTitle(rs.getString("job_title"));
        //     result.add(incomingCustomer);
        // }

        // return (Collections.unmodifiableList(result));
        
            result = jdbcTemplate.query(findAllCustomers_SQL, BeanPropertyRowMapper.newInstance(Customer.class), limit, offset);
            return result;

    }

    public Customer findCustomerById(int id){

        // Get the details of a customer with the customer’s id
        //     GET /api/customer/<customer_id>
        //     Accept: application/json
        // Return a 404 and an appropriate error object if the customer does not exist

        try {
            Customer customer = jdbcTemplate.queryForObject(findCustomerId_SQL, BeanPropertyRowMapper.newInstance(Customer.class), id);
            return customer;

        } catch (DataAccessException daex) {
            // throw new DataAccessResourceFailureException("Customer id " + id + " not found!");
            return null;
        }
    }

    public List<Order> findOrdersByCustomerId(int id){
    //     Get all orders for a customer
    //     GET /api/customer/<customer_id>/orders
    //     Accept: application/json
    //     This endpoint returns an array of orders in JSON. If the customer does not have any orders, the endpoint should return an empty array
        final List<Order> orderList = new ArrayList<Order>();

        // try {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findOrdersForID2_SQL, id);

        while (rs.next()){
            Order order = new Order();
            // order.setEmployeeId(rs.getInt("employee_id"));
            order.setCustomerId(rs.getInt("customer_id"));
            LocalDateTime odt1 = (LocalDateTime) rs.getObject("order_date");
            order.setOrderDate(odt1);
            LocalDateTime odt2 = (LocalDateTime) rs.getObject("shipped_date");
            order.setShippedDate(odt2);
            
            orderList.add(order);
        }

        System.out.println("printing orderList " + orderList);
        return Collections.unmodifiableList(orderList);

        // } catch (Exception ex){
        //     System.err.println(ex);
        //     return null;
        // }

    }
    
}
