package de.retest.recheck.example;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import de.retest.web.RecheckWebOptions;
import de.retest.web.selenium.By;
import de.retest.web.selenium.RecheckDriver;

@Disabled
class MyUnbreakableSeleniumTest {

	RecheckDriver driver;

	@BeforeEach
	void setup() {
		final RecheckWebOptions reopts = RecheckWebOptions.builder().enableReportUpload().build();
		final ChromeOptions opts = new ChromeOptions();
		opts.addArguments(
				// Enable headless mode for faster execution.
				"--headless",
				// Use Chrome in container-based Travis CI enviroment (see https://docs.travis-ci.com/user/chrome#Sandboxing).
				"--no-sandbox",
				// Fix window size for stable results.
				"--window-size=1200,800" );
		driver = new RecheckDriver( new ChromeDriver( opts ), reopts );
	}

	@Test
	public void login() throws Exception {
		final String url = Paths.get( "src/test/resources/demo-app.html" ).toUri().toURL().toString();
		driver.get( url );

		driver.findElement( By.id( "username" ) ).sendKeys( "Simon" );
		driver.findElement( By.id( "password" ) ).sendKeys( "secret" );
		driver.findElement( By.id( "sign-in" ) ).click();
	}

	@AfterEach
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}
