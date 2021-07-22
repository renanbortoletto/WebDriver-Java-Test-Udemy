package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

    @RunWith(DataDrivenTestRunner.class)
    @DataLoader(filePaths = "InformacoesUsuarioTestData.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();

        //Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();


        //Identificando o formulario de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com nome "Login"que esta dentro do formulario de ID "Signinbox" o texto "renanbortoletto"
        formularioSignInBox.findElement(By.name("login")).sendKeys("renanbortoletto");


        //Digitar no campo com nome "password"que esta dentro do formulario de ID "Signinbox" o texto "21109400re"
        formularioSignInBox.findElement(By.name("password")).sendKeys("21109400re");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }


    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

        //Clicar no botao atraves do seu xpath "//button[@data-target="addmoredata"]"
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar o popUp onde esta o formulario de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));


        //Na combo de "Name Type" escolher a opcao "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de name "contact" digitar "+5511987654321"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que esta no popUp
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto e "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);

    }

    @Test
    public void removerUmContatoDeUmUsuario(){
        // 11878767675
        //Clicar no elemento pelo seu xpath "secondary-content button"
        navegador.findElement(By.xpath("//span[text()=\"11878767675\"]/following-sibling::a")).click();

        // Clicar no botao OK
        navegador.switchTo().alert().accept();

        // validar a mensagem "Rest in peace, dear phone!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "/Users/renanbortoletto/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        //Aguardar ate 10seg para que a janela desapareca
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        //Fazer Logout
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();

    }
}
