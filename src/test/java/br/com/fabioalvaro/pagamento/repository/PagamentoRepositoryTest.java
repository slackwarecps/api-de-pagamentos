package br.com.fabioalvaro.pagamento.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.dto.PagamentoDTO;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class PagamentoRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void testFindAllByTransacaoId() {

    }

    @Test
    void testFindByStatus() {

    }

    @Test
    void testprocuraporTransacaoID() {

    }

    private Pagamento criaPagamento(PagamentoDTO dto) {
        Pagamento novoPagamento = dto.transformaParaObjeto();

        this.entityManager.persist(novoPagamento);
        return novoPagamento;

    }
}
