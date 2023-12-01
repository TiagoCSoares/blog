package br.com.unifalmg.blog.controller;


import br.com.unifalmg.blog.controller.request.UserRequest;
import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    //localhost:2020/api/user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        User user = service.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<User> createUSer(@RequestBody UserRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

}