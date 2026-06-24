package com.edificio.reserva.service;

import com.edificio.reserva.model.Departamento;
import com.edificio.reserva.model.EstadoDepartamento;
import com.edificio.reserva.repository.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import net.datafaker.Faker;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartamentoServiceTest {

    private final Faker faker = new Faker();

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoService departamentoService;

    @Test
    void buscarPorId_deberiaRetornarDepartamento() {
        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNumero("101");
        departamento.setPiso(1);
        departamento.setEstado(EstadoDepartamento.DISPONIBLE);

        when(departamentoRepository.findById(1L))
                .thenReturn(Optional.of(departamento));

        Departamento resultado = departamentoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("101", resultado.getNumero());
        assertEquals(EstadoDepartamento.DISPONIBLE, resultado.getEstado());
    }

    @Test
    void buscarPorId_deberiaLanzarErrorSiNoExiste() {
        when(departamentoRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException error = assertThrows(RuntimeException.class, () -> {
            departamentoService.buscarPorId(99L);
        });

        assertEquals("Departamento no encontrado", error.getMessage());
    }

    @Test
    void crear_deberiaGuardarDepartamentoSiNumeroNoExiste() {

        Departamento departamento = new Departamento();
        departamento.setNumero(faker.number().digits(3));
        departamento.setPiso(1);
        departamento.setEstado(EstadoDepartamento.DISPONIBLE);

        when(departamentoRepository.existsByNumero(departamento.getNumero()))
                .thenReturn(false);

        when(departamentoRepository.save(departamento))
                .thenReturn(departamento);

        Departamento resultado = departamentoService.crear(departamento);

        assertEquals(departamento.getNumero(), resultado.getNumero());
        verify(departamentoRepository).save(departamento);
    }

    @Test
    void crear_deberiaLanzarErrorSiNumeroExiste() {
        Departamento departamento = new Departamento();
        departamento.setNumero("101");

        when(departamentoRepository.existsByNumero("101"))
                .thenReturn(true);

        RuntimeException error = assertThrows(RuntimeException.class, () -> {
            departamentoService.crear(departamento);
        });

        assertEquals("El número del departamento ya existe", error.getMessage());
    }

    @Test
    void listar_deberiaRetornarDepartamentos() {

        Departamento departamento = new Departamento();
        departamento.setId(1L);
        departamento.setNumero("101");

        when(departamentoRepository.findAll())
                .thenReturn(List.of(departamento));

        List<Departamento> resultado = departamentoService.listar();

        assertEquals(1, resultado.size());
        verify(departamentoRepository, times(1)).findAll();
    }

    @Test
    void actualizar_deberiaModificarDepartamento() {

        Departamento existente = new Departamento();
        existente.setId(1L);
        existente.setNumero("101");
        existente.setPiso(1);
        existente.setEstado(EstadoDepartamento.DISPONIBLE);

        Departamento datos = new Departamento();
        datos.setNumero("102");
        datos.setPiso(2);
        datos.setEstado(EstadoDepartamento.OCUPADO);

        when(departamentoRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(departamentoRepository.save(existente))
                .thenReturn(existente);

        Departamento resultado = departamentoService.actualizar(1L, datos);

        assertEquals("102", resultado.getNumero());
        assertEquals(2, resultado.getPiso());
        assertEquals(EstadoDepartamento.OCUPADO, resultado.getEstado());
        verify(departamentoRepository, times(1)).save(existente);
    }

    @Test
    void eliminar_deberiaEliminarDepartamento() {

        Departamento departamento = new Departamento();
        departamento.setId(1L);

        when(departamentoRepository.findById(1L))
                .thenReturn(Optional.of(departamento));

        departamentoService.eliminar(1L);

        verify(departamentoRepository, times(1)).delete(departamento);
    }

}
