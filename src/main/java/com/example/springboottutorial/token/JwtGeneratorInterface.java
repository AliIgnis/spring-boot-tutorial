package com.example.springboottutorial.token;

import com.example.springboottutorial.Customer;

import java.util.Map;

public interface JwtGeneratorInterface {

    Map<String, String> generateToken(Customer customer);
}