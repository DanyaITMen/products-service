package com.automarket;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/products")
public class ProductsService {

    private List<Product> products = List.of(
            new Product(1, "BMW I5 F90 2025", 100000.0),
            new Product(2, "KIA SORENTO 2004", 7000.0),
            new Product(3, "RENAULT KANGOO 2003", 2000.0)
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<Product>> getProducts() {
        Map<String, List<Product>> response = new HashMap<>();
        response.put("products", products);
        return response;
    }
}
