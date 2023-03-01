package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;

import java.math.BigDecimal;

public class ReajusteService {

    public void valorReajuste(Funcionario funcionario, Desempenho desempenho) {

        // Depois da refatoração
        BigDecimal reajuste = funcionario.getSalario().multiply(desempenho.percentualReajuste());
        funcionario.reajustarSalario(reajuste);

        // Antes da refatoração

        //        if (desempenho.equals(Desempenho.A_DESEJAR)) {
//            BigDecimal reajuste = funcionario.getSalario().multiply();
//            funcionario.reajustarSalario(reajuste);
//        }
//
//        if (desempenho.equals(Desempenho.BOM)) {
//            BigDecimal reajuste = funcionario.getSalario().multiply();
//            funcionario.reajustarSalario(reajuste);
//        }
//
//        if (desempenho.equals(Desempenho.OTIMO)) {
//            BigDecimal reajuste = funcionario.getSalario().multiply();
//            funcionario.reajustarSalario(reajuste);
//        }
    }
}
