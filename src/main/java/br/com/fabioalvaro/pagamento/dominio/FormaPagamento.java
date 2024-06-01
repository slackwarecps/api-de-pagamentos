package br.com.fabioalvaro.pagamento.dominio;

public enum FormaPagamento {
    PIX(1),
    CARTAO_CREDITO(2),
    SALDO(3);

    private final int codigo;

    FormaPagamento(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static FormaPagamento fromCodigo(int codigo) {
        for (FormaPagamento forma : FormaPagamento.values()) {
            if (forma.getCodigo() == codigo) {
                return forma;
            }
        }
        throw new IllegalArgumentException("Código de forma de pagamento inválido: " + codigo);
    }
}