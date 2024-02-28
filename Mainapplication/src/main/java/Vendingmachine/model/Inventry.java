package Vendingmachine.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public  class Inventry implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  int productId;
    @NotBlank
    private  String name;
    @NotNull
    private  int productPrice;
    @NotNull
    private  int productInventoryCount;



    public Inventry(int productId, String name, int productPrice, int productInventoryCount, String currencyUnit) {
        this.productId = productId;
        this.name = name;
        this.productPrice = productPrice;
        this.productInventoryCount = productInventoryCount;

    }
    public Inventry() {
    }

    public int getProductId() {
        return productId;
    }


    public  String getName() {
        return name;
    }

    public  int getProductPrice() {
        return productPrice;
    }


    public  int getProductInventoryCount() {
        return productInventoryCount;
    }



    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductInventoryCount(int productInventoryCount) {
        this.productInventoryCount = productInventoryCount;
    }


}
