
// The Rest Controller class - ProductServiceController.java
package com.example.app.controller;

import com.example.app.exception.ProductNotfoundException;
import com.example.app.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    // lister tous les produits existante
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    // Creation d'un nouveau Produit
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    // Modification d'un produit existant
    @RequestMapping(value = "/products/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,@RequestBody Product product)
    {
        if(!productRepo.containsKey(id))
            throw new ProductNotfoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id,product);
        return new ResponseEntity<>("Product is updated successfully",HttpStatus.OK);
    }
    // suppression d'un produit existant
    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        if(!productRepo.containsKey(id))
            throw new ProductNotfoundException();
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully",HttpStatus.NO_CONTENT);
    }
}