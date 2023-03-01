package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReajusteServiceTest {

    @Test
    void reajusteDeveSerDeTresPorCentoQuandoDesempenhoForADesejar() {
        ReajusteService reajusteService = new ReajusteService();
        Funcionario funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));

        reajusteService.valorReajuste(funcionario, Desempenho.A_DESEJAR);

        Assert.assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
    }
}