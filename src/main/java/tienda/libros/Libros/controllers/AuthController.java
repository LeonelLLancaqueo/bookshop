package tienda.libros.Libros.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.User;
import tienda.libros.Libros.services.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User userRequest) {
        Optional<User> userResponse= userService.login(userRequest);
        if(userResponse.isPresent()){
            return new ResponseEntity<>(userResponse.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("El usuario o contraseña son incorrectos", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User userRequest) {
        return new ResponseEntity<>(userService.register(userRequest), HttpStatus.OK);
    }
    
}
