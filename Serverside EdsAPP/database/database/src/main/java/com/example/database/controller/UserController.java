package com.example.database.controller;
import com.example.database.dto.UserDTO;
import com.example.database.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO udto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        Long id = service.createUser(udto);
        udto.id = id;
        // no idea what this is???????
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(udto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO udto = service.getUser(id);
        System.out.println("hello world");
        System.out.println("hello world");
        return ResponseEntity.ok(udto);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        Iterable<UserDTO> usersIterable = service.getUsers();
        List<UserDTO> usersList = new ArrayList<>();
        for (UserDTO user : usersIterable) {
            usersList.add(user);
        }
        return ResponseEntity.ok(usersList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().body("User " + id + " got removed!");
    }

}
