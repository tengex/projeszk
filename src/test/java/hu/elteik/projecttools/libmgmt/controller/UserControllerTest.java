package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by aron51 on 2017. 05. 22..
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class UserControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void registerUserShouldRedirectToIndex() throws Exception {
        RequestBuilder request = post("/registration")
                .param("username", "test1")
                .param("password", "test1")
                .param("email", "test@test.hu")
                .with(csrf());

        mockMvc
                .perform(request)
                .andExpect(redirectedUrl("index"));
    }


    @Test
    public void loginUserShouldSucceed() throws Exception {
        RequestBuilder request = post("/login")
                .param("username", "test1")
                .param("password", "test1")
                .with(csrf());
        mockMvc
                .perform(request)
                .andExpect(redirectedUrl("/"));
    }


    @Test
    public void logoutUser() throws Exception {
        User user = new User("admin", null, null, null, null, "admin", "ROLE_ADMIN");
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
        mockMvc.perform(get("/index")
                .principal(testingAuthenticationToken))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void loginUserShouldFail() throws Exception {
        RequestBuilder request = post("/login")
                .param("username", "admin")
                .param("password", "asd")
                .with(csrf());
        mockMvc
                .perform(request)
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void registerExistingUserShouldRedirectToRegistration() throws Exception {
        RequestBuilder request = post("/registration")
                .param("username", "user1")
                .param("password", "test1")
                .param("email", "test@test.hu")
                .with(csrf());

        mockMvc
                .perform(request)
                .andExpect(status().isOk());
    }

}

