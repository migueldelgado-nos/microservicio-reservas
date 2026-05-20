package com.edificio.reserva.service;

import com.edificio.reserva.model.Reserva;
import com.edificio.reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);

    private final ReservaRepository reservaRepository;

    //Listar
    public List<Reserva> listar(){
        logger.info("Listado reservas");
        return reservaRepository.findAll();
    }

    //Buscar por id
    public Reserva buscarPorId(Long id){
        logger.info("Buscando reserva con id: {}",id);
        return reservaRepository.findById(id).orElseThrow(()-> new RuntimeException("Reserva no encontrada"));
    }

    //Crear
    public Reserva crear(Reserva reserva) {
        logger.info("Creando reserva");
        return reservaRepository.save(reserva);
    }

    //Actualizar
    public Reserva actualizar(Long id, Reserva datos) {
        Reserva reserva = buscarPorId(id);

        reserva.setNombreResidente(datos.getNombreResidente());
        reserva.setFechaInicio(datos.getFechaInicio());
        reserva.setFechaFin(datos.getFechaFin());
        reserva.setEstado(datos.getEstado());
        reserva.setDepartamento(datos.getDepartamento());

        return reservaRepository.save(reserva);
    }

    //Eliminar
    public void eliminar(Long id) {
        Reserva reserva = buscarPorId(id);
        reservaRepository.delete(reserva);
    }
}
