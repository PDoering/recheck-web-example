package de.retest.recheck.example;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;

class ExplicitChecksTest {

	RemoteWebDriver driver;
	Recheck re;

	@BeforeEach
	public void setup() {
		re = new RecheckImpl();
		driver =  new ChromeDriver();
	}

	@Test
	public void check() throws Exception {
		String url = Paths.get( "src/test/resources/demo-app.html" ).toUri().toURL().toString();
		driver.get(url);
		re.check(driver, "init");
		// equivalent to
		re.check(driver.findElement(By.tagName("html")), "init");
	}

	@AfterEach
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}
