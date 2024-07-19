package com.qa.opencart.tests;

/** This test class calls method from the playwrightfactory class.
 * Also extends the BaseTest class which specifies the preconditions for
 * setting up and tearing down test.
 */

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    @Test
    public void homePageTitleTest() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void homePageUrlTest() {
        String actualURL= homePage.getHomePageUrl();
        Assert.assertEquals(actualURL, props.getProperty("url"));
    }

    //use object list to provide list of searchable items
    @DataProvider
    public Object[][] getProductList(){
        return new Object[][]{
                {"macbook"},
                {"iphone"},
                {"iMac"},
        };
    }

    @Test(dataProvider = "getProductList")
    public void searchTest(String productName) {
        String actualSearchHeader = homePage.doSearch(productName); //Search for a product name
        Assert.assertEquals(actualSearchHeader, "Search - " + productName);
    }

}
