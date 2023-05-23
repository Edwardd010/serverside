package com.example.database.service;

import com.example.database.dto.UserDTO;
import com.example.database.exception.ResourceNotFoundException;
import com.example.database.exception.UsernameExistsException;
import com.example.database.model.User;
import com.example.database.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repos;

    public UserService(UserRepository repos){
        this.repos = repos;
    }

    public void checkUserExists(String username) {
        User user = repos.findByUsername(username);
        if (user != null) {
            throw new UsernameExistsException("Username already exists: " + username);
        }
    }



    public Long createUser(UserDTO udto){

            // mapping
        User u = new User();
        u.setUsername(udto.username);
        u.setPassword(udto.password);

        repos.save(u);

        return u.getId();
    }

    public UserDTO getUser(Long id){
        User u = repos.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        // mapping
        UserDTO udto = new UserDTO();
        udto.id = u.getId();
        udto.username = u.getUsername();
        udto.password = u.getPassword();

        return udto;
    }

    public List<UserDTO> getUsers(){
        Iterable<User> users = repos.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            UserDTO udto = new UserDTO();
            udto.id = user.getId();
            udto.username = user.getUsername();
            udto.password = user.getPassword();
            usersDTO.add(udto);
        }
        return usersDTO;
    }

    public void delete(Long id){
        repos.deleteById(id);
    }

}
