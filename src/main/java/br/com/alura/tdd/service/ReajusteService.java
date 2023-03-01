package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;

import java.math.BigDecimal;

public class ReajusteService {

    public void valorReajuste(Funcionario funcionario, Desempenho desempenho) {
        BigDecimal reajuste = funcionario.getSalario().multiply(desempenho.percentualReajuste());
        funcionario.reajustarSalario(reajuste);
    }
}
