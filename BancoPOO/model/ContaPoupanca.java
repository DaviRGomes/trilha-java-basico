package model;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(Cliente cliente, double taxaRendimento) {
        super(cliente);
        this.taxaRendimento = taxaRendimento;
    }

    public void aplicarRendimento() {
        double rendimento = saldo * taxaRendimento;
        saldo += rendimento;
        System.out.println("Rendimento aplicado: " + rendimento);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        super.imprimirInfosComuns();
        System.out.println("Taxa de Rendimento: " + this.taxaRendimento);
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }
}