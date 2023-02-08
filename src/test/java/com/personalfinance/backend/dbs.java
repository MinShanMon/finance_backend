package com.personalfinance.backend;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class dbs {
    public static void main(String[] args) throws IOException{
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.dbs.com.sg/personal/rates-online/singapore-dollar-fixed-deposits.page");
        System.out.println(driver.getPageSource());
}


}