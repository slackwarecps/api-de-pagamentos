package br.com.fabioalvaro.pagamento.controller;

import java.util.ArrayList;
import java.util.List;

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
        Pagamento createdPagamento = PagamentoService.createPagamento(dto.transformaParaObjeto());
        // return
        // ResponseEntity.ok(PagamentoRespostaDTO.transformaEmDTO(createdPagamento));
        return new ResponseEntity<>(PagamentoRespostaDTO.transformaEmDTO(createdPagamento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAllPagamentos() {
        List<Pagamento> lista_pagamentos = PagamentoService.getAllPagamentos();
        List<PagamentoDTO> lista_pagamentos_dto = new ArrayList<PagamentoDTO>();
        for (Pagamento pagamento : lista_pagamentos) {
            PagamentoDTO dto = new PagamentoDTO();
            dto.setId(pagamento.getId());
            dto.setPayer(pagamento.getPayer());
            dto.setPayee(pagamento.getPayee());
            lista_pagamentos_dto.add(dto);
        }

        return ResponseEntity.ok(lista_pagamentos_dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Long id) {
        Pagamento Pagamento = PagamentoService.getPagamentoById(id);
        if (Pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Pagamento);
    }
}