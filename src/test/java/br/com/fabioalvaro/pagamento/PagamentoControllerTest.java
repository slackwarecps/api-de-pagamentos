package br.com.fabioalvaro.pagamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import br.com.fabioalvaro.JsonTestUtils;
import br.com.fabioalvaro.pagamento.controller.mapper.PagamentoDTOMapper;
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

    @Mock
    private PagamentoDTOMapper mapper;

    @InjectMocks
    private PagamentoController pagamentoController;

    private String MOCK_FOLDER_PAGAMENTO = "src/test/resources/json/mock/pagamento";

    public PagamentoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreatePagamentoSuccess() throws IOException {
        PagamentoDTO dto = JsonTestUtils.convertFileToObject(MOCK_FOLDER_PAGAMENTO, "/PagamentoDTOSucesso.json", PagamentoDTO.class);
        Pagamento pagamento = JsonTestUtils.convertFileToObject(MOCK_FOLDER_PAGAMENTO, "/PagamentoSucesso.json", Pagamento.class);
        when(pagamentoService.createPagamento(any(Pagamento.class))).thenReturn(pagamento);
        when(mapper.pagamentoDtoToPagamento(any(PagamentoDTO.class))).thenReturn(pagamento);

        ResponseEntity<PagamentoRespostaDTO> response = pagamentoController.createPagamento(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("PROCESSANDO", response.getBody().getStatus());
    }

    @Test
    public void testCreatePagamentoAboveLimit() throws IOException {
        System.out.println("Iniciando testes....");
        PagamentoDTO dto = JsonTestUtils.convertFileToObject(MOCK_FOLDER_PAGAMENTO, "/PagamentoDTOAboveLimit.json", PagamentoDTO.class);
        Pagamento pagamento = JsonTestUtils.convertFileToObject(MOCK_FOLDER_PAGAMENTO, "/PagamentoFalhaAboveLimit.json", Pagamento.class);

        when(pagamentoService.createPagamento(any(Pagamento.class))).thenReturn(pagamento);
        when(mapper.pagamentoDtoToPagamento(any(PagamentoDTO.class))).thenReturn(pagamento);
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