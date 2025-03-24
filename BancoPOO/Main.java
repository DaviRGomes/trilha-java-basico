import model.Cliente;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.IConta;
import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;

public class Main {
    public static void main(String[] args) {
        try {
            Cliente cliente1 = new Cliente("João Silva", "111.222.333-44");
            Cliente cliente2 = new Cliente("Maria Souza", "555.666.777-88");

            IConta corrente = new ContaCorrente(cliente1, 1000.0);
            IConta poupanca = new ContaPoupanca(cliente2, 0.005);

            corrente.depositar(1500.0);
            poupanca.depositar(1000.0);

            corrente.sacar(200.0);
            corrente.transferir(300.0, poupanca);

            ((ContaPoupanca)poupanca).aplicarRendimento();

            corrente.imprimirExtrato();
            System.out.println();
            poupanca.imprimirExtrato();

            corrente.sacar(5000.0);  // Valor maior que saldo + cheque especial
            poupanca.sacar(2000.0);  // Valor maior que saldo

        } catch (SaldoInsuficienteException e) {
            System.err.println("Erro na operação: " + e.getMessage());
        } catch (ValorInvalidoException e) {
            System.err.println("Erro no valor: " + e.getMessage());
        }
    }
}