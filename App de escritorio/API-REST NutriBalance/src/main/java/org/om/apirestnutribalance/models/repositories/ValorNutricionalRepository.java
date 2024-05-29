package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.ValorNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValorNutricionalRepository extends JpaRepository<ValorNutricional, Integer> {
}
