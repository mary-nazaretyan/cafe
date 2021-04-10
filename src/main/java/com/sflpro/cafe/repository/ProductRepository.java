package com.sflpro.cafe.repository;

import com.sflpro.cafe.model.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
