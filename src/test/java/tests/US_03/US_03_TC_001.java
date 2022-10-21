package tests.US_03;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SpendinGoodPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.List;

public class US_03_TC_001 extends TestBaseRapor {
    SpendinGoodPage spendinGoodPage;
    Actions actions=new Actions(Driver.getDriver());
    @Test
    public void tc_001() {
        extentTest=extentReports.createTest("5 urun secilmeli ve Secilen ürünler tek tek sepete aktarma testi"
                ,"5 urun secilmeli ve Secilen ürünler tek tek sepete aktarıldigi gorulmeli");
        spendinGoodPage=new SpendinGoodPage();
        Driver.getDriver().get(ConfigReader.getProperty("spendingGoodUrl"));
        extentTest.info("Spendinggood Sitesine Gidildi");
        spendinGoodPage.signInButton.click();
        extentTest.info("Sign in butonuna tiklandi");
        spendinGoodPage.emailAdresBox.sendKeys(ConfigReader.getProperty("bekirEmail"));
        extentTest.info("Username olarak gecerli bir email adresi girildi");
        spendinGoodPage.passwordBox.sendKeys(ConfigReader.getProperty("bekirPassword1"));
        extentTest.info("Gecerli bir password girildi");
        spendinGoodPage.signInButton2.click();
        extentTest.info("SIGN IN butonuna tiklandi");
        ReusableMethods.waitFor(3);
        spendinGoodPage.myAccountButton.click();
        extentTest.info("My Account'a tiklandi");
        ReusableMethods.jsExecutorScrool(spendinGoodPage.myAccountOrdersButon);
        ReusableMethods.jsExecutorClick(spendinGoodPage.myAccountOrdersButon);
        extentTest.info(" ORDERS' a tiklandi");
        ReusableMethods.jsExecutorScrool(spendinGoodPage.ordersGoShopButon);
        ReusableMethods.jsExecutorClick(spendinGoodPage.ordersGoShopButon);
        ReusableMethods.waitFor(2);
        List<WebElement> addCartButonList=spendinGoodPage.miniAddToCartButon;
        List<WebElement> urunListesi=spendinGoodPage.us_03_UrunListesi;
        int sepet=0;

            for (int i = 0; i < urunListesi.size(); i++) {
                    ReusableMethods.jsExecutorScrool(urunListesi.get(i));
                    ReusableMethods.waitFor(2);
                    actions.moveToElement(urunListesi.get(i)).perform();
                    if (addCartButonList.get(i).isEnabled()) {
                        ReusableMethods.jsExecutorClick(addCartButonList.get(i));
                        sepet++;

                    }
                    ReusableMethods.waitFor(2);
                if (sepet==5){
                    break;
                }
            }

    }
}
