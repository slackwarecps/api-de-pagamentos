package br.com.fabioalvaro.pagamento.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fabioalvaro.pagamento.controller.mapper.PagamentoDTOMapper;
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
import br.com.fabioalvaro.pagamento.service.NotificationService;
import br.com.fabioalvaro.pagamento.service.PagamentoService;

@RestController
@RequestMapping("/api/v1/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PagamentoDTOMapper mapper;

    @PostMapping
    public ResponseEntity<PagamentoRespostaDTO> createPagamento(@Validated @RequestBody PagamentoDTO dto) {
        Pagamento createdPagamento = pagamentoService.createPagamento(mapper.pagamentoDtoToPagamento(dto));
        return new ResponseEntity<>(PagamentoRespostaDTO.transformaEmRespostaDTO(createdPagamento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoRespostaDTO>> getAllPagamentos() {
        List<Pagamento> lista_pagamentos = pagamentoService.getAllPagamentos();
        List<PagamentoRespostaDTO> lista_pagamentos_dto = new ArrayList<PagamentoRespostaDTO>();
        for (Pagamento pagamento : lista_pagamentos) {
            PagamentoRespostaDTO resposta_dto = PagamentoRespostaDTO.transformaEmRespostaDTO(pagamento);

            lista_pagamentos_dto.add(resposta_dto);
        }

        return ResponseEntity.ok(lista_pagamentos_dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoRespostaDTO> getPagamentoById(@PathVariable Long id) {

        Pagamento Pagamento = pagamentoService.getPagamentoById(id);
        if (Pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PagamentoRespostaDTO.transformaEmRespostaDTO(Pagamento));
    }

    @GetMapping("/transacao/{transacaoId}")
    public ResponseEntity<PagamentoRespostaDTO> getPagamentoByTransacaoId(@PathVariable String transacaoId) {
        Optional<Pagamento> pagamento = pagamentoService.getPagamentoByTransacaoId(transacaoId);
        if (pagamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PagamentoRespostaDTO.transformaEmRespostaDTO(pagamento.get()));
    }
}