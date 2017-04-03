package uo.asw.steps;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.Application;
import uo.asw.dashboard.MainController;

import java.util.List;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LoginSteps {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Given("^a list of users:$")
    public void a_list_of_users(List<User> users) throws Throwable {
        for (User u : users) {
            System.out.println("Inserting user..." + u.name + " - " + u.password);
        }
    }

    @When("^I login with name \"(.+)\" and password \"(.+)\"$")
    public void i_login_with_name_and_password(String name, String password) throws Throwable {
        System.out.println("Login with values..." + name + " - " + password);
    }

    @Then("^I receive a welcome message$")
    public void i_receive_a_welcome_message() throws Throwable {
        System.out.println("Wellcome value received");
    }

    public static class User {
        private String name;
        private String password;
    }
}
