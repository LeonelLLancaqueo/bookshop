package tienda.libros.Libros.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tienda.libros.Libros.models.Autor;



import java.util.List;

import java.lang.Object;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Integer>{
         
    @Query(value = "select a.id_autor, a.nombre, a.nacionalidad , a.url_image ,a.descripcion  from autor a", nativeQuery = true)
    public List<Object[]> getAllUserConvertedToDto();

    @Query(value = "select a.id_autor, a.nombre, a.nacionalidad , a.url_image ,a.descripcion from autor a where a.nacionalidad = :nacionalidad", nativeQuery = true)
    public List<Object[]> getPorNacionalidad(
        @Param ("nacionalidad") String nacionalidad
    );
    @Query(value = "select a.id_autor, a.nombre from autor a", nativeQuery = true)
    public List<Object[]> getIdAndName();
}
