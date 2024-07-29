package tienda.libros.Libros.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import tienda.libros.Libros.models.Libro;


import java.util.List;
import java.util.Optional;



@Repository
public interface ILibroRespository extends JpaRepository<Libro,Integer>{
    String selectDto= "l.id_libro, l.nombre, l.url, l.descripcion, l.precio, l.unidades_en_total, l.unidades_disponibles, l.unidades_vendidas, g.nombre, a.nombre";
    String libroJoinAutorJoinGenero= "libro l inner join genero g on (l.id_genero = g.id_genero) inner join autor a on(l.id_autor = a.id_autor)";

    String filterCantTotal= " and l.unidades_en_total >= :cantUnidMin and  l.unidades_en_total <= :cantUnidMax";
    String filterCantVend=" and l.unidades_vendidas >= :cantVendMin and  l.unidades_vendidas <= :cantVendMax";
    String filterCantDisp=" and l.unidades_disponibles >= :cantDispMin and  l.unidades_disponibles <= :cantDispMax";
    String whereFilterLibros= "l.precio >= :precioMin and  l.precio <= :precioMax " + filterCantDisp + filterCantTotal + filterCantVend;

    @Query(value = "select "+selectDto+" from "+libroJoinAutorJoinGenero +" where l.id_autor=:idAutor ", nativeQuery = true)
    public List<Object[]> getLibrosByIdAutor(
        @Param("idAutor") int idAutor
    ); 

    @Query(value = "select "+ selectDto+" from "+libroJoinAutorJoinGenero, nativeQuery = true)
    public List<Object[]> getLibrosDto();

    @Modifying
    @Transactional
    @Query(value = "update libro set id_genero=(select id_genero from genero where genero.nombre = :nombreGenero), nombre= :nombre, descripcion= :descripcion,precio= :precio, url= :url,unidades_en_total= :uT,unidades_disponibles= :uD, unidades_vendidas= :uV   where libro.id_libro = :idLibro", nativeQuery = true)
    public void UpdateLibroConGenero(
        @Param("idLibro") int idLibro,
        @Param("nombreGenero") String nombreGenero,
        @Param("nombre") String nombre,
        @Param("url") String url,
        @Param("descripcion") String descripcion,
        @Param("precio") double precio,
        @Param("uT") int uT,
        @Param("uD") int uD,
        @Param("uV") int uV
    );

    
    @Query(value = "select "+ selectDto+" from "+ libroJoinAutorJoinGenero+" where l.id_libro= :idLibro", nativeQuery = true)
    public Optional<Object[]> getLibroDtoById(
        @Param("idLibro") int idLibro
    );
        
    @Query(value = "select "+ selectDto+" from "+libroJoinAutorJoinGenero+" where "+whereFilterLibros, nativeQuery = true)
    public List<Object[]> getFilterLibrosByOptions(
        @Param("precioMin") int precioMin,
        @Param("precioMax") int precioMax,
        @Param("cantDispMin") int cantDispMin,
        @Param("cantDispMax") int cantDispMax,
        @Param("cantVendMin") int cantVendMin,
        @Param("cantVendMax") int cantVendMax,
        @Param("cantUnidMin") int cantUnidMin,
        @Param("cantUnidMax") int cantUnidMax
    );
    @Query(value = "select "+ selectDto+" from "+libroJoinAutorJoinGenero+" where "+whereFilterLibros+" and g.id_genero = :idGenero", nativeQuery = true)
    public List<Object[]> getFilterLibrosByOptionsAndGenero(
        @Param("precioMin") int precioMin,
        @Param("precioMax") int precioMax,
        @Param("cantDispMin") int cantDispMin,
        @Param("cantDispMax") int cantDispMax,
        @Param("cantVendMin") int cantVendMin,
        @Param("cantVendMax") int cantVendMax,
        @Param("cantUnidMin") int cantUnidMin,
        @Param("cantUnidMax") int cantUnidMax,
        @Param("idGenero") int idGenero
    );
} 
