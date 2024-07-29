package tienda.libros.Libros.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.StringBuilder;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String password;

    public String encriptPassword(String password){
        StringBuilder newPassword= new StringBuilder();
        char[] passwordArr= password.toCharArray();
        
        for(int i= 0; i< passwordArr.length; i++){
            if(i%2 == 0){
                newPassword.append("dhe5d8a7ss"+passwordArr[i]);
            }else{
                newPassword.append("efd5fd78f6"+passwordArr[i]);
            }

        }
        return newPassword.toString();
    }
    public String desecriptPassword(String password){
        char[] passwordArray= password.toCharArray();
        
        
        StringBuilder newPassword= new StringBuilder();
        for(int i=10; i< passwordArray.length; i+=11){
            newPassword.append(passwordArray[i]);
        }
        return newPassword.toString();
    }
}
