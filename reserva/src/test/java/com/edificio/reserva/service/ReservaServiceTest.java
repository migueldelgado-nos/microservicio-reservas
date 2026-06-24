package com.edificio.reserva.service;

import com.edificio.reserva.model.Reserva;
import com.edificio.reserva.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import net.datafaker.Faker;
import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    private final Faker faker = new Faker();

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void buscarPorId_deberiaRetornarReserva() {

        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setNombreResidente("Juan Perez");

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(reserva));

        Reserva resultado = reservaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombreResidente());

        verify(reservaRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_deberiaLanzarErrorSiNoExiste() {

        when(reservaRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException error = assertThrows(RuntimeException.class, () -> {
            reservaService.buscarPorId(99L);
        });

        assertEquals("Reserva no encontrada", error.getMessage());

        verify(reservaRepository, times(1)).findById(99L);
    }

    @Test
    void crear_deberiaGuardarReserva() {

        Reserva reserva = new Reserva();
        reserva.setNombreResidente(faker.name().fullName());

        when(reservaRepository.save(reserva))
                .thenReturn(reserva);

        Reserva resultado = reservaService.crear(reserva);

        assertNotNull(resultado);
        assertEquals(reserva.getNombreResidente(), resultado.getNombreResidente());

        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void eliminar_deberiaEliminarReserva() {

        Reserva reserva = new Reserva();
        reserva.setId(1L);

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(reserva));

        reservaService.eliminar(1L);

        verify(reservaRepository, times(1)).delete(reserva);
    }

    @Test
    void listar_deberiaRetornarReservas() {

        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setNombreResidente("Juan Perez");

        when(reservaRepository.findAll())
                .thenReturn(List.of(reserva));

        List<Reserva> resultado = reservaService.listar();

        assertEquals(1, resultado.size());
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void actualizar_deberiaModificarReserva() {

        Reserva existente = new Reserva();
        existente.setId(1L);
        existente.setNombreResidente("Juan Perez");

        Reserva datos = new Reserva();
        datos.setNombreResidente("Maria Lopez");

        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(reservaRepository.save(existente))
                .thenReturn(existente);

        Reserva resultado = reservaService.actualizar(1L, datos);

        assertEquals("Maria Lopez", resultado.getNombreResidente());
        verify(reservaRepository, times(1)).save(existente);
    }
}