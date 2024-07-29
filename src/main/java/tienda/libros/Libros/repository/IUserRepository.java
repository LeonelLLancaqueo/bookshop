package tienda.libros.Libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tienda.libros.Libros.models.User;
import java.util.List;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {


    @Query(value = "select * from user u where u.username= :username and u.password= :password ", nativeQuery = true)
    public List<User> login(
        @Param("username") String username,
        @Param("password") String password
        );
}

