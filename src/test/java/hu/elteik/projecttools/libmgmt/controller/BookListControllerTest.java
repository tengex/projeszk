package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.entity.Book;
import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by aron51 on 2017. 05. 22..
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class BookListControllerTest {


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
    public void addBookShouldSucceed() throws Exception {
        mockMvc.perform(post("/add_book").with(csrf())
                .param("title", "testTitle")
                .param("author", "test")
                .param("isbn", "123456")
                .param("publisher", "test")
                .param("year", "2017")
                .param("subtitle", "")
                .param("copies", "")
                .param("details", "420 oldal")
                .param("addBookCopyCount","1")
        ).andExpect(redirectedUrl("/book_list"));
    }

    @Test
    public void getListOfBooksShouldReturnListOfBooks() throws Exception {

        mockMvc.perform(get("/book_list"))
                .andExpect(model().attribute("bookList", hasItems(
                        allOf(
                                hasProperty("title", is("A víz mélyén"))
                        ),
                        allOf(
                                hasProperty("title", is("A lány akinek nincs múltja"))
                        ),
                        allOf(
                                hasProperty("title", is("testTitle"))
                        )
                )))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ROLE_LIBRARIAN" })
    public void addCopiesShouldSucceed() throws Exception {
        mockMvc.perform(post("/add_copies").with(csrf())
                .param("addCopyBookId","2")
                .param("addCopyCount","200")
        ).andExpect(redirectedUrl("/book_list"));
    }

    @Test
    public void addBookShouldRedirectToLoginPageWithoutAuth()throws Exception{
        mockMvc.perform(post("/add_book").with(csrf())
                .param("title", "testTitle")
                .param("author", "test")
                .param("isbn", "123456")
                .param("publisher", "test")
                .param("year", "2017")
                .param("subtitle", "")
                .param("copies", "")
                .param("details", "420 oldal")
                .param("addBookCopyCount","1")
        ).andExpect(redirectedUrl("http://localhost/login"));
    }

}
