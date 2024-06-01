package br.com.fabioalvaro.pagamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento createPagamento(Pagamento Pagamento) {
        return pagamentoRepository.save(Pagamento);
    }

    public Pagamento updatePagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento); // Salva a alteração no banco de dados
    }

    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento getPagamentoById(Long id) {
        return pagamentoRepository.findById(id).orElse(null);
    }

    public void printPendingPayments() {
        List<Pagamento> pendingPayments = pagamentoRepository.findByStatus("PROCESSANDO");
        for (Pagamento pagamento : pendingPayments) {
            System.out.println("Transacao ID: " + pagamento.getTransacaoId());
        }
    }

    public List<Pagamento> getPendingPayments() {
        List<Pagamento> pendingPayments = pagamentoRepository.findByStatus("PROCESSANDO");
        return pendingPayments;
    }

    public Optional<Pagamento> getPagamentoByTransacaoId(String transacaoId) {
        return pagamentoRepository.findByTransacaoId(transacaoId);
    }

}