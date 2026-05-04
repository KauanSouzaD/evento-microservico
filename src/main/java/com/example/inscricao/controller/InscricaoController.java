package com.example.inscricao.controller;

import com.example.inscricao.dto.InscricaoRequestDTO;
import com.example.inscricao.dto.InscricaoResponseDTO;
import com.example.inscricao.services.InscricaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> inscrever(
            @RequestBody @Valid InscricaoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inscricaoService.inscrever(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoResponseDTO> buscarPorId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(inscricaoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<InscricaoResponseDTO>> buscarTodas() {
        return ResponseEntity.ok(inscricaoService.buscarTodas());
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoResponseDTO>> buscarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(inscricaoService.buscarPorEvento(eventoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) throws Exception {
        inscricaoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}