package com.qa.opencart.factory;
/*
The init file contains instructions about browser specifications.
 */

import com.microsoft.playwright.*;

public class PlayWrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    public Page initBrowser(String browserName) {
        System.out.println("browser name is " + browserName);
        playwright = Playwright.create();

        //write switch statement to cover any choice of browser
        switch (browserName.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
                case "safari":
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                    case "firefox":
                        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                        break;
                        case "chrome":
                            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                            break;
                            default:
                                System.out.println("Please use acceptable browser names");
                                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate("https://naveenautomationlabs.com/opencart/");
        return page;
    }
}
