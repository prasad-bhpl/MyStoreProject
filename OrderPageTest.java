/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.Base.BasecClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

/**
 * @author prasa
 *
 */
public class OrderPageTest extends BasecClass {
	IndexPage indexPage;
	LoginPage loginpage;
	HomePage homepage;
	SearchResultPage searchresultPage;
	AddToCartPage addtocartpage;
	OrderPage orderpage;
	
	@Parameters("browsername")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browsername) {
		launchapp(browsername);
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups="Regression", dataProvider="getProduct", dataProviderClass=DataProviders.class)
	public void verifyTotalprice(String productName, String qty, String size) throws Exception
	{
		Log.startTestCase("verifyTotalprice");
		indexPage=new IndexPage();
		searchresultPage=indexPage.searchProduct(productName);
		addtocartpage=searchresultPage.clickOnProduct();
		addtocartpage.enterQuantity(qty);
		addtocartpage.enterSize(size);
		addtocartpage.clickOnAddToCart();
		orderpage=addtocartpage.clickOnCheckOut();
		Double unitprice=orderpage.getUnitPrice();
		Double totalprice=orderpage.getTotalPrice();
		Double totalexpectedprice=(unitprice*(Double.parseDouble(qty)))+2;
		Assert.assertEquals(totalprice, totalexpectedprice);
		Log.endTestCase("verifyTotalprice");
		
	}
	
}
