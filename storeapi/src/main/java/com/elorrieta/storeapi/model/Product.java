package com.elorrieta.storeapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the products database table.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id")
    private long productId;

    private int amount;
    private BigDecimal cost;
    private byte enabled;

    @Lob
    private byte[] image;

    @Column(name = "minimum_amount")
    private int minimumAmount;

    private String name;

    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    private byte season;

    @OneToMany(mappedBy = "product")
    private List<ProductInvoice> productInvoices;

    @OneToMany(mappedBy = "product")
    private List<Sale> sales;
}
