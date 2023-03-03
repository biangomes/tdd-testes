package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReajusteServiceTest {


    // Minha refatoracao
    // ReajusteService reajusteService = new ReajusteService();
    // Funcionario funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));

    // Refatoracao da aula
    private ReajusteService reajusteService;
    private Funcionario funcionario;

    @BeforeEach
    public void inicializar() {
        System.out.println("Método inicializar com a anotação @BeforeEach");
        this.reajusteService = new ReajusteService();
        this.funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));
    }

    // Annotation @AfterEach
    @AfterEach
    public void finalizar() {
        System.out.println("Método finalizar com a anotação @AfterEach");
    }

    @BeforeAll
    public static void antesDeTodos() {
        System.out.println("=====INICIO=====");
    }

    @AfterAll
    public static void depoisDeTodos() {
        System.out.println("=====FIM=====");
    }

    @Test
    void reajusteDeveSerDeTresPorCentoQuandoDesempenhoForADesejar() {
//        ReajusteService reajusteService = new ReajusteService();
//        Funcionario funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));

        reajusteService.valorReajuste(funcionario, Desempenho.A_DESEJAR);

        Assert.assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
    }


    @Test
    void reajusteDeveSerQuinzePorCentoQuandoDesempenhoForBom() {
//        ReajusteService reajusteService = new ReajusteService();
//        Funcionario funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));

        reajusteService.valorReajuste(funcionario, Desempenho.BOM);

        Assert.assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
    }


    @Test
    void reajusteDeveSerVintePorCentoQuandoDesempenhoForOtimo() {
//        ReajusteService reajusteService = new ReajusteService();
//        Funcionario funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));

        reajusteService.valorReajuste(funcionario, Desempenho.OTIMO);

        Assert.assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
    }
}
