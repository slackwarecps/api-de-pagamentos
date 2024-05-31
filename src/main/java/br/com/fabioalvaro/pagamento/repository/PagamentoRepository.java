package br.com.fabioalvaro.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}