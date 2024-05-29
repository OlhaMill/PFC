package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.DietaReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietaRecetaRepository extends JpaRepository<DietaReceta, Integer> {
    List<DietaReceta> findByDieta_Id(Integer dietaId);
}
