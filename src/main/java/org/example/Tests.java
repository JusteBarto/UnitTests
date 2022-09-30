package org.example;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Tests {

    private static String email;
    private static String password;
    private static FirefoxDriver driver;

    @BeforeClass
    public static void createAccount() {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Kanta\\Downloads\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");

        Random random = new Random();
        int x = random.nextInt(5000);
        email = x + "@gmail.com";
        password = x + "pass";
        driver.findElement(By.xpath("//a[text() = \"Register\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"inputs\"]//input[@value=\"M\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"inputs\"]//input[@id = \"FirstName\"]")).sendKeys("Vardenis");
        driver.findElement(By.xpath("//div[@class=\"inputs\"]//input[@id = \"LastName\"]")).sendKeys("Pavardenis");
        driver.findElement(By.xpath("//div[@class=\"inputs\"]//input[@id = \"Email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//div[@class=\"inputs\"]//input[@id = \"Password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//div[@class=\"inputs\"]//input[@id = \"ConfirmPassword\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id = \"register-button\"]")).click();

        driver.quit();
    }

    @Test
    public void Test1() throws FileNotFoundException, InterruptedException {
        String item;
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//a[@href = \"/login\"]")).click();
        driver.findElement(By.xpath("//input[@id = \"Email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id = \"Password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value = \"Log in\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"listbox\"] //a[@href = \"/digital-downloads\"]")).click();

        File file = new File("C:\\Users\\Kanta\\IdeaProjects\\withUnitTests\\src\\main\\java\\textFiles\\data1.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            item = sc.nextLine();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[text() = '"+item+"']/../..//input")).click();
        }

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()=\"Shopping cart\"]")).click();
        driver.findElement(By.xpath("//input[@name = \"termsofservice\"]")).click();
        driver.findElement(By.xpath("//button[@id = \"checkout\"]")).click();
        if(driver.findElements(By.xpath("//select[@class=\"address-select valid\"]")).isEmpty()){
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_City\"]")).sendKeys("First City");
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_Address1\"]")).sendKeys("First Address");
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_ZipPostalCode\"]")).sendKeys("554422");
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_PhoneNumber\"]")).sendKeys("123456789");
            driver.findElement(By.xpath("//select[@id = \"BillingNewAddress_CountryId\"]")).click();
            driver.findElement(By.xpath("//option[@value = \"1\"]")).click();
        }
        driver.findElement(By.xpath("//input[@value = \"Continue\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@onClick= \"PaymentMethod.save()\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@onClick= \"PaymentInfo.save()\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value = \"Confirm\"]")).click();
        Thread.sleep(2000);
        if(driver.findElement(By.xpath("//strong")).getText().equals("Your order has been successfully processed!"))
            System.out.println("1st order was made");
        else System.out.println("1st order wasn't made");

        driver.quit();

    }

    @Test
    public void Test2() throws InterruptedException, FileNotFoundException {
        String item;
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//a[@href = \"/login\"]")).click();
        driver.findElement(By.xpath("//input[@id = \"Email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id = \"Password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value = \"Log in\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"listbox\"] //a[@href = \"/digital-downloads\"]")).click();

        File file = new File("C:\\Users\\Kanta\\IdeaProjects\\withUnitTests\\src\\main\\java\\textFiles\\data2.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            item = sc.nextLine();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[text() = '"+item+"']/../..//input")).click();
        }

        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()=\"Shopping cart\"]")).click();
        driver.findElement(By.xpath("//input[@name = \"termsofservice\"]")).click();
        driver.findElement(By.xpath("//button[@id = \"checkout\"]")).click();

        Thread.sleep(1000);
        if(driver.findElements(By.xpath("//select[@id=\"billing-address-select\"]")).isEmpty()){
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_City\"]")).sendKeys("First City");
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_Address1\"]")).sendKeys("First Address");
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_ZipPostalCode\"]")).sendKeys("554422");
            driver.findElement(By.xpath("//input [@id = \"BillingNewAddress_PhoneNumber\"]")).sendKeys("123456789");
            driver.findElement(By.xpath("//select[@id = \"BillingNewAddress_CountryId\"]")).click();
            driver.findElement(By.xpath("//option[@value = \"1\"]")).click();
        }
        driver.findElement(By.xpath("//input[@value = \"Continue\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@onClick= \"PaymentMethod.save()\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@onClick= \"PaymentInfo.save()\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value = \"Confirm\"]")).click();
        Thread.sleep(2000);
        if(driver.findElement(By.xpath("//strong")).getText().equals("Your order has been successfully processed!"))
            System.out.println("2nd order was made");
        else System.out.println("2nd order wasn't made");

        driver.quit();
    }
}
