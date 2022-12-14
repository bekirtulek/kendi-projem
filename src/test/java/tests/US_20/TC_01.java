package tests.US_20;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.SpendinGoodPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC_01 {
    @Test
    public void testCase01() {
        SpendinGoodPage spendingPage = new SpendinGoodPage();
        Actions actions = new Actions(Driver.getDriver());
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        spendingPage.girisYap("oguzEmail","oguzPassword");
        ReusableMethods.waitFor(3);
        spendingPage.myAccount.click();
        js.executeScript("arguments[0].scrollIntoView(true);", spendingPage.dashboard);
        ReusableMethods.waitFor(1);
        spendingPage.storeManager.click();
        js.executeScript("arguments[0].scrollIntoView(true);", spendingPage.home);
        js.executeScript("arguments[0].click()", spendingPage.reviews);
        spendingPage.productReviews.click();
        Assert.assertTrue(spendingPage.productReviewsKullaniciBilgisi.isDisplayed());
        Assert.assertTrue(spendingPage.productReviewsComment.isDisplayed());

    }
}
