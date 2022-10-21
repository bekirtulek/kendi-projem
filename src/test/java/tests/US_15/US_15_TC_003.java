package tests.US_15;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SpendinGoodPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class US_15_TC_003 extends TestBaseRapor {
    SpendinGoodPage spendinGoodPage=new SpendinGoodPage();
    Actions actions=new Actions(Driver.getDriver());
    Faker faker=new Faker();

    @Test
    public void tc_003() {
        spendinGoodPage=new SpendinGoodPage();
        extentTest=extentReports.createTest("Belli kimselere (mesela cok kullanan userlara) kullanim limiti koyma testi"
                ,"Belli kimselere (mesela cok kullanan userlara) kullanim limiti koyuldugunu gorebilme");
        Driver.getDriver().get(ConfigReader.getProperty("spendingGoodUrl"));
        spendinGoodPage.signInButton.click();
        spendinGoodPage.emailAdresBox.sendKeys(ConfigReader.getProperty("bekirEmail"));
        spendinGoodPage.passwordBox.sendKeys(ConfigReader.getProperty("bekirPassword1"));
        //todo US_12_TC_005\tc_005_02 ignore edildiyse bekirPassword2'yi kullan
        spendinGoodPage.signInButton2.click();
        extentTest.info("login olundu");
        ReusableMethods.waitFor(2);
        spendinGoodPage.myAccountButton.click();
        ReusableMethods.jsExecutorScrool(spendinGoodPage.storeManager);
        spendinGoodPage.storeManager.click();
        ReusableMethods.jsExecutorScrool(spendinGoodPage.couponButton);
        ReusableMethods.jsExecutorClick(spendinGoodPage.couponButton);
        ReusableMethods.jsExecutorScrool(spendinGoodPage.couponAddNewButton);
        ReusableMethods.jsExecutorClick(spendinGoodPage.couponAddNewButton);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.click(spendinGoodPage.codeText).sendKeys(faker.idNumber().valid())
                .sendKeys(Keys.TAB).sendKeys("Electronics").sendKeys(Keys.TAB)
                        .sendKeys(Keys.TAB).sendKeys("100").sendKeys(Keys.TAB)
                        .sendKeys("2022-12-31").sendKeys(Keys.TAB).sendKeys(Keys.SPACE)
                        .sendKeys(Keys.TAB).sendKeys(Keys.SPACE).perform();
        extentTest.info("Gerekli kupon bilgileri girildi");
        ReusableMethods.jsExecutorScrool(spendinGoodPage.couponLimitButon);
        ReusableMethods.jsExecutorClick(spendinGoodPage.couponLimitButon);
        extentTest.info("Limit'e tiklandi");
        spendinGoodPage.usageLimitPerUserTextBox.sendKeys("2");
        extentTest.info("Limit usage limit per user'a bir sayi degeri girildi");
        ReusableMethods.jsExecutorScrool(spendinGoodPage.couponSubmitButon);
        spendinGoodPage.couponSubmitButon.click();
        extentTest.info("Submit butonuna basildi");
        //ReusableMethods.jsExecutorScrool(spendinGoodPage.couponSuccessfullyPublishedMessage);
        ReusableMethods.waitForVisibility(spendinGoodPage.couponSuccessfullyPublishedMessage,15);
        String exceptionText="Coupon Successfully Published.";
        String actualText=spendinGoodPage.couponSuccessfullyPublishedMessage.getText();
        Assert.assertEquals(exceptionText,actualText);
        extentTest.pass("Belli kimselere (mesela cok kullanan userlara) kullanim limiti koyuldugu goruldu");


    }
}
