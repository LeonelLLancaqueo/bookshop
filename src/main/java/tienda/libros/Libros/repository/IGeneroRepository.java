package tienda.libros.Libros.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tienda.libros.Libros.models.Genero;


@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer>{
    /* 
    public List<Genero> getAll();
    public Genero getGeneroByID(int id);
    public Genero updateGenero(int id);
    public Genero saveGenero(Genero genero);
    public Genero deleteGenero(int id);
    */
}
