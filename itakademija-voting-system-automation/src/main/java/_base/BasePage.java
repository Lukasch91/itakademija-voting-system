package _base;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import _pages.admin.AdminCandidateUploadMulti;

public class BasePage {

	public WebDriver webDriver;
	public String PAGE_URL;
	public String PAGE_TITLE;
	public static final int WAIT_FOR_ELEMENT = 20;
	public By popUp = By.xpath("//*[@class='btn btn-xs btn-danger']");
	public By remove = By.xpath("//*[@class='glyphicon glyphicon-remove']");

	public BasePage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public void loadPage() {
		webDriver.get(getPageUrl());
	}

	public void clickElement(WebElement webElement) {
		webElement.click();
	}

	public void setElementText(WebElement webElement, String text) {
		webElement.clear();
		webElement.sendKeys(text);
		assertEquals(webElement.getAttribute("value"), text);
	}

	public void setElementTextNoClear(WebElement webElement, String text) {
		webElement.sendKeys(text);
		assertEquals(webElement.getAttribute("value"), text);
	}

	public void setElementTextNoAssert(WebElement webElement, String text) {
		webElement.clear();
		webElement.sendKeys(text);
	}

	public void selectDropdownValue(WebElement webElement, String value) {
		Select select = new Select(webElement);
		select.selectByVisibleText(value);
	}

	public void confirmPopUp() {
		waitUntilElementToBeClickable(popUp);
		WebElement webElement = webDriver.findElement(popUp);
		webElement.click();
		waitForJavascript();
	}

	public void clickRemove() {
		clickElement(webDriver.findElement(remove));
	}

	// --------------GETTERS-----------------

	public String getPageUrl() {
		return PAGE_URL;
	}

	// --------------WAITS------------------

	public boolean verifyElementIsPresent(WebElement webElement) {
		try {
			webElement.getTagName();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {

		}

	}

	/**
	 * JavaScript waits
	 */
	public void checkIsLoaded() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(pageLoadCondition);
	}

	public void waitForJavascript() {
		new WebDriverWait(webDriver, 5).until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
			}
		});
	}

	/**
	 * Looks for element in page
	 */
	public boolean isElementPresent(By by) {
		try {
			webDriver.findElement(by);
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Function that checks if element is displayed
	 */
	public boolean isElementDisplayed(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Wait for element while it is listed in page's DOM structure and is
	 * visible
	 */
	public void waitForElementToBeInDOM(WebElement element) {
		WebDriverWait wait = new WebDriverWait(webDriver, WAIT_FOR_ELEMENT);
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for element, this is not check whether element is clickable;
	 */
	public void waitForElement(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, WAIT_FOR_ELEMENT);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Wait until element is clickable
	 */
	public void waitUntilElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, WAIT_FOR_ELEMENT);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * Wait until your system gets optimized
	 */
	public void waitOrNotToWait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Why did you use it..?" + e.getMessage());
		}
	}

}
