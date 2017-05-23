package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by aron51 on 2017. 05. 22..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultContextControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


    @Test
    public void GetPerformToRootShouldRedirectToIndex() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void adminAuthenticationShouldSucceed() throws Exception {
        User user = new User("admin", null, null, null, null,  "admin","ROLE_ADMIN");
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

        mockMvc.perform(get("/index")
                .principal(testingAuthenticationToken))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentUser", is(user.toString())));
    }


    @Test
    public void nonExistingPageShouldReturnClientError() throws Exception {
        mockMvc.perform(get("/asd"))
                .andExpect(status().is4xxClientError());
    }

}
