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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by aron51 on 2017. 05. 23..
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
public class BorrowListControllerTest {

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
    @WithMockUser(username = "admin", authorities = { "ROLE_LIBRARIAN" })
    public void getBorrowListShouldReturnEmptyBorrowList() throws Exception {
        mockMvc.perform(get("/borrow_list"))
                .andExpect(model().attribute("borrowList", hasSize(0)))
                .andExpect(status().isOk());

    }

    @Test
    public void getBorrowsWithoutAuthShouldRedirect() throws Exception {
        mockMvc.perform(get("/borrow_list"))
                .andExpect(redirectedUrl("http://localhost/login"));

    }


}
