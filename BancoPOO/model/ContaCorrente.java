package model;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    public ContaCorrente(Cliente cliente, double chequeEspecial) {
        super(cliente);
        this.chequeEspecial = chequeEspecial;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de saque deve ser positivo");
        }

        if (valor > (saldo + chequeEspecial)) {
            throw new SaldoInsuficienteException("Saldo + cheque especial insuficiente");
        }

        saldo -= valor;
        System.out.println("Saque realizado (incluindo cheque especial): " + valor);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
        System.out.println("Cheque Especial: " + this.chequeEspecial);
        System.out.println("Saldo Disponível: " + (this.saldo + this.chequeEspecial));
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }
}