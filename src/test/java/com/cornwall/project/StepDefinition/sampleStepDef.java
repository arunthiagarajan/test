package com.cornwall.project.StepDefinition;

import com.cornwall.project.BaseStepDef;
import com.cornwall.project.Utils.WebServiceUtil;
import com.cornwall.project.Pages.logIn;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class sampleStepDef extends BaseStepDef {

    private WebServiceUtil service = new WebServiceUtil();

    @Then("^log into platform using services$")
    public void verify() {

    }

    @And("^Verify the token id is generated$")
    public void sample() {

    }
    @And("^Verify Dashboard page is Displayed$")
    public void dashboard() throws Throwable
    {

    }





    private logIn login = new logIn();

    @Given("^As a seller after registration with credentials available$")
    public void logIn() {
        service.getResponse();
    }


    @Given("^Log In Into Platform$")
    public void launchBroswer() throws Throwable {
        {
            getDriver().get("http://cornwall-platform.s3-website-eu-west-1.amazonaws.com/");
            login.userName().sendKeys("cornwalltest18@gmail.com");
            login.password().sendKeys("Password123#");
            Thread.sleep(2000);
            login.signButton().click();
            Thread.sleep(2000);
            //login.mouseover().click();
        }
    }

}

