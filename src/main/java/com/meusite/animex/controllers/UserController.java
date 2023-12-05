package com.meusite.animex.controllers;

import com.meusite.animex.entities.User;
import com.meusite.animex.entities.dtos.UserDTO;
import com.meusite.animex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    // criar usuario
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        try {
            UserDTO userCreated = userService.createUser(user);
            URI location = new URI("/users" + userCreated.getId().toString());
            return ResponseEntity.created(location).body(userCreated);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // ler um usuario


    // update no usuario
    // deletar um usuario

    // listar todos os usuarios
}
