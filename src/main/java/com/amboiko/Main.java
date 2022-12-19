package com.amboiko;

import com.amboiko.model.Cart;
import com.amboiko.model.Product;
import com.amboiko.model.User;
import com.amboiko.repository.CartRepository;
import com.amboiko.repository.ProductRepository;
import com.amboiko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        Cart cart = new Cart();
        Map<Product, Integer> ss = new HashMap<>();
        ss.put(productRepository.findById(2L).orElse(null), 10);
        ss.put(productRepository.findById(3L).orElse(null), 10);
        cart.setProducts(ss);
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        System.out.println(user);
    }
}
