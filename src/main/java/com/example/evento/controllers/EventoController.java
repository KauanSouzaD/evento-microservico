package com.example.evento.controllers;

import com.example.evento.dto.EventoRequestDTO;
import com.example.evento.dto.EventoResponseDTO;
import com.example.evento.services.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(eventoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(eventoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> cadastrar(
            @RequestBody @Valid EventoRequestDTO eventoRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventoService.cadastrar(eventoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) throws Exception {
        eventoService.apagar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody @Valid EventoRequestDTO eventoRequestDTO) throws Exception {

        return ResponseEntity.ok(eventoService.editar(id, eventoRequestDTO));
    }
}
