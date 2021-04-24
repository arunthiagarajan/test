package com.cornwall.project.Pages;

import com.cornwall.project.framework.AbstractPage;
import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.WebElement;

public class logIn extends AbstractPage {

    public WebElement userName() {
        return waitForUnstableElement(By.id("inputEmail"));
    }

    public WebElement password() {
        return waitForUnstableElement(By.id("inputPassword"));
    }

    public WebElement signButton()
    {
        return waitForUnstableElement(By.xpath("/html/body/div/router-view/div/div/div[1]/div[1]/div/form/div/div[5]/button[1]"));
    }

    public WebElement mouseover()
    {
        return waitForUnstableElement(By.xpath("/html/body/div[1]/router-view/div[1]/div[1]/div/nav/div/ul/li[3]/a/i"));
    }
}
