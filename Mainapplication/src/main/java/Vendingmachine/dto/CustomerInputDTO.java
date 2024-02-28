package Vendingmachine.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//package com.VendingMachine.VendingMachine01.dto;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//public class CustomerInputDTO {
//
//    @NotNull
//    @JsonIgnore
//    private final int productId;
//    @NotNull
//    @JsonIgnore
//    private final int price;
//    @NotNull
//    @JsonIgnore
//    private final int quantity;
//
//    @JsonCreator
//    public static CustomerInputDTO create(
//            @JsonProperty("product_code") int productId,
//            @JsonProperty("giving_amount") int price,
//            @JsonProperty("quantity") int quantity) {
//        return builder()
//                .productId(productId)
//                .price(price)
//                .quantity(quantity)
//                .build();
//    }
//
//    public CustomerInputDTO(CustomerInputBuilder customerInputBuilder) {
//        this.productId = customerInputBuilder.productId;
//        this.price = customerInputBuilder.price;
//        this.quantity = customerInputBuilder.quantity;
//    }
//
//    public static CustomerInputBuilder builder() {
//        return new CustomerInputBuilder();
//    }
//
//    public int getProductId() {
//        return productId;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public static class CustomerInputBuilder {
//        private int productId;
//        private int price;
//        private int quantity;
//
//        public CustomerInputBuilder() {
//        }
//
//        public CustomerInputBuilder productId(int productId) {
//            this.productId = productId;
//            return this;
//        }
//
//        public CustomerInputBuilder price(int price) {
//            this.price = price;
//            return this;
//        }
//
//        public CustomerInputBuilder quantity(int quantity) {
//            this.quantity = quantity;
//            return this;
//        }
//
//        public CustomerInputDTO build() {
//            return new CustomerInputDTO(this);
//        }
//    }
//}
//public class CustomerInputDTO {
//
//    @NotNull
//    @Valid
//    private final List<@Valid PurchaseInputDTO> products;
//
//
//    @JsonCreator
//    public static CustomerInputDTO create(@JsonProperty("products") List<PurchaseInputDTO> products) {
//        return new CustomerInputDTO(products);
//    }
//
//    public CustomerInputDTO(List<PurchaseInputDTO> products) {
//        this.products = products;
//    }
//
//    public List<PurchaseInputDTO> getProducts() {
//        return products;
//    }
//}
public class CustomerInputDTO {

    @NotNull
    @Valid
    private final List<@Valid PurchaseInputDTO> products;



    @JsonCreator
    public static CustomerInputDTO create(@JsonProperty("products") List<Map<String, Object>> products) {
        List<PurchaseInputDTO> purchaseInputDTOs = products.stream()
                .map(map -> PurchaseInputDTO.create(
                        (int) map.get("productId"),
                        (int) map.get("price"),
                        (int) map.get("quantity")
                ))
                .collect(Collectors.toList());
        return new CustomerInputDTO(purchaseInputDTOs);
    }

    public CustomerInputDTO(List<PurchaseInputDTO> products) {
        this.products = products;
    }

    public List<PurchaseInputDTO> getProducts() {
        return products;
    }
}
