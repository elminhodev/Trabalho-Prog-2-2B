package com;

public class Posto {
        private double estoque;
    private double precoLitro;

    public Posto() {
        this.estoque = 0.0;
        this.precoLitro = 5.0;
    }

    public double getEstoque() {
        return estoque;
    }

    public boolean adicionar(double quantidade) {
        if (quantidade > 0) {
            estoque += quantidade;
            return true;
        }
        return false;
    }

    public String abastecerPorLitros(double litros) {
        if (litros <= 0) return "Quantidade inválida.";
        if (litros > estoque) return "Estoque insuficiente.";
        estoque -= litros;
        return String.format("Abastecido %.2f litros.", litros);
    }

    public String venderPorValor(double valor) {
        if (valor <= 0) return "Valor inválido.";
        double litros = valor / precoLitro;
        if (litros > estoque) return "Estoque insuficiente.";
        estoque -= litros;
        return String.format("Abastecido %.2f litros com R$ %.2f.", litros, valor);
    }
}
