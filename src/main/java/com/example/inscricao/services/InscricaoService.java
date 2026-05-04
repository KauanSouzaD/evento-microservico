package com.example.inscricao.services;

import com.example.inscricao.client.EventoClient;
import com.example.inscricao.client.dto.EventoResponseDTO;
import com.example.inscricao.domain.Inscricao;
import com.example.inscricao.dto.InscricaoRequestDTO;
import com.example.inscricao.dto.InscricaoResponseDTO;
import com.example.inscricao.mapper.InscricaoMapper;
import com.example.inscricao.repository.InscricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final InscricaoMapper inscricaoMapper;
    private final EventoClient eventoClient;

    public InscricaoResponseDTO inscrever(InscricaoRequestDTO dto) {
        EventoResponseDTO evento = eventoClient.buscarPorId(dto.eventoId());

        Inscricao inscricao = Inscricao.builder()
                .eventoId(dto.eventoId())
                .nomeParticipante(dto.nomeParticipante())
                .email(dto.email())
                .dataInscricao(LocalDateTime.now())
                .build();

        inscricao = inscricaoRepository.save(inscricao);

        return inscricaoMapper.toDTO(inscricao, evento.nome());
    }

    public InscricaoResponseDTO buscarPorId(Long id) throws Exception {
        Inscricao inscricao = buscarInscricaoPorId(id);

        EventoResponseDTO evento = eventoClient.buscarPorId(inscricao.getEventoId());

        return inscricaoMapper.toDTO(inscricao, evento.nome());
    }

    public List<InscricaoResponseDTO> buscarTodas() {
        return inscricaoRepository.findAll().stream()
                .map(inscricao -> {
                    EventoResponseDTO evento = eventoClient.buscarPorId(inscricao.getEventoId());
                    return inscricaoMapper.toDTO(inscricao, evento.nome());
                })
                .toList();
    }

    public List<InscricaoResponseDTO> buscarPorEvento(Long eventoId) {
        EventoResponseDTO evento = eventoClient.buscarPorId(eventoId);

        return inscricaoRepository.findByEventoId(eventoId).stream()
                .map(inscricao -> inscricaoMapper.toDTO(inscricao, evento.nome()))
                .toList();
    }

    public void cancelar(Long id) throws Exception {
        Inscricao inscricao = buscarInscricaoPorId(id);
        inscricaoRepository.delete(inscricao);
    }

    private Inscricao buscarInscricaoPorId(Long id) throws Exception {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new Exception("Inscrição não encontrada com id " + id));
    }
}
