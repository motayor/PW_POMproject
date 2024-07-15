package com.qa.opencart.tests;

/* This test class calls method from the playwrightfactory class.

 */

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlayWrightFactory;
import com.qa.opencart.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTests {
    PlayWrightFactory pw;
    Page page;
    HomePage homePage;


    @BeforeTest  //init a browser setup method before each test
    public void setUp() {
        pw = new PlayWrightFactory();
        //the init class PWFactory returns a page ref, hence
        page = pw.initBrowser("firefox");
        homePage = new HomePage(page);
    }

    @Test
    public void homePageTitleTest() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, "Your Store");
    }

    @Test
    public void homePageUrlTest() {
        String actualURL= homePage.getHomePageUrl();
        Assert.assertEquals(actualURL, "https://naveenautomationlabs.com/opencart/");
    }

    @Test
    public void searchTest(){
        String actualSearchHeader = homePage.doSearch("Macbook"); //Search for a product name
        Assert.assertEquals(actualSearchHeader, "Search - Macbook");
    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }
}
