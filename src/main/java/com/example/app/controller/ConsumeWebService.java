package com.example.app.controller;

import com.example.app.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;

    // consumer le service liste of products

    @RequestMapping(value = "/template/products")
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange("http://localhost:9090/products", HttpMethod.GET,entity,String.class).getBody();
    }

    // consumer le service de creation d'un nouveau produit
    @RequestMapping(value = "/template/products",method = RequestMethod.POST)
    public String createProducts(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
        return restTemplate.exchange("http://localhost:9090/products",HttpMethod.POST,entity,String.class).getBody();
    }

    // Consumer le service de modification d'un produit existant
    @RequestMapping(value = "/template/products/{id}",method = RequestMethod.PUT)
    public String updateProduct(@PathVariable("id") String id,@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
        return restTemplate.exchange("http://localhost:9090/products/"+id,HttpMethod.PUT,entity,String.class).getBody();
    }

    // Consumer le service de supression d'un produit
    @RequestMapping(value = "/template/products/{id}",method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(headers);
        return restTemplate.exchange("http://localhost:9090/products/"+id,HttpMethod.DELETE,entity,String.class).getBody();
    }






}
