package com.qa.opencart.pages;
/*
It is a nice idea to create layer page for the element locators that each test class can call.
 */

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;
    //1. String Locators - OR
    private String search = "input[name='search']";
    private String searchIcon = "div#search button";
    private String searchPageHeader = "div#content h1";

    //2. page constructor
    public HomePage(Page page) {
        this.page = page;
    }

    //3. page actions/methods
    public String getHomePageTitle() {
        String title = page.title();
        System.out.println("Page title: " + title);
        return title;
    }

    public String getHomePageUrl(){
        String url = page.url();
        System.out.println("Page url: " + url);
        return page.url();
    }

    public String doSearch(String productName){
        page.fill(search, productName);
        page.click(searchIcon);
        String header = page.textContent(searchPageHeader);
        System.out.println("Search Page header: " + header);
        return header;

    }



}
