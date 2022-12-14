package tests.us_03;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.SpendinGoodPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.List;

public class US_03 extends TestBaseRapor {
    SpendinGoodPage spendinGoodPage;
    Actions actions=new Actions(Driver.getDriver());
    Faker faker=new Faker();
    @Test
    public void us_03_tc_001() {
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

    @Test(dependsOnMethods = "us_03_tc_001")
    public void us_03_tc_002() {
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

    @Test(dependsOnMethods = "us_03_tc_002")
    public void us_03_tc_003() {
        extentTest=extentReports.createTest("Sepete gitme,Checkout butonunu görüp ve tıklama testi"
        ,"Sepete gitme,Checkout butonunu gorebilme ve tıklayabilmeli");
        ReusableMethods.jsExecutorClick(spendinGoodPage.cartButonu);
        extentTest.info("sepete gidildi");
        ReusableMethods.jsExecutorClick(spendinGoodPage.checkoutButon);
        extentTest.pass("Sepete gidildi,Checkout butonu goruldu ve tıklandi");
    }

    @Test(dependsOnMethods = "us_03_tc_003")
    public void us_03_tc_004() {
        extentTest=extentReports.createTest("Adres bilgilerini eksiksiz biçimde girebilme testi"
        ,"Adres bilgilerini eksiksiz biçimde girebilme");
        ReusableMethods.waitForClickablility(spendinGoodPage.billingDetailsFistnameTextBox,15);
        ReusableMethods.jsExecutorScrool(spendinGoodPage.billingDetailsFistnameTextBox);
        spendinGoodPage.billingDetailsFistnameTextBox.clear();
        ReusableMethods.waitFor(2);

        actions.click(spendinGoodPage.billingDetailsFistnameTextBox).sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB).sendKeys(faker.name().lastName()).sendKeys(Keys.TAB)
                .sendKeys(faker.company().name()).sendKeys(Keys.TAB).sendKeys("Turkey")
                .sendKeys(Keys.ENTER).perform();
        ReusableMethods.waitFor(2);
        ReusableMethods.jsExecutorScrool(spendinGoodPage.billingDetailsStreetAddressTextBox);
        ReusableMethods.jsExecutorClick(spendinGoodPage.billingDetailsStreetAddressTextBox);
        spendinGoodPage.billingDetailsStreetAddressTextBox.clear();
        ReusableMethods.waitFor(2);
        actions.click(spendinGoodPage.billingDetailsStreetAddressTextBox).sendKeys(faker.address().streetAddress())
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(faker.address().zipCode()).sendKeys(Keys.TAB)
                .sendKeys(faker.address().cityName()).sendKeys(Keys.TAB).sendKeys("Adana",Keys.ENTER)
                .sendKeys(Keys.TAB).sendKeys(faker.phoneNumber().cellPhone()).sendKeys(Keys.TAB)
                .sendKeys("brt161822@gmail.com").perform();


        extentTest.pass("Adres bilgilerini eksiksiz biçimde girildi");


    }

    @Test(dependsOnMethods = "us_03_tc_004")
    public void us_03_tc_005() {
        extentTest=extentReports.createTest("YOUR ORDER bölümünde Payment Methods'u secme ve PLACE ORDER'a tiklama testi"
        ,"YOUR ORDER bölümünde Payment Methods'u secebilme ve PLACE ORDER'a tiklayabilme");
        ReusableMethods.waitFor(2);
        ReusableMethods.jsExecutorScrool(spendinGoodPage.payAtTheDoorRadioButon);
        ReusableMethods.jsExecutorClick(spendinGoodPage.payAtTheDoorRadioButon);
        ReusableMethods.jsExecutorScrool(spendinGoodPage.placeOrderButon);
        ReusableMethods.jsExecutorClick(spendinGoodPage.placeOrderButon);
        ReusableMethods.waitForVisibility(spendinGoodPage.orderCompletedText,15);
        String expectedText="Thank you. Your order has been received.";
        String actualText=spendinGoodPage.orderCompletedText.getText();
        System.out.println("actualText = " + actualText);
        Assert.assertTrue(spendinGoodPage.orderCompletedText.isDisplayed());
        extentTest.pass("YOUR ORDER bölümünde Payment Methods'u secildi ve PLACE ORDER'a tiklandi");


    }
}
