package com.sflpro.cafe.service;

import java.util.List;

import com.sflpro.cafe.model.jpa.Product;
import com.sflpro.cafe.model.jpa.User;
import com.sflpro.cafe.repository.ProductRepository;
import com.sflpro.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void save(Product product){
        productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
