package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {
}
