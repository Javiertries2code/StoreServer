package com.elorrieta.storeapi.model;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the product_invoice database table.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_invoice")
@NamedQuery(name = "ProductInvoice.findAll", query = "SELECT p FROM ProductInvoice p")
public class ProductInvoice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "detail_id")
    private long detailId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
