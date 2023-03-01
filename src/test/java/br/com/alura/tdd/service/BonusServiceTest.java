package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {


    @org.junit.jupiter.api.Test
    void bonusDeveSerZeroParaSalarioMaiorQueDezMil() {
    BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(30000)));

        Assert.assertEquals(BigDecimal.ZERO, bonus);
    }

    @org.junit.jupiter.api.Test
    void bonusDeveSerDezPorCentoParaSalarioIgualADezMil() {
        BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(10000)));
        Assert.assertEquals(new BigDecimal("1000.0"), bonus);
    }

    @org.junit.jupiter.api.Test
    void bonusDeveSerDezPorCentoDoSalarioParaSalarioMenorQueDezMil() {
        BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(2500)));
        Assert.assertEquals(new BigDecimal("250.0"), bonus);
    }
}