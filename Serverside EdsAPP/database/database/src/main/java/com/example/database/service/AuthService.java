package com.example.database.service;
import com.example.database.exception.ResourceNotFoundException;
import com.example.database.model.User;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class AuthService {

    @Autowired
    private UserRepository reposLogin;

    public User validateUser(String username, String password) throws ResourceNotFoundException {
        User user = reposLogin.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            throw new ResourceNotFoundException("Invalid username or password");
        }
        return user;
    }
}
