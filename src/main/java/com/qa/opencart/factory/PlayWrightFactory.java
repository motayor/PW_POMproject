//package com.qa.opencart.factory;
///*
//The init file contains instructions about browser specifications.
// */
//
//import com.microsoft.playwright.*;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class PlayWrightFactory {
//    Playwright playwright;
//    Browser browser;
//    BrowserContext browserContext;
//    static Page page;
//    Properties props;  // this accommodates the initialisation of the config.properties call-in
//
///**
// * this method initialises properties from the config file
// * @return properties attributes
// */
//    public Properties init_prop(){
//        try {
//            FileInputStream ip = new FileInputStream("src/test/resources/config/config.properties");
//            props = new Properties();
//            props.load(ip);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return props;
//    }
//
////method for browser identification
//    public Page initBrowser(Properties props) {
//        String browserName = props.getProperty("browser").trim();
//        System.out.println("browser name is " + browserName);
//
//        playwright = Playwright.create();
//
//        //write switch statement to cover any choice of browser
//        switch (browserName.toLowerCase()){
//            case "chromium":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//                break;
//                case "safari":
//                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
//                    break;
//                    case "firefox":
//                        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
//                        break;
//                        case "chrome":
//                            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
//                            break;
//                            default:
//                                System.out.println("Please use acceptable browser names");
//                                break;
//        }
//
//        browserContext = browser.newContext();
//        page = browserContext.newPage();
//        //page.navigate("https://naveenautomationlabs.com/opencart/"); //we don't need hard coded url since we have config.property file
//        page.navigate(props.getProperty("url"));
//        return page;
//    }
//
////    public static String takeScreenshot() {
////        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
////        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
////        return path;
////    }
//
//
//}
