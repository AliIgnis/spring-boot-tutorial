package com.example.restapi.token;

import com.example.restapi.Customer;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface{

    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

//test
    @Override
    public Map<String, String> generateToken(Customer customer) {
        String jwtToken="";
        //jwtToken = Jwts.builder().setSubject(customer.getFirstName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        JwtBuilder step1 = Jwts.builder().setSubject(customer.getFirstName());
        step1 = step1.setIssuedAt(new Date());
        SignatureAlgorithm test = SignatureAlgorithm.HS256;
        step1 = step1.signWith(SignatureAlgorithm.HS256, "secret");
        jwtToken = step1.compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}