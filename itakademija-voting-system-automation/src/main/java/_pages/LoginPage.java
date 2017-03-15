package _pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import _base.BasePage;

public class LoginPage extends BasePage {
	
	//--ELEMENTS--
	@FindBy(id = "username")
	private WebElement field_UsernameLogin;
	
	@FindBy(id = "password")
	private WebElement field_PasswordLogin;
	
	@FindBy(id = "signin")
	private WebElement button_SignIn;
	
	@FindBy(css = ".login-message")
	private WebElement errorMessage;

	
	//--CONSTRUCTOR--
	public LoginPage(WebDriver webDriver) {
		super(webDriver);
		this.PAGE_TITLE = "RinkSis";
		this.PAGE_URL = "http://localhost:8080/login";
	}
	
	//--METHODS--
	public void setText_UsernameLogin(String text) {
		checkIsLoaded();
		setElementText(field_UsernameLogin, text);
	}
	
	public void setText_PasswordLogin(String text) {
		checkIsLoaded();
		setElementText(field_PasswordLogin, text);
	}
	
	public void clickLogin() {
		checkIsLoaded();
		clickElement(button_SignIn);
	}
	public void login(String username, String password) {
		loadPage();
		setText_UsernameLogin(username);
		setText_PasswordLogin(password);
		clickLogin();
		
	}
	
	//---------------ASSERTS---------------
	public boolean checkErrorMessage(String text) {
		try {
			waitForElementToBeInDOM(field_UsernameLogin);
			return errorMessage.getText().equals(text);
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	
}
