# Curso TDD e Java: testes automatizados com JUnit

### Aula 02: JUnit

É uma boa prática colocar o sufixo `Test` depois do nome da classe que se quer testar.

Foi criado uma classe chamada `Calculadora` com o método `soma()`. Depois, criou-se a classe `CalculadoraTest`.

Para utilizar o `JUnit` pra fazer testes unitários, basta colocar a annotation `@Test`.

Para verificar uma assertiva, ou seja, se é de fato o valor correto, utilizamos a classe `Assert` com o método `assertEquals()`.

No nosso caso, a classe `CalculadoraTest` ficou da seguinte maneira:

```java
package br.com.alura.tdd;

import junit.framework.Assert;
import org.junit.Test;

public class CalculadoraTest {

    @Test
    public void deveSomarDoisNumerosPositivos() {
        Calculadora calc = new Calculadora();
        int soma = calc.soma(3, 7);

        Assert.assertEquals(10, soma);
    }
}
```

---

Agora será trabalhado as classes `Funcionario` dentro de `.../modelo/` e `BonusService` dentro de `.../service/`.

Para criar testes no IntelliJ de forma simplificada, basta clicar com o botão direito ao lado do método alvo - ou pressionar Alt + Insert - e escolher a opção de `Test`.
Já será criado um teste dentro do `src/test/java/nome_do_pacote/`.

### Aula 03. TDD: Test-Driven Development

Nesta abordagem, o fluxo de desenvolvimento será: Testes -> Implementação -> Refatoração. Este último retorna para Testes. Essa abordagem se chama **_Test-Driven Development_**.

### Funcionalidade (objetivo: implementar TDD)

#### Reajuste baseado no desempenho anual

O sistema deve permitir que os funcionários recebam um reajuste salarial anual baseado em seu desempenho, obedecendo as seguintes regras:

- Se o desempenho foi "A desejar", reajuste será de apenas 3% do salário;
- Se o desempenho foi "Bom", reajuste será de 15% do salário;
- Se o desempenho foi "Ótimo", reajuste será de 20% do salário.

Agora, no tópico 04, é visto **refatoração**. Esta nada mais é do que modificar o código, sem alterar o comportamento.

Como o único parâmetro que influencia a estratégia de reajuste, é o desempenho, podemos removar aquela quantidade de `if ... else` e criar um método abstrato, direto no enum (`Desempenho`).

Foi visto como realizar teste unitário de uma `Exception`. Existem duas implementações a priori, em que uma é utilizando o bloco `try ... catch`
e a outra é utilizando o `assertThrows` junto à uma função lambda.

```java
    @org.junit.jupiter.api.Test
    void bonusDeveSerZeroParaSalarioMaiorQueDezMil() {
        BonusService bonusService = new BonusService();
        //BigDecimal bonus = bonusService.calcularBonus(new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(30000)));
//        assertThrows(IllegalArgumentException.class, () -> bonusService.calcularBonus(
//                new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(30000))
//        ));

        // outra implementacao
        try {
            bonusService.calcularBonus(new Funcionario("beatriz", LocalDate.now(), new BigDecimal(30000.00)));
            fail("nao deu exception");
        } catch (Exception e) {

        }
```
Abordagem `try ... catch` acima.

Abordagem usando `assertThrows` abaixo.
```java
    @org.junit.jupiter.api.Test
    void bonusDeveSerZeroParaSalarioMaiorQueDezMil() {
        BonusService bonusService = new BonusService();
        //BigDecimal bonus = bonusService.calcularBonus(new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(30000)));
        assertThrows(IllegalArgumentException.class, () -> bonusService.calcularBonus(
                new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(30000))
        ));

        // outra implementacao
//        try {
//            bonusService.calcularBonus(new Funcionario("beatriz", LocalDate.now(), new BigDecimal(30000.00)));
//            fail("nao deu exception");
//        } catch (Exception e) {
//
//        }
    }
```
---

### Aula 05. Mais recursos

A refatoração, caso seja necessária, deve ser realizada nos testes também.

Ao olhar a classe `ReajusteServiceTest`, é visto que cada teste instancia um objeto do tipo `ReajusteService` e `Funcionario`. Então, a primeira refatoração é passar para um escopo "global".

Eu pensei em fazer isso passando para o construtor, ficando da forma abaixo:

```java
    // Refatoracao da aula
    private ReajusteService reajusteService;
    private Funcionario funcionario;

    public ReajusteServiceTest() {
        this.reajusteService = new ReajusteService();
        this.funcionario = new Funcionario("Beatriz", LocalDate.now(), new BigDecimal(1000));
    }
```

Porém, além de o professor ter feito de outra forma, ao procurar no fórum do curso encontrei essa question:

> [Dúvida] Poderia executar o método inicializar no construtor? 
> Estava assistindo o primeiro vídeo da aula 5 e o professor indicou utilizar o Annotation @BeforeEach. Mas pensei comigo mesmo que poderiamos inicializar os atributor no construtor também. Esta seria uma opção viavel?  Obrigado

E então foi dito pelo instrutor, que ao utilizar a annotation `@BeforeEach` é garantido que cada teste seja rodado de forma independente.

O `@BeforeEach` diz que: 
> *Antes* de **cada** teste, execute _isso_.

Existe também a annotation `@AfterEach` que diz:
> *Depois* de **cada** teste, execute _isso_.

Também existe as annotations `@BeforeAll` e `@AfterAll`, que diferencia das anteriores porque estas são antes e depois do início da aplicação como um todo, respectivamente. Ou seja, executa apenas uma vez.


Exemplo de aplicações das annotations acima:

- Inicializar conexão para cada teste;
- Finalizar conexão para cada teste.

Métodos privados **não** são testados diretamente. No tópico "4. Como testar métodos privados?" foi criado um método privado chamado `arredondarSalario`:

```java
	private void arredondarSalario() {
		this.salario = this.salario.setScale(2, RoundingMode.HALF_UP);		// arredonda o valor para cima
	}
```

E no método `reajustarSalario` invocamos ele:

```java
	public void reajustarSalario(BigDecimal reajuste) {
		this.salario = this.salario.add(reajuste);
		arredondarSalario();
	}
```

Ocorre que não testamos o método privado, mas quando o invocamos em um método que é público (`reajustarSalario`), ele mesmo já testa. Ou seja, não o testamos separadamente.

#### Tópico: 05. O que testar na aplicação?

Os métodos `getters` e `setters` não precisam ser testados, pois são padrões, **não existem regras de negócio neles**.

Só é testado aquilo que tem:

- Regras de negócio;
- Algoritmos;
- Validação;
- Coisas que tendem a **mudar** no futuro.

### Nota de fim de curso (ctrl c, ctrl v)

Chegamos ao fim do nosso treinamento!

Aqui, aprendemos a fazer testes automatizados e a usar o JUnit.

Usamos uma aplicação que simula um sistema de folha de pagamento de Recursos Humanos com a classe BonusService, que continha uma regra de negócio importante para essa aplicação.

Descobrimos como testar essa classe construindo um código de teste depois que o BonusService já foi implementado. Nessa etapa, aprendemos a:

-    Adicionar o JUnit ao projeto
-    Usar anotações de teste (@Test)
-    Fazer assertivas (asserts)

Também vimos que é possível começar o processo pela escrita do teste com a abordagem TDD (Desenvolvimento Guiado por Teste). Nela, percebemos que o teste serve de rascunho para modelarmos o design do nosso código e depois partir para a implementação. Nessa etapa, também aprendemos a lidar com as exceptions e verificá-las.

Fizemos uma implementação cheia de ifs e elses e depois usamos o padrão de projeto strategy para fazer uma refatoração desse código. Depois disso, apresentamos algumas boas práticas, como refatorar o código do teste, e usamos alguns recursos do JUnit como @AfterEach e o @BeforeEach para simplificar o código. Por fim, discutimos quais códigos devem ou não ser testados numa aplicação.

E esses foram os objetivos do nosso treinamento! Se você já trabalha com testes automatizados, pode ser que já conheça os temas abordados aqui, mas a ideia era mostrar os testes automatizados para quem nunca trabalhou com eles ou está começando a escrevê-los, especialmente com a biblioteca JUnit na linguagem Java.

Aqui na Alura temos outros treinamentos para você dar sequência no seu aprendizado. Neste curso, focamos nos testes de unidade, mas temos cursos que abordam os testes de integração, que integram dois sistemas distintos, os de aceitação, que simulam o comportamento do usuário e diversos outros cursos que podem expandir o seu conhecimento.

Por isso, recomendamos que você faça esses cursos para complementar seus estudos e aprofundar seus conhecimentos em testes automatizados em Java.

Esperamos que você tenha gostado e nos vemos em outros treinamentos aqui na Alura! Não deixe de usar o nosso fórum para postar dúvidas e ajudar outras pessoas que também estão aprendendo. Um abraço!
