package br.com.alura.tdd;

import junit.framework.Assert;
import org.junit.Test;

public class CalculadoraTest {

    Calculadora calc = new Calculadora();

    @Test
    public void deveSomarDoisNumerosPositivos() {
        int soma = calc.soma(3, 7);

        Assert.assertEquals(10, soma);
    }

    @Test
    public void deveSomarUmNumeroPositivoEOutroNegativo() {
        int somaComNegativo = calc.soma(5, -5);

        Assert.assertEquals(0, somaComNegativo);
    }
}
