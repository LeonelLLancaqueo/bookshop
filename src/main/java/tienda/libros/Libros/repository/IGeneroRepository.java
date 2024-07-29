package tienda.libros.Libros.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tienda.libros.Libros.models.Genero;


@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer>{


    @Query(value = "select g.id_genero, g.nombre, g.descripcion from genero g where g.nombre = :name  ", nativeQuery = true)
    public List<Object[]> getGeneroByName( @Param("name") String name);
    
    @Query(value = "select g.id_genero, g.nombre from genero g", nativeQuery = true)
    public List<Object[]> getIdAndName();
}
