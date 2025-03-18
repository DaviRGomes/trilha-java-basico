import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criação do objeto Scanner para leitura dos dados via terminal
        Scanner scanner = new Scanner(System.in);

        // Solicitação e leitura do número da agência
        System.out.println("Por favor, digite o número da Agência:");
        String agencia = scanner.nextLine();

        // Solicitação e leitura do número da conta
        System.out.println("Por favor, digite o número da Conta:");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()

        // Solicitação e leitura do nome do cliente
        System.out.println("Por favor, digite o nome do Cliente:");
        String nomeCliente = scanner.nextLine();

        // Solicitação e leitura do saldo da conta
        System.out.println("Por favor, digite o saldo da Conta:");
        double saldo = scanner.nextDouble();

        // Exibição da mensagem final com os dados inseridos
        System.out.println("Olá " + nomeCliente + ", obrigado por criar uma conta em nosso banco, sua agência é " + agencia + ", conta " + numero + " e seu saldo " + saldo + " já está disponível para saque.");

        // Fechamento do objeto Scanner
        scanner.close();
    }
}