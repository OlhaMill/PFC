package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Integer> {
}
