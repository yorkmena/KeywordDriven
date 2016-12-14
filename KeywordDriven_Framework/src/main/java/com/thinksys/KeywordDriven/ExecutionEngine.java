package com.thinksys.KeywordDriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExecutionEngine {
	
	 private static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.gecko.driver", "D:\\Selenium Projects\\KeywordDriven_Framework\\geckodriver.exe");
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://in.match.com/cpx/intl/match/IndexPage/");
		driver.findElement(By.linkText("Member Sign In")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("email")).sendKeys("mathur.int@gmail.com");
		System.out.println("Enter Password");
		driver.findElement(By.id("password")).sendKeys("qwerty");
		driver.findElement(By.xpath("//button[contains(text(),'Sign in now »')]")).click();
		System.out.println("Login SUccessfull");
		Thread.sleep(10000);
        driver.quit();
            }

	}


