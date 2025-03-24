package model;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de saque deve ser positivo");
        }

        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        saldo -= valor;
        System.out.println("Saque realizado: " + valor);
    }

    @Override
    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de depósito deve ser positivo");
        }

        saldo += valor;
        System.out.println("Depósito realizado: " + valor);
    }

    @Override
    public void transferir(double valor, IConta destino) throws SaldoInsuficienteException, ValorInvalidoException {
        this.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência realizada: " + valor);
    }

    protected void imprimirInfosComuns() {
        System.out.println("Titular: " + this.cliente.getNome());
        System.out.println("Agência: " + this.agencia);
        System.out.println("Número: " + this.numero);
        System.out.println("Saldo: " + this.saldo);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
}