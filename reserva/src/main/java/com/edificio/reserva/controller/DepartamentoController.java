package com.edificio.reserva.controller;


import com.edificio.reserva.model.Departamento;
import com.edificio.reserva.service.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    //Listar
    @GetMapping
    public ResponseEntity<List<Departamento>> listar() {
        return ResponseEntity.ok(departamentoService.listar());
    }

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.buscarPorId(id));
    }

    //Crear
    @PostMapping
    public ResponseEntity<Departamento> crear(@Valid @RequestBody Departamento departamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.crear(departamento));
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@Valid @PathVariable Long id, @RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoService.actualizar(id, departamento));
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
