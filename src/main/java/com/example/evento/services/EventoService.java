package com.example.evento.services;

import com.example.evento.domain.Evento;
import com.example.evento.dto.EventoRequestDTO;
import com.example.evento.dto.EventoResponseDTO;
import com.example.evento.mapper.EventoMapper;
import com.example.evento.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

    public EventoResponseDTO cadastrar(EventoRequestDTO eventoRequestDTO) {
        Evento evento = eventoMapper.toEntity(eventoRequestDTO);

        evento = eventoRepository.save(evento);

        return eventoMapper.toDTO(evento);
    }

    public EventoResponseDTO buscarPorId(Long id) throws Exception {
        Evento evento = buscarEventoPorId(id);

        return eventoMapper.toDTO(evento);
    }

    public List<EventoResponseDTO> buscarTodos() {
        List<Evento> eventos = eventoRepository.findAll();

        return eventos.stream().map(eventoMapper::toDTO).toList();
    }

    public void apagar(Long id) throws Exception {
        Evento produto = buscarEventoPorId(id);
        eventoRepository.delete(produto);
    }

    public EventoResponseDTO editar(Long id, EventoRequestDTO eventoRequestDTO) throws Exception {
        Evento evento = buscarEventoPorId(id);

        evento.setNome(eventoRequestDTO.nome());
        evento.setDescricao(eventoRequestDTO.descricao());
        evento.setLocal(eventoRequestDTO.local());
        evento.setData(eventoRequestDTO.data());
        evento.setPreco(eventoRequestDTO.preco());

        evento = eventoRepository.save(evento);

        return eventoMapper.toDTO(evento);
    }

    private Evento buscarEventoPorId(Long id) throws Exception {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new Exception("Evento não encontrado com id " + id));
    }

}
