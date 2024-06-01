package tienda.libros.Libros.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tienda.libros.Libros.models.Libro;

@Repository
public interface ILibroRespository extends JpaRepository<Libro,Integer>{

    //getAllLibro
    //getLibroByid
    //putLibro
    //postLibro
    //deleteLibro
    /* 
    public List<Libro> getAll();
    public Libro getLibroByID(int id);
    public Libro updateLibro(int id);
    public Libro saveLibro(Libro libro);
    public Libro deleteLibro(int id);
    */

    

    
} 
