package com.amboiko.controller;

import com.amboiko.model.Cart;
import com.amboiko.model.Product;
import com.amboiko.model.User;
import com.amboiko.repository.CartRepository;
import com.amboiko.repository.ProductRepository;
import com.amboiko.repository.UserRepository;
import com.amboiko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public User createUser(@RequestBody User user) {

        System.out.println("create user: " + user);
        Cart cart = new Cart();
        Map<Product, Integer> products = new HashMap<>();
        products.put(productRepository.findById(2L).orElse(null), 2);
        products.put(productRepository.findById(3L).orElse(null), 3);
        cart.setProducts(products);
        cartRepository.save(cart);
        user.setCart(cart);
        return userService.createUser(user);
    }
}
