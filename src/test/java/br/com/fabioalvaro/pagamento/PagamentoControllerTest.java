package br.com.fabioalvaro.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.fabioalvaro.pagamento.controller.PagamentoController;
import br.com.fabioalvaro.pagamento.dominio.FormaPagamento;
import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import br.com.fabioalvaro.pagamento.dto.PagamentoDTO;
import br.com.fabioalvaro.pagamento.dto.PagamentoRespostaDTO;
import br.com.fabioalvaro.pagamento.service.PagamentoService;

@DataJpaTest
@ActiveProfiles("test")
public class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    public PagamentoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("null")
    @Test
    public void testCreatePagamentoSuccess() {
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(123L);
        dto.setPayer("Fabio");
        dto.setPayee("Tatiana");
        dto.setAmount(new BigDecimal("125.98"));
        dto.setCallback("http://localhost:8080/nada");
        dto.setFormaPagamento(FormaPagamento.PIX);

        Pagamento pagamento = dto.transformaParaObjeto();
        pagamento.setId(1L);
        pagamento.setStatus("PROCESSANDO");
        pagamento.setTransacaoId("f38496c6-17bd-448f-9f41-864a89d51443");
        pagamento.setCreated("2024-06-01 06:12:50");

        when(pagamentoService.createPagamento(any(Pagamento.class))).thenReturn(pagamento);

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.createPagamento(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("PROCESSANDO", response.getBody().getStatus());
    }

    @Test
    public void testCreatePagamentoAboveLimit() {
        System.out.println("Iniciando testes....");
        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(123L);
        dto.setPayer("Fabio");
        dto.setPayee("Tatiana");
        dto.setAmount(new BigDecimal("250.00"));
        dto.setCallback("http://localhost:8080/nada");
        dto.setFormaPagamento(FormaPagamento.PIX);

        Pagamento pagamento = dto.transformaParaObjeto();
        pagamento.setId(1L);
        pagamento.setStatus("RECUSADO");

        pagamento.setTransacaoId("f38496c6-17bd-448f-9f41-864a89d51443");
        pagamento.setCreated("2024-06-01 06:12:50");

        when(pagamentoService.createPagamento(any(Pagamento.class))).thenReturn(pagamento);

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.createPagamento(dto);
        System.out.println("==============================");
        System.out.println(pagamento);
        System.out.println("==============================");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("RECUSADO", response.getBody().getStatus());

    }

    @Test
    public void testGetPagamentoByIdSuccess() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setPayer("Alice");
        pagamento.setPayee("Bob");
        pagamento.setAmount(new BigDecimal("100.00"));
        pagamento.setStatus("CONCLUIDA");
        pagamento.setCreated("2024-06-01 08:34:40.331697");
        pagamento.setTransacaoId("TX123");
        pagamento.setCallback("https://open-bingo.wiremockapi.cloud/pagbank/notificacao");
        pagamento.setFormaPagamento(FormaPagamento.SALDO);

        when(pagamentoService.getPagamentoById(1L)).thenReturn(pagamento);

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.getPagamentoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CONCLUIDA", response.getBody().getStatus());
    }

    @Test
    public void testGetPagamentoByIdNotFound() {
        when(pagamentoService.getPagamentoById(1L)).thenReturn(null);

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.getPagamentoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetPagamentoByTransacaoIdSuccess() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setPayer("Alice");
        pagamento.setPayee("Bob");
        pagamento.setAmount(new BigDecimal("100.00"));
        pagamento.setStatus("CONCLUIDA");
        pagamento.setCreated("2024-06-01 08:34:40.331697");
        pagamento.setTransacaoId("TX123");
        pagamento.setCallback("https://open-bingo.wiremockapi.cloud/pagbank/notificacao");
        pagamento.setFormaPagamento(FormaPagamento.SALDO);

        when(pagamentoService.getPagamentoByTransacaoId("TX123")).thenReturn(Optional.of(pagamento));

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.getPagamentoByTransacaoId("TX123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CONCLUIDA", response.getBody().getStatus());
    }

    @Test
    public void testGetPagamentoByTransacaoIdNotFound() {
        when(pagamentoService.getPagamentoByTransacaoId("TX123")).thenReturn(Optional.empty());

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.getPagamentoByTransacaoId("TX123");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}