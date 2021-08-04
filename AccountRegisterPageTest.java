package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.Base.BasecClass;
import com.mystore.actiondriver.Action;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class AccountRegisterPageTest extends BasecClass {
	IndexPage indexPage;
	LoginPage loginpage;
	AccountCreationPage accountCreationPage;
	//AccountRegisterPage accountRegisterPage;
	
	@Parameters("browsername")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browsername) {
		launchapp(browsername);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	@Test
	public void accountRegisterConfirm() throws InterruptedException
	{
		indexPage=new IndexPage();
		loginpage=indexPage.clickOnSignIn();
		accountCreationPage=loginpage.createNewAccount("apd1@gmail.com");
		Thread.sleep(5000);
		accountCreationPage.createAccountRegistration("apd", "ankam", "apds@gmail.com", "apple1112", 5, 4, 3,
"Cherlapally", "ECIL", "NES", "Hyd", "Hyderbad", 4, 50616, 1, "9985000387");
		//String actual=accountCreationPage.validateRegistration();
		String expectedtext="Welcome to your account. Here you can manage all of your personal information and orders.";
		//Assert.assertEquals(actual, expectedtext);		
	}

}
