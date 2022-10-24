package tests.us_04;

import com.github.javafaker.Faker;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.SpendinGoodPage;
import tests.us_03.US_03;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class US_04 extends TestBaseRapor{
    SpendinGoodPage spendinGoodPage;

    public static void run1(){
        US_03 test1=new US_03();
        test1.us_03_tc_001();
        test1.us_03_tc_002();
    }

    @Test
    public void us_04_run1() {
        run1();
    }

    @Test(priority = 1)
    public void us_04_tc_001() {
        spendinGoodPage=new SpendinGoodPage();
        extentTest=extentReports.createTest("Urunnleri; product,Price,Ouantity,subtotal olarak gorebilme testi"
        ,"urunleri;product,Price,Ouantity,subtotal olarak gorebilme");
        ReusableMethods.waitForClickablility(spendinGoodPage.cartButonu,5);
        ReusableMethods.jsExecutorClick(spendinGoodPage.cartButonu);
        extentTest.info("Cart'a tiklandi");
        ReusableMethods.waitForClickablility(spendinGoodPage.viewCartButon,10);
        ReusableMethods.jsExecutorClick(spendinGoodPage.viewCartButon);
        extentTest.info("View Cart'a tiklandi");
        ReusableMethods.waitForVisibility(spendinGoodPage.sepettekiUrunList.get(1),10);
        Assert.assertEquals(5,spendinGoodPage.sepettekiUrunList.size());
        extentTest.pass("Urunler; product, Price, Ouantity, subtotal olarak goruldu");

    }

    @Test(priority = 2)
    public void us_04_tc_002() {
        spendinGoodPage = new SpendinGoodPage();
        extentTest=extentReports.createTest("Sepetteki ürünleri artirip azaltabildigini kontrol etme testi"
        ,"Sepetteki ürünleri arttirilip azaltilabilindigi gorulur");



    }
}
