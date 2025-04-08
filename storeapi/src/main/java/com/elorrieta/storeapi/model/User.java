package com.elorrieta.storeapi.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the users database table.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private long userId;

    private String email;
    private byte enabled;

    @Lob
    private byte[] image;

    private String name;

    @JsonIgnore
    private String pass;

    private String rol;

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;
}
