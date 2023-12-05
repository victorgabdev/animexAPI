package com.meusite.animex.services;

import com.meusite.animex.entities.User;
import com.meusite.animex.entities.dtos.UserDTO;
import com.meusite.animex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findbyEmail(User user) {
        // Se o email estiver escrito errado

        // se o user não existir

        // retornar
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        return userOptional.orElse(null);
    }

    public UserDTO createUser(User user) {
        // se o email já estiver em uso
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) throw new IllegalArgumentException("Email já em uso!");


        User userCreated =  userRepository.save(user);
        return new UserDTO(userCreated);
    }
}
