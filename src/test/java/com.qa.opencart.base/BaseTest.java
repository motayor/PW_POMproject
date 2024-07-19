package com.qa.opencart.base;

import com.microsoft.playwright.Page;
//import com.qa.opencart.factory.PlayWrightFactory;
import com.qa.opencart.factory.PlayWrightFactoryThreadLocal;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

/**
 * This file contains the testng preconditions for your test files e.g. setting up and tearing down
 * the test pages. All testng classes can extend this base file to specify their preconditions.
 */


public class BaseTest {
    //PlayWrightFactory pw;
    PlayWrightFactoryThreadLocal pw;
    Page page;
    protected HomePage homePage;
    protected Properties props;
    protected LoginPage loginPage;

    @BeforeTest  //init a browser setup method before each test
    public void setUp() {
        //pw = new PlayWrightFactory();
        pw = new PlayWrightFactoryThreadLocal();
        props = pw.init_prop(); //call in init_prop properties method from the PWFactory
        page = pw.initBrowser(props);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown(){
        page.context().browser().close();
    }

}
