package com.example.restapi.token;

import com.example.restapi.Customer;

import java.util.Map;

public interface JwtGeneratorInterface {

    Map<String, String> generateToken(Customer customer);
}