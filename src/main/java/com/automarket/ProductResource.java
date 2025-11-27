package com.automarket;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @GET
    public List<Product> getAll() {
        return Product.listAll();
    }

    @POST
    @Transactional
    public void create(Product product) {
        product.persist();
    }

    // === НОВЕ: ОНОВЛЕННЯ (UPDATE) ===
    @PUT
    @Path("/{id}")
    @Transactional
    public Product update(@PathParam("id") Long id, Product newData) {
        // 1. Шукаємо машину в базі
        Product entity = Product.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Машину не знайдено", 404);
        }

        // 2. Оновлюємо дані
        entity.brand = newData.brand;
        entity.model = newData.model;
        entity.licensePlate = newData.licensePlate;
        entity.price = newData.price;
        entity.isAvailable = newData.isAvailable;

        // 3. Panache автоматично збереже зміни при виході з методу (бо це транзакція)
        return entity;
    }

    // === НОВЕ: ВИДАЛЕННЯ (DELETE) ===
    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        // Магія: видаляє за ID. Якщо не знайшло - поверне false (але нам байдуже для лаби)
        boolean deleted = Product.deleteById(id);
        if (!deleted) {
            throw new WebApplicationException("Машину не знайдено", 404);
        }
    }
}