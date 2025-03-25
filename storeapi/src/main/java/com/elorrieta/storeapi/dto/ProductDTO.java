package com.elorrieta.storeapi.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private long productId;
    private int amount;
    private BigDecimal cost;
    private byte enabled;
    private byte[] image;
    private int minimumAmount;
    private String name;
    private BigDecimal retailPrice;
    private byte season;

    public ProductDTO() {}

    public ProductDTO(long productId, int amount, BigDecimal cost, byte enabled,
                      byte[] image, int minimumAmount, String name,
                      BigDecimal retailPrice, byte season) {
        this.productId = productId;
        this.amount = amount;
        this.cost = cost;
        this.enabled = enabled;
        this.image = image;
        this.minimumAmount = minimumAmount;
        this.name = name;
        this.retailPrice = retailPrice;
        this.season = season;
    }


    
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(int minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public byte getSeason() {
        return season;
    }

    public void setSeason(byte season) {
        this.season = season;
    }
}
