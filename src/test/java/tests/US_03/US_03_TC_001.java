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
        extentTest = extentReports.createTest("5 urun secilmeli ve Secilen ürünler tek tek sepete aktarma testi"
                , "5 urun secilmeli ve Secilen ürünler tek tek sepete aktarıldigi gorulmeli");
        spendinGoodPage = new SpendinGoodPage();
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
        extentTest.pass("8. Vendor acilan Orders sayfasinda BROWSE PRODUCTS/Go Shop tiklandi");

    }

    @Test(dependsOnMethods = "tc_001")
    public void tc_002() {
        extentTest=extentReports.createTest("5 adet ürün secme ve secilen ürünleri tek tek sepete ekleme testi"
        ,"5 adet ürün secme ve secilen ürünleri tek tek sepete ekleyebilme");
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
            ReusableMethods.waitFor(2);
        String expectedSepettekiUrunSayisi= String.valueOf(sepet);
        String actualSepettekiUrunSayisi=spendinGoodPage.cartButonu.getText();
        Assert.assertEquals(expectedSepettekiUrunSayisi,actualSepettekiUrunSayisi);
        extentTest.pass("5 adet ürün secildi ve secilen ürünler tek tek sepete eklendi");

    }

    @Test(dependsOnMethods = "tc_002")
    public void tc_003() {
        extentTest=extentReports.createTest("Sepete gitme,Checkout butonunu görüp ve tıklama testi"
        ,"Sepete gitme,Checkout butonunu gorebilme ve tıklayabilmeli");
        ReusableMethods.jsExecutorClick(spendinGoodPage.cartButonu);
        extentTest.info("sepete gidildi");
        ReusableMethods.jsExecutorClick(spendinGoodPage.checkoutButon);
        extentTest.pass("Sepete gidildi,Checkout butonu goruldu ve tıklandi");
    }

    @Test(dependsOnMethods = "tc_003")
    public void tc_004() {
        extentTest=extentReports.createTest("Adres bilgilerini eksiksiz biçimde girebilme testi"
        ,"Adres bilgilerini eksiksiz biçimde girebilme");
        ReusableMethods.jsExecutorScrool(spendinGoodPage.billingDetailsFistnameTextBox);
        spendinGoodPage.billingDetailsFistnameTextBox.clear();
        actions.click(spendinGoodPage.billingDetailsFistnameTextBox).sendKeys("Bekir")
                .sendKeys(Keys.TAB).sendKeys("T").sendKeys(Keys.TAB)
                .sendKeys("ERDEM").sendKeys(Keys.TAB).sendKeys("turkey").sendKeys(Keys.ENTER).perform();
        ReusableMethods.jsExecutorScrool(spendinGoodPage.streetAddressTextBox);
        ReusableMethods.jsExecutorClick(spendinGoodPage.streetAddressTextBox);
        actions.sendKeys(spendinGoodPage.streetAddressTextBox,"8 sk. no:9").sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB).sendKeys("01250").sendKeys(Keys.TAB)
                .sendKeys("Saricam").sendKeys(Keys.TAB).sendKeys("ADANA")
                .sendKeys(Keys.TAB).sendKeys("+905543210514").sendKeys(Keys.TAB)
                .sendKeys("brt161822@gmail.com").sendKeys(Keys.TAB).sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB).sendKeys(Keys.DOWN).sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER).perform();


    }
}
