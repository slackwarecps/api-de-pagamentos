package br.com.fabioalvaro.pagamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT p FROM Pagamento p WHERE p.status = :status")
    List<Pagamento> findByStatus(@Param("status") String status);

    // Buscar todos filtrando por transacao
    @Query("SELECT p FROM Pagamento p WHERE p.transacaoId = :transacaoId")
    List<Pagamento> findAllByTransacaoId(@Param("transacaoId") String transacaoId);

    // Buscar Pagamento filtrando por transacao
    @Query("SELECT p FROM Pagamento p WHERE p.transacaoId = :transacaoId")
    Optional<Pagamento> findByTransacaoId(@Param("transacaoId") String transacaoId);
}