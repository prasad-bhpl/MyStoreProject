package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.Base.BasecClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class LoginPageTest extends BasecClass{
	IndexPage indexPage;
	LoginPage loginpage;
	HomePage homepage;
	
	@Parameters("browsername")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browsername) {
		launchapp(browsername);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(dataProvider="credentials", dataProviderClass=DataProviders.class)
	public void loginTest(String uname, String pwd)
	{
		Log.startTestCase("loginTest");
		indexPage=new IndexPage();
		Log.info("User is going to Click on Sign In");
		loginpage=indexPage.clickOnSignIn();
		Log.info("Enter Username and password");
		//homepage=loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		homepage=loginpage.login(uname, pwd);
		String actualurl=homepage.getCurrentUrl();
		String expectedurl="http://automationpractice.com/index.php?controller=my-account";
		Log.info("User is verifying if able to login");
		Assert.assertEquals(actualurl, expectedurl);
		Log.info("Login is Success");
		Log.endTestCase("loginTest");
		
	}

}
