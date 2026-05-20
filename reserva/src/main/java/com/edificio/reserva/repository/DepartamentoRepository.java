package com.edificio.reserva.repository;

import com.edificio.reserva.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    boolean existsByNumero(String numero);

}
