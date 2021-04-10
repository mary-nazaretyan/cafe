package com.sflpro.cafe.config;

import com.sflpro.cafe.model.enums.UserRole;
import com.sflpro.cafe.model.jpa.Product;
import com.sflpro.cafe.model.jpa.Table;
import com.sflpro.cafe.model.jpa.User;
import com.sflpro.cafe.repository.ProductRepository;
import com.sflpro.cafe.repository.TableRepository;
import com.sflpro.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInit {

    public static final String WAITER = "waiter";
    public static final String MANAGER = "manager";
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TableRepository tableRepository;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            this.initUsers();
            this.initProducts();
            this.initTables();
        };
    }

    private void initTables() {
        tableRepository.deleteAll();

        final User wa = userRepository.findByUsername(WAITER).get();
        tableRepository.save(new Table("first", wa));
        tableRepository.save(new Table("second", wa));
    }

    private void initProducts() {
        productRepository.deleteAll();

        productRepository.save(new Product(300, "Cola"));
        productRepository.save(new Product(350, "Fanta"));
    }

    private void initUsers() {
        userRepository.deleteAll();

        final User manager = new User();
        manager.setPassword(passwordEncoder.encode(MANAGER));
        manager.setUsername(MANAGER);
        manager.setEmail("test@mail.com");
        manager.setRole(UserRole.MANAGER);

        userRepository.save(manager);

        final User waiter = new User();
        waiter.setPassword(passwordEncoder.encode(WAITER));
        waiter.setUsername(WAITER);
        waiter.setEmail("test@mail.com");
        waiter.setRole(UserRole.WAITER);

        userRepository.save(waiter);
    }

}
