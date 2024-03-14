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
}
