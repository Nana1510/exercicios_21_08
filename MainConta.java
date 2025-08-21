public class MainConta {
    public static void main(String[] args) {
        // Criação de contas válidas
        ContaBancaria conta1 = new ContaBancaria("Ygor", "12345678", 500);
        ContaBancaria conta2 = new ContaBancaria("Nana", "87654321", 300);

        System.out.println("--- Contas criadas ---");
        System.out.println(conta1);
        System.out.println(conta2);

        // Depósito
        try {
            conta1.depositar(200);
            System.out.println("\nApós depósito de 200 na conta1:");
            System.out.println(conta1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Saque válido
        try {
            conta1.sacar(100);
            System.out.println("\nApós saque de 100 na conta1:");
            System.out.println(conta1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Transferência válida
        try {
            conta1.transferir(conta2, 150);
            System.out.println("\nApós transferir 150 da conta1 para conta2:");
            System.out.println(conta1);
            System.out.println(conta2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Testando falhas
        System.out.println("\n--- Testando falhas ---");

        try {
            conta1.depositar(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            conta1.sacar(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            conta1.transferir(conta2, -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Criação de conta inválida para testar validação
        try {
            new ContaBancaria("", "9999", 100); // só testar erro, não precisa salvar
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
