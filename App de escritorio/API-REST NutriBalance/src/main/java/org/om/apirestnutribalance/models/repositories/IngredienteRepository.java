package org.om.apirestnutribalance.models.repositories;

import org.om.apirestnutribalance.models.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
