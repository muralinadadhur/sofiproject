package com.automation.techassessment.ui.Sauce;

import com.automation.techassessment.ui.lib.Wait;
import com.automation.techassessment.ui.pages.sauce.SauceDemo;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SauceDemoCartTest extends BaseUITest {
    @Test
    public void validLogin() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        SauceDemo.Login.login("standard_user", "secret_sauce");
        softAssert.assertTrue(SauceDemo.MenuBar.menuBarButtonExists(),
                "Unable to find Menu Bar button, login must have failed");
        softAssert.assertTrue(SauceDemo.Dashboard.productContainerIsVisible(),
                "Unable to find Product Container, dashboard must have failed to load");
        softAssert.assertAll();
    }
    @Test
    public void validProductPage() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        SauceDemo.Login.login("standard_user", "secret_sauce");
        softAssert.assertTrue(SauceDemo.ShoppingCartPage.product_Label_Exists(),
                "Unable to find the list of products, this must be wrong page");
        softAssert.assertTrue(SauceDemo.ShoppingCartPage.onsie_Label_Exists(),
                "Page does not have an Sauce Labs Onsie item. Please check");
        Assert.assertTrue(SauceDemo.ShoppingCartPage.bike_Label_Exists(),
                "Page does not contain Sauce Labs Bike Light. Please check.");

    }
    @Test
    public void validProductPage_AddItemstoCart() throws Exception {
        SoftAssert softAssert = new SoftAssert();
        SauceDemo.Login.login("standard_user", "secret_sauce");
        softAssert.assertTrue(SauceDemo.ShoppingCartPage.product_Label_Exists(),
                "Unable to find the list of products, this must be wrong page");
        softAssert.assertEquals(SauceDemo.ShoppingCartPage.onsie_Label_Click("Onesie"),"1");
        Assert.assertEquals(SauceDemo.ShoppingCartPage.onsie_Label_Click("Bike"),"2");
    }
}
