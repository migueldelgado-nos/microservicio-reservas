package com.edificio.reserva.service;

import com.edificio.reserva.model.Departamento;
import com.edificio.reserva.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private static final Logger logger = LoggerFactory.getLogger(DepartamentoService.class);

    private final DepartamentoRepository departamentoRepository;

    //Listar todos
    public List<Departamento> listar(){
        logger.info("Listado departamentos");
        return departamentoRepository.findAll();
    }

    //Buscar por id
    public Departamento buscarPorId(Long id){
        logger.info("Buscando departamento con id: {}",id);
        return departamentoRepository.findById(id).orElseThrow(()-> new RuntimeException("Departamento no encontrado"));
    }

    // CREAR
    public Departamento crear(Departamento departamento) {
        logger.info("Creando departamento numero: {}",departamento.getNumero());
        if (departamentoRepository.existsByNumero(departamento.getNumero())) {
            throw new RuntimeException("El número del departamento ya existe");
        }
        return departamentoRepository.save(departamento);
    }

    // ACTUALIZAR
    public Departamento actualizar(Long id, Departamento datos) {
        logger.info("Actualizando departamento con el id: {}",id);
        Departamento departamento = buscarPorId(id);

        departamento.setNumero(datos.getNumero());
        departamento.setPiso(datos.getPiso());
        departamento.setEstado(datos.getEstado());

        return departamentoRepository.save(departamento);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        logger.info("Eliminando departamento con id: {}",id);
        Departamento departamento = buscarPorId(id);
        departamentoRepository.delete(departamento);
    }
}
