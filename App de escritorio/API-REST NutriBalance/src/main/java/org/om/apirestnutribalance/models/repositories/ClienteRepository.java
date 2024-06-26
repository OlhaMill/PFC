package org.om.apirestnutribalance.models.repositories;

import jakarta.transaction.Transactional;
import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.entities.DietaReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente ORDER BY id DESC LIMIT 6", nativeQuery = true)
    List<Cliente> obtenerUltimosClientes();

    @Query(value = "SELECT c.nombre, c.valoración FROM Cliente c ORDER BY c.id DESC", nativeQuery = true)
    List<Object[]> obtenerTop5Valoracion();

    List<Cliente> findByNutricionistaId(Integer nutricionistaId);

    @Query("SELECT c FROM Cliente c WHERE (LOWER(c.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.telefono) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND c.nutricionista.id = :nutrId")
    List<Cliente> searchByKeywordAndNutricionistaId(@Param("keyword") String keyword, @Param("nutrId") Integer nutrId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cliente ( nombre, email, telefono, fecha_nacimiento, nutricionista_id, genero) " +
            "VALUES ( :nombre, :email, :telefono, :fechaNacimiento, :nutricionistaId, :genero)", nativeQuery = true)
    void insertCliente(
            @Param("nombre") String nombre,
            @Param("email") String email,
            @Param("telefono") String telefono,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("nutricionistaId") Integer nutricionistaId,
            @Param("genero") String genero
    );
}
