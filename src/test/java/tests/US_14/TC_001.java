package tests.US_14;

import org.testng.annotations.Test;
import pages.SpendinGoodPage;
import tests.US_13.TC_01;

public class TC_001 extends TC_01 {

    @Test()
    public void TC001() {
    run();
        spendinGoodPage = new SpendinGoodPage();
        extentTest = extentReports.createTest("Kupon Olusturma Testi", "kupon olusturabilme");
        spendinGoodPage.minimumSpend.sendKeys("10000");
        extentTest.info("minimumSpend girildi");
        spendinGoodPage.maximumSpend.sendKeys("100000");
        extentTest.info("maximumSpend girildi");
    }

}