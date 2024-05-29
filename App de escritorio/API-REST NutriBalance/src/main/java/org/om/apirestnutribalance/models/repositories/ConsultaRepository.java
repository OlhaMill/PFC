package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.Cliente;
import org.om.apirestnutribalance.models.entities.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    @Query(value = "SELECT * FROM Consulta WHERE nutricionista_id = :id ORDER BY fecha_hora DESC LIMIT 6", nativeQuery = true)
    List<Consulta> findTop6ByIdOrderByFechaHoraDesc(@Param("id") Long id);

    @Query("SELECT c FROM Consulta c WHERE c.nutricionista.id = :nutricionistaId")
    List<Consulta> findByNutricionistaId(@Param("nutricionistaId") Integer nutricionistaId);
}
