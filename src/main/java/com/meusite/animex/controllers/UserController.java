package com.meusite.animex.controllers;

import com.meusite.animex.entities.User;
import com.meusite.animex.entities.dtos.UserDTO;
import com.meusite.animex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    // criar usuario
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
       UserDTO userCreated = userService.createUser(user);
       URI location = URI.create("/users/" + userCreated.getId());
       return ResponseEntity.created(location).body(userCreated);
    }

    // ler um usuario através do email
    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    // listar todos os usuarios
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = userService.getAllUsers();
        return ResponseEntity.ok(usersDTO);
    }

    // update no usuario
    @PutMapping("/update/{email}")
    public ResponseEntity<Void> updateUser(@PathVariable String email,
                                           @RequestBody UserDTO updatedUserDTO) {
        userService.updateUser(email, updatedUserDTO);
        return ResponseEntity.noContent().build();
    }


    // deletar um usuario
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

}
