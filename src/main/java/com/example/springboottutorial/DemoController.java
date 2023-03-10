package com.example.springboottutorial;

import com.example.springboottutorial.token.JwtGeneratorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @Autowired
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/add")
    public String addCustomer(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customerRepository.save(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/login")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {

            if(customer.getFirstName() == null && customer.getLastName() == null && customer.getEmail() == null) {
                return new ResponseEntity<>("Missing Values", HttpStatus.CONFLICT);
            }

            customerRepository.save(customer);
            return new ResponseEntity<>(jwtGenerator.generateToken(customer), HttpStatus.OK);
    }

    @GetMapping("/testToken")
    public ResponseEntity<?> testLogin() {
        Customer customer = customerRepository.findCustomerById(1);
        return new ResponseEntity<>(jwtGenerator.generateToken(customer), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(@RequestParam Integer id) {
        return "Test succesfull";
        //return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }
    @GetMapping("/find/{id}/email")
    public String getCustomerEmailById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id).getEmail();
    }

    @DeleteMapping("/delete")
    public void deleteCustomerById(@RequestParam Integer id) {
        customerRepository.deleteById(id);
    }
}