package br.com.fabioalvaro.pagamento.dominio;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "Pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pagamento {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String payer;

    @NotNull
    private String payee;

    @NotNull
    private BigDecimal amount;

    private String status;
    private String created;
    private String transacaoId;

}