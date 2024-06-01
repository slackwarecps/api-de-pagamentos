package br.com.fabioalvaro.pagamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT p FROM Pagamento p WHERE p.status = :status")
    List<Pagamento> findByStatus(@Param("status") String status);
}