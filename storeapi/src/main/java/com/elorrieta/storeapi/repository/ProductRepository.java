package com.elorrieta.storeapi.repository;


import com.elorrieta.storeapi.model.Product;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	  @Modifying
	    @Transactional
	    @Query("UPDATE Product p SET p.amount = p.amount + 1 WHERE p.id = :id")
	    void incrementAmount(@Param("id") Long id);

	    @Modifying
	    @Transactional
	    @Query("UPDATE Product p SET p.amount = p.amount - 1 WHERE p.id = :id")
	    void decrementAmount(@Param("id") Long id);
}