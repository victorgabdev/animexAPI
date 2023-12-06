package com.meusite.animex.services;

import com.meusite.animex.entities.User;
import com.meusite.animex.entities.dtos.UserDTO;
import com.meusite.animex.exceptions.CustomException;
import com.meusite.animex.exceptions.exception.EmailAlreadyInUseException;
import com.meusite.animex.exceptions.exception.NoUsersFoundException;
import com.meusite.animex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(User user) {
        if (existsByEmail(user.getEmail())) throw new EmailAlreadyInUseException(user.getEmail());

        User userCreated =  userRepository.save(user);
        return convertToDTO(userCreated);
    }

    public UserDTO getUserByEmail(String email) {
        User user = getUserByEmailInternal(email);
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new NoUsersFoundException();
        return users.stream().map(this::convertToDTO).toList();
    }

    public void updateUser(String email, UserDTO updatedUserDTO) {
        User user = getUserByEmailInternal(email);
        user.setName(updatedUserDTO.getName());
        user.setEmail(updatedUserDTO.getEmail());

        userRepository.save(user);
    }

    public void deleteUser(String email) {
        User user = getUserByEmailInternal(email);
        userRepository.delete(user);
    }


    // ---------------------- METODOS PRIVADOS ----------------------
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void validateEmailFormat(String email) {
        if(!isValidEmail(email)) throw new CustomException("Formato de email inválido: " + email, HttpStatus.BAD_REQUEST);
    }

    private User getUserByEmailInternal(String email) {
        validateEmailFormat(email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) throw new CustomException("Usuário não encontrado com o email: " + email, HttpStatus.NOT_FOUND);

        return optionalUser.get();
    }

    private boolean existsByEmail(String email) {
        validateEmailFormat(email);
        return userRepository.existsByEmail(email);
    }
}
