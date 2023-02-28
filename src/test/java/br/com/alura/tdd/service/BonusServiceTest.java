package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    @Test
    void bonusDeveSerZeroParaSalarioMaiorQueMil() {
        BonusService bonusService = new BonusService();
        BigDecimal bonus = bonusService.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(30000)));

        Assert.assertEquals(BigDecimal.ZERO, bonus);
    }
}