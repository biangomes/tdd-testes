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

