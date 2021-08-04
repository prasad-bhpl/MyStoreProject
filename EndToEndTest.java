/**
 * 
 */
package com.mystore.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.Base.BasecClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummaryPage;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShipppingPage;

/**
 * @author prasa
 *
 */
public class EndToEndTest extends BasecClass {
	IndexPage indexPage;
	LoginPage loginpage;
	HomePage homepage;
	SearchResultPage searchresultPage;
	AddToCartPage addtocartpage;
	OrderPage orderpage;
	AddressPage addresspage;
	ShipppingPage shipppingpage;
	OrderSummaryPage ordersummarypage;
	PaymentPage paymentpage;
	OrderConfirmationPage orderconfirmpage;
	
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
	public void verifyendetoend() throws Exception
	{
		indexPage=new IndexPage();
		searchresultPage=indexPage.searchProduct("t-shirt");
		addtocartpage=searchresultPage.clickOnProduct();
		addtocartpage.enterQuantity("2");
		addtocartpage.enterSize("M");
		addtocartpage.clickOnAddToCart();
		orderpage=addtocartpage.clickOnCheckOut();
		loginpage=orderpage.clickOnCheckOut();
		addresspage=loginpage.login1(prop.getProperty("username"), prop.getProperty("password"));
		shipppingpage=addresspage.clickOnCheckOut();
		shipppingpage.checkTheTerms();
		paymentpage=shipppingpage.clickOnProceedToCheckOut();
		ordersummarypage=paymentpage.clickOnPaymentWireMethod();
		orderconfirmpage=ordersummarypage.clickOnConfirmOrderBtn();
		
		String actualmessage=orderconfirmpage.validateConfirmMessage();
		String expectedmessage="Your order on My Store is complete.";
		Assert.assertEquals(actualmessage, expectedmessage);	
		
	}

}
