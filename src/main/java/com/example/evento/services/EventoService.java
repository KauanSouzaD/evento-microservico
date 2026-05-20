package com.example.evento.services;

import com.example.evento.domain.Evento;
import com.example.evento.exceptions.RecursoNaoEncontradoException;
import com.example.evento.dto.EventoRequestDTO;
import com.example.evento.dto.EventoResponseDTO;
import com.example.evento.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoResponseDTO cadastrar(EventoRequestDTO eventoRequestDTO) {
        Evento evento = new Evento();

        evento.setNome(eventoRequestDTO.nome());
        evento.setDescricao(eventoRequestDTO.descricao());
        evento.setLocal(eventoRequestDTO.local());
        evento.setData(eventoRequestDTO.data());
        evento.setPreco(eventoRequestDTO.preco());

        evento = eventoRepository.save(evento);

        return converterParaDTO(evento);
    }

    public EventoResponseDTO buscarPorId(Long id) {
        Evento evento = buscarEventoPorId(id);
        return converterParaDTO(evento);
    }

    public List<EventoResponseDTO> buscarTodos() {
        List<Evento> eventos = eventoRepository.findAll();

        return eventos.stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public void apagar(Long id) {
        Evento evento = buscarEventoPorId(id);
        eventoRepository.delete(evento);
    }

    public EventoResponseDTO editar(Long id, EventoRequestDTO eventoRequestDTO) {
        Evento evento = buscarEventoPorId(id);

        evento.setNome(eventoRequestDTO.nome());
        evento.setDescricao(eventoRequestDTO.descricao());
        evento.setLocal(eventoRequestDTO.local());
        evento.setData(eventoRequestDTO.data());
        evento.setPreco(eventoRequestDTO.preco());

        evento = eventoRepository.save(evento);

        return converterParaDTO(evento);
    }

    private Evento buscarEventoPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Evento não encontrado com id " + id));
    }

    private EventoResponseDTO converterParaDTO(Evento evento) {

        boolean gratuito =
                evento.getPreco().compareTo(BigDecimal.ZERO) == 0;

        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getLocal(),
                evento.getData(),
                evento.getPreco(),
                gratuito
        );
    }
}