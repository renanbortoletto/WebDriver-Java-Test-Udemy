package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformacoesUsuarioTes {
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "/Users/renanbortoletto/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();

        //Navegando para a pagina do Taskit
        navegador.get("http://www.renanbortoletto.com.br/taskit");

        //Validaçao
        assertEquals(1,1);
    }
}
