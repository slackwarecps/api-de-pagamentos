package br.com.fabioalvaro.pagamento.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository PagamentoRepository;

    

    public Pagamento createPagamento(Pagamento Pagamento) {
        return PagamentoRepository.save(Pagamento);
    }

    public List<Pagamento> getAllPagamentos() {
        return PagamentoRepository.findAll();
    }

    public Pagamento getPagamentoById(Long id) {
        return PagamentoRepository.findById(id).orElse(null);
    }


 

}