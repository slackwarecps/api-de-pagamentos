package br.com.fabioalvaro.pagamento.controller.mapper;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.dto.PagamentoDTO;
import br.com.fabioalvaro.pagamento.dto.PagamentoRespostaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PagamentoDTOMapper {
    @Mapping(target = "status", constant = "PROCESSANDO")
    @Mapping(target = "transacaoId", expression = "java(generateUUID())")
    @Mapping(target = "created", expression = "java(generateCreate())")
    Pagamento pagamentoDtoToPagamento (PagamentoDTO dto);

    default String generateUUID() {
        return UUID.randomUUID().toString();
    }

    default String generateCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
