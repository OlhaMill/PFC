package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.entities.DietaReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente ORDER BY id DESC LIMIT 6", nativeQuery = true)
    List<Cliente> obtenerUltimosClientes();

    @Query(value = "SELECT c.nombre, c.valoración FROM Cliente c ORDER BY c.id DESC LIMIT 5", nativeQuery = true)
    List<Object[]> obtenerTop5Valoracion();

    List<Cliente> findByNutricionistaId(Integer nutricionistaId);

    @Query("SELECT c FROM Cliente c WHERE (LOWER(c.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.telefono) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND c.nutricionista.id = :nutrId")
    List<Cliente> searchByKeywordAndNutricionistaId(@Param("keyword") String keyword, @Param("nutrId") Integer nutrId);
}
