package com.example.inscricao.mapper;

import com.example.inscricao.domain.Inscricao;
import com.example.inscricao.dto.InscricaoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InscricaoMapper {

    @Mapping(target = "nomeEvento", source = "nomeEvento")
    @Mapping(target = "id", source = "inscricao.id")
    @Mapping(target = "eventoId", source = "inscricao.eventoId")
    @Mapping(target = "nomeParticipante", source = "inscricao.nomeParticipante")
    @Mapping(target = "email", source = "inscricao.email")
    @Mapping(target = "dataInscricao", source = "inscricao.dataInscricao")
    InscricaoResponseDTO toDTO(Inscricao inscricao, String nomeEvento);
}