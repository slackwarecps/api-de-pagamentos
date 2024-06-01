package br.com.fabioalvaro.pagamento.dto;

import java.math.BigDecimal;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PagamentoRespostaDTO {

    private Long id;
    private String payer;
    private String payee;
    private BigDecimal amount;
    private String status;
    private String created;
    private String transacaoId;

    public static PagamentoRespostaDTO transformaEmRespostaDTO(Pagamento pagamento) {
        return new PagamentoRespostaDTO(pagamento.getId(), pagamento.getPayer(), pagamento.getPayee(),
                pagamento.getAmount(), pagamento.getStatus(), pagamento.getCreated(), pagamento.getTransacaoId());
    }
}