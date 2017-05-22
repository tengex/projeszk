package hu.elteik.projecttools.libmgmt.service;

import hu.elteik.projecttools.libmgmt.data.dao.BookDao;
import hu.elteik.projecttools.libmgmt.data.dao.CopyDao;
import hu.elteik.projecttools.libmgmt.data.dao.LibraryDao;
import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import hu.elteik.projecttools.libmgmt.data.entity.Book;
import hu.elteik.projecttools.libmgmt.data.entity.Copy;
import hu.elteik.projecttools.libmgmt.data.entity.Library;
import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aron51 on 2017. 05. 22..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainServiceTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CopyDao copyDao;

    @Autowired
    private LibraryDao libraryDao;


    @Test
    public void findAllBooksShouldReturnAllBooks() {
        List<Book> bookList = (List<Book>) bookDao.findAll();
        assertThat(bookList).hasSize(18);
    }

    @Test
    public void findBookByIdShouldReturnOneBook(){
        Book book = bookDao.findOne(new Long(1));
        assertThat(book.getTitle()).isEqualTo("A víz mélyén");
    }

    @Test
    public void findUserByUsernameShouldReturnOneUser(){
        User user = userDao.findByUsername("user1");
        assertThat(user.getUsername()).isEqualTo("user1");
    }

    @Test(expected=NullPointerException.class)
    public void findNonExistingUserShouldThrowException(){
        User user= userDao.findByUsername("nonExists");
        assertThat(user.getUsername()).isEqualTo("nonExists");
    }

    @Test
    public void findAllCopiesShouldReturn(){
        List<Copy> copies = (List<Copy>) copyDao.findAll();
        assertThat(copies.size()).isEqualTo(541);
    }

    @Test
    public void findTheBestLib(){
        Library library = libraryDao.findByName("BestLib");
        assertThat(library.getDescription()).isEqualTo("BestLib - best library ever");
    }


}
