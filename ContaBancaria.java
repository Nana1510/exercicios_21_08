public class ContaBancaria {
    private String titular;
    private String numero;
    private double saldo;

    // Construtor com validação
    public ContaBancaria(String titular, String numero, double saldoInicial) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("Titular não pode ser nulo ou vazio.");
        }
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
        this.titular = titular;
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    // Métodos públicos
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }
        saldo -= valor;
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino inválida.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
        }
        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para transferência.");
        }

        // operação atômica: só conclui se for possível sacar e depositar
        this.sacar(valor);
        destino.depositar(valor);
    }

    // Getters (sem setSaldo!)
    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setter para titular com validação
    public void setTitular(String novoTitular) {
        if (novoTitular == null || novoTitular.isBlank()) {
            throw new IllegalArgumentException("Novo titular não pode ser nulo ou vazio.");
        }
        this.titular = novoTitular;
    }

    // toString com número mascarado
    @Override
    public String toString() {
        String numeroMascarado = numero.length() > 4
                ? "***" + numero.substring(numero.length() - 4)
                : numero;
        return "Conta [Titular: " + titular +
                ", Número: " + numeroMascarado +
                ", Saldo: R$ " + String.format("%.2f", saldo) + "]";
    }
}