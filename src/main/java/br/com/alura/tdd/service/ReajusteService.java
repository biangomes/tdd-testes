package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;

import java.math.BigDecimal;

public class ReajusteService {

    public void valorReajuste(Funcionario funcionario, Desempenho desempenho) {
        if (desempenho.equals(Desempenho.A_DESEJAR)) {
            BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.03"));
            funcionario.reajustarSalario(reajuste);
        }

        if (desempenho.equals(Desempenho.BOM)) {
            BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.15"));
            funcionario.reajustarSalario(reajuste);
        }
    }
}
