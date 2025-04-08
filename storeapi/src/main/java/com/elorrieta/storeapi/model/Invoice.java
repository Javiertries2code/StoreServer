package com.elorrieta.storeapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the invoices database table.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
@NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "invoice_id")
    private long invoiceId;

    @Column(name = "CIF")
    private String cif;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private byte paid;

    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "invoice")
    private List<ProductInvoice> productInvoices;
}
