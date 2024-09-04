package com.qa.opencart.factory;

/** Use this class or the PlayWrightFactory class and change implementation in the
 * BaseTest class
 */

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlayWrightFactoryThreadLocal {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties props;


    private static ThreadLocal<Playwright> tlPlayWright= new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser= new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext= new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage= new ThreadLocal<>();

    //Get Methods
    public static Playwright getPlaywright() {
        return tlPlayWright.get();
    }
    public static Browser getBrowser() {
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }
    public static Page getPage() {
        return tlPage.get();
    }

//===============************************************========================
    public Properties init_prop(){
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config/config.properties");
            props = new Properties();
            props.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
//===============************************************========================

    //method for browser identification
    public Page initBrowser(Properties props) {
        String browserName = props.getProperty("browser").trim();
        System.out.println("browser name is " + browserName);

        //Set Methods here
        tlPlayWright.set(Playwright.create());

        switch (browserName.toLowerCase()){
            case "chromium":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            default:
                System.out.println("Please use acceptable browser names");
                break;
        }


        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage()); // initialise the page
        getPage().navigate(props.getProperty("url").trim());
        return getPage();

        //browserContext = browser.newContext();
        //page = browserContext.newPage();
        //page.navigate("https://naveenautomationlabs.com/opencart/"); //we don't need hard coded url since we have config.property file
        //page.navigate(props.getProperty("url"));
        //return page;
    }

    /**
     * Take Screenshot
     * @return the path
     */
    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        return path;
    }

}
