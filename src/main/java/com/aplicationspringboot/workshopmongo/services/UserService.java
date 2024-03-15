package com.aplicationspringboot.workshopmongo.services;

import com.aplicationspringboot.workshopmongo.domain.User;
import com.aplicationspringboot.workshopmongo.dto.UserDTO;
import com.aplicationspringboot.workshopmongo.repository.UserRepository;
import com.aplicationspringboot.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll(){
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream().map( x -> new UserDTO(x)).collect(Collectors.toList());
        return userDTOList;
    }

    public UserDTO findById(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        return new UserDTO(user);
    }

    public UserDTO create(User user){
        //o metodo insert vai lançar uma excecao caso nao achar o usuario no banco
        user = userRepository.insert(user);
        return new UserDTO(user);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User UserDTOToUser(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
