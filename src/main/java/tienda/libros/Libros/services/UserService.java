package tienda.libros.Libros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tienda.libros.Libros.models.User;
import tienda.libros.Libros.repository.IUserRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public Optional<User> login(User userRequest){
        
        List<User> user= userRepository.login(userRequest.getUsername(), userRequest.encriptPassword(userRequest.getPassword()));
        Optional<User> userResponse= Optional.empty();
        if(user.size() == 1){
            User userAux= user.get(0);
            //desencripto la contraseña para la devolucion
            userAux.setPassword(userAux.desecriptPassword(userAux.getPassword()));
            userResponse= Optional.of(userAux);
            
        }
        return userResponse;
    }
    public User register(User userRequest){
        String passwordEncripted= userRequest.encriptPassword(userRequest.getPassword());
        userRequest.setPassword(passwordEncripted);
        User userAux= userRepository.save(userRequest);
        //desencripto la contraseña para la devolucion
        userAux.setPassword(userAux.desecriptPassword(userAux.getPassword()));
        
        return userAux;
    }
}
