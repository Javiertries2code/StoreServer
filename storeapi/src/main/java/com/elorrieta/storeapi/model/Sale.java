package com.elorrieta.storeapi.model;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the sales database table.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
@NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s")
public class Sale implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sale_id")
    private long saleId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
