package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Integer> {
}
