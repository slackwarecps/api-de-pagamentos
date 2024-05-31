package br.com.fabioalvaro.pagamento.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.fabioalvaro.pagamento.dominio.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String payer;

    private String payee;

    private BigDecimal amount;

    public Pagamento transformaParaObjeto() {
        return new Pagamento(id, payer, payee, amount, null, null, null);
    }
}