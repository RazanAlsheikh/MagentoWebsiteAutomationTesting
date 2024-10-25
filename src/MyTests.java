import java.awt.event.InvocationEvent;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTests {

	WebDriver driver = new ChromeDriver();
	String MyWebsite = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();

	String password = "Roro@951345";
	String LogoutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String EmailAddressToLogin = "";

	@BeforeTest
	public void mysetup() {
		driver.manage().window().maximize();

		driver.get(MyWebsite);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1)
	public void CreateAnAccount() {

		WebElement createAnAccountPage = driver
				.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));

		createAnAccountPage.click();

		// First Name

		String[] firstNames = { "Liam", "Emma", "Noah", "Olivia", "Ava", "Isabella", "Sophia", "Mason", "Lucas", "Mia",
				"Amelia", "Harper", "Evelyn", "James", "Charlotte" };

		// Last Name

		String[] lastNames = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
				"Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson" };

		int RandomIndexForFirstNames = rand.nextInt(firstNames.length);
		int RandomIndexForLastNames = rand.nextInt(lastNames.length);

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement LastNameInput = driver.findElement(By.id("lastname"));
		WebElement EmailInput = driver.findElement(By.id("email_address"));
		WebElement PassowrdInput = driver.findElement(By.id("password"));
		WebElement PassowrdConfirmedInput = driver.findElement(By.id("password-confirmation"));
		WebElement CreateAnAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));

		String firstName = firstNames[RandomIndexForFirstNames];
		String LastName = lastNames[RandomIndexForLastNames];
		String domainName = "@gmail.com";
		int randomNumber = rand.nextInt(1987);

		firstNameInput.sendKeys(firstName);
		LastNameInput.sendKeys(LastName);
		EmailInput.sendKeys(firstName + LastName + randomNumber + domainName);
		PassowrdInput.sendKeys(password);
		PassowrdConfirmedInput.sendKeys(password);
		CreateAnAccountButton.click();

		EmailAddressToLogin = firstName + LastName + randomNumber + domainName;

		WebElement RegistrationMessage = driver.findElement(By.className("messages"));

		String ActualMessage = RegistrationMessage.getText();
		String ExpectedMessage = "Thank you for registering with Main Website Store.";

		Assert.assertEquals(ActualMessage, ExpectedMessage);

	}

	@Test(priority = 2)
	public void LogoutTest() {

		driver.get(LogoutPage);

		WebElement LogoutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));

		String ActualMessage = LogoutMessage.getText();
		String ExpectedMessage = "You are signed out";

		Assert.assertEquals(ActualMessage, ExpectedMessage);

	}

	@Test(priority = 3)
	public void LogInTest() {

		WebElement LogInPage = driver.findElement(By.linkText("Sign In"));
		LogInPage.click();

		WebElement LongInEmailInput = driver.findElement(By.id("email"));
		WebElement LogInPasswordInput = driver.findElement(By.id("pass"));
		WebElement SignInButton = driver.findElement(By.cssSelector(".action.login.primary"));

		LongInEmailInput.sendKeys(EmailAddressToLogin);
		LogInPasswordInput.sendKeys(password);

		SignInButton.click();

		String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();
		boolean ActualValue = WelcomeMessage.contains("Welcome");
		boolean ExpectedValue = true;

		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 4)
	public void AddMenItems() {

		WebElement MenSection = driver.findElement(By.cssSelector("#ui-id-5"));

		MenSection.click();

		WebElement ItemsContainer = driver.findElement(By.className("product-items"));

		List<WebElement> Items = ItemsContainer.findElements(By.tagName("li"));

		int TotalNumbersOfItems = Items.size();
		int RandomItem = rand.nextInt(TotalNumbersOfItems);
		Items.get(RandomItem).click();

		WebElement SizeContainer = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));

		List<WebElement> ListOfSize = SizeContainer.findElements(By.className("swatch-option"));

		int TotalNumbersOfSize = ListOfSize.size();
		int RandomSize = rand.nextInt(TotalNumbersOfSize);
		ListOfSize.get(RandomSize).click();

		WebElement ColorContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> ListOfColors = ColorContainer.findElements(By.tagName("div"));

		int TotalNumbersOfColors = ListOfColors.size();
		int RandomColor = rand.nextInt(TotalNumbersOfColors);
		ListOfColors.get(RandomColor).click();

		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
		AddToCartButton.click();

		WebElement AddedMessage = driver.findElement(By.cssSelector(".message-success.success.message"));

		System.out.println(AddedMessage.getText().contains("You added"));

		Assert.assertEquals(AddedMessage.getText().contains("You added"), true);

	}

	@Test(priority = 5)
	public void AddWomenItems() throws InterruptedException {

		WebElement WomenSecion = driver.findElement(By.id("ui-id-4"));
		WomenSecion.click();
		WebElement WomenItemsContainer = driver.findElement(By.className("product-items"));
		List<WebElement> ListOfWomenItems = WomenItemsContainer.findElements(By.tagName("li"));

		int TotalNumersOfWomenItems = ListOfWomenItems.size();
		int randomItem = rand.nextInt(TotalNumersOfWomenItems);
		ListOfWomenItems.get(randomItem).click();

		WebElement SizeContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));

		List<WebElement> ListOfSizes = SizeContainer.findElements(By.tagName("div"));

		int TotalSizes = ListOfSizes.size();
		int RandomSize = rand.nextInt(TotalSizes);
		ListOfSizes.get(RandomSize).click();

		WebElement ColorContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> ListOFColors = ColorContainer.findElements(By.tagName("div"));

		int TotalNumbersOfColors = ListOFColors.size();
		int RandomColor = rand.nextInt(TotalNumbersOfColors);
		ListOFColors.get(RandomColor).click();

		WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));

		AddToCartButton.click();

		WebElement AddToCartMessage = driver.findElement(By.cssSelector(".message-success.success.message"));

		System.out.println(AddToCartMessage.getText().contains("You added"));

		Assert.assertEquals(AddToCartMessage.getText().contains("You added"), true);

		//// Reviews

		WebElement ReviewButton = driver.findElement(By.partialLinkText("Reviews"));

		ReviewButton.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo (0,1800)");

		WebElement RatingStarsSection = driver.findElement(By.cssSelector(".control.review-control-vote"));

		Thread.sleep(2000);

		System.out.println(RatingStarsSection.findElement(By.tagName("label")).getSize());

		String[] ids = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };

		int RandomIndex = rand.nextInt(ids.length);

		WebElement element = driver.findElement(By.id(ids[RandomIndex]));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);

		WebElement NickNameField = driver.findElement(By.id("nickname_field"));
		NickNameField.sendKeys("Roro");

		WebElement SummaryField = driver.findElement(By.id("summary_field"));
		SummaryField.sendKeys("Hi There");

		WebElement ReviewField = driver.findElement(By.id("review_field"));
		ReviewField.sendKeys("very good item");

		WebElement SubmitButton = driver.findElement(By.cssSelector("button[class='action submit primary']"));

		SubmitButton.click();

		String ActualTextForSubmitReview = driver.findElement(By.cssSelector(".message-success.success.message"))
				.getText();

		String ExpectedTextforSubmitReview = "You submitted your review for moderation.";

		Assert.assertEquals(ActualTextForSubmitReview, ExpectedTextforSubmitReview);

	}
}
