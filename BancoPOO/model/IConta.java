package model;
import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;
public interface IConta {
    void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException;
    void depositar(double valor) throws ValorInvalidoException;
    void transferir(double valor, IConta destino) throws SaldoInsuficienteException, ValorInvalidoException;
    void imprimirExtrato();
}