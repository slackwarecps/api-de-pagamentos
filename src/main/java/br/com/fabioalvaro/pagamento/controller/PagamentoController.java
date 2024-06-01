package br.com.fabioalvaro.pagamento.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.dto.PagamentoDTO;
import br.com.fabioalvaro.pagamento.dto.PagamentoRespostaDTO;
import br.com.fabioalvaro.pagamento.service.PagamentoService;

@RestController
@RequestMapping("/api/v1/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService PagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoRespostaDTO> createPagamento(@Validated @RequestBody PagamentoDTO dto) {
        String codigoUUID = UUID.randomUUID().toString();
        LocalDateTime created = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createdAsString = created.format(formatter);
        Pagamento createdPagamento = PagamentoService.createPagamento(dto.transformaParaObjeto());
        createdPagamento.setTransacaoId(codigoUUID);
        createdPagamento.setCreated(createdAsString);
        createdPagamento.setStatus("PROCESSANDO");

        // return
        // ResponseEntity.ok(PagamentoRespostaDTO.transformaEmDTO(createdPagamento));
        return new ResponseEntity<>(PagamentoRespostaDTO.transformaEmRespostaDTO(createdPagamento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoRespostaDTO>> getAllPagamentos() {
        List<Pagamento> lista_pagamentos = PagamentoService.getAllPagamentos();
        List<PagamentoRespostaDTO> lista_pagamentos_dto = new ArrayList<PagamentoRespostaDTO>();
        for (Pagamento pagamento : lista_pagamentos) {
            PagamentoRespostaDTO resposta_dto = PagamentoRespostaDTO.transformaEmRespostaDTO(pagamento);

            lista_pagamentos_dto.add(resposta_dto);
        }

        return ResponseEntity.ok(lista_pagamentos_dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoRespostaDTO> getPagamentoById(@PathVariable Long id) {
        Pagamento Pagamento = PagamentoService.getPagamentoById(id);
        if (Pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PagamentoRespostaDTO.transformaEmRespostaDTO(Pagamento));
    }
}