package tests.us_04;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SpendinGoodPage;
import tests.us_03.US_03;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.List;

public class US_04 extends TestBaseRapor{
    SpendinGoodPage spendinGoodPage;
    Actions actions=new Actions(Driver.getDriver());
    Faker faker=new Faker();
    public static void run1(){
        US_03 test1=new US_03();
        test1.us_03_tc_001();
        test1.us_03_tc_002();
    }
    @Test()
    public void TC0001() {
        run1();
    }

    @Test
    public void us_04_tc_001() {
        spendinGoodPage=new SpendinGoodPage();
        extentTest=extentReports.createTest("Urunnleri; product,Price,Ouantity,subtotal olarak gorebilme testi"
        ,"urunleri;product,Price,Ouantity,subtotal olarak gorebilme");
        ReusableMethods.waitForClickablility(spendinGoodPage.cartButonu,5);
        ReusableMethods.jsExecutorClick(spendinGoodPage.cartButonu);
        extentTest.info("sepete gidildi");




    }
}
