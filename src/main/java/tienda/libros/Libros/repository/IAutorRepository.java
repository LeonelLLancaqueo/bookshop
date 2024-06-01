package tienda.libros.Libros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tienda.libros.Libros.models.Autor;
import tienda.libros.Libros.models.Genero;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Integer>{

    /* 
    public List<Autor> getAll();
    public Autor getAutorByID(int id);
    public Autor updateAutor(int id);
    public Autor saveAutor(Autor autor);
    public Autor deleteAutor(int id);
    */

    /* 
    
    @Query(value = "SELECT g FROM autor a join libro l join genero g where a.id_autor = :id", nativeQuery = true)
    public List<Genero> colGenerosUsados(
        @Param("id") int id
    );
    
    */

    


     
     
}
