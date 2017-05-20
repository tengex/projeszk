package hu.elteik.projecttools.libmgmt.service;

import hu.elteik.projecttools.libmgmt.data.dao.*;
import hu.elteik.projecttools.libmgmt.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Bázis on 2017. 04. 14..
 */
@Service
@Transactional
public class MainService {
    private static final Logger logger = Logger.getLogger(MainService.class.getName());

    @Autowired
    private PreferenceDao preferenceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private CopyDao copyDao;

    @Autowired
    private LibraryDao libraryDao;


    @PostConstruct
    public void init() {
        //initialization goes here; todo: some exception handling if these fail
        insertTestValues();
        testDbValues();
        logger.info("Application started");
    }

    private void testDbValues() {
        for (Book b : bookDao.findAll()) { //get all persisted books
            logger.info(b.getTitle());
            for (Copy c : b.getCopies()) //get all persisted copies
                logger.info(c.getCopyId() + " " + c.getCopyStatus());
        }
        User u = userDao.findByUsername("user1"); //get a specific persisted user
        logger.info(u.getFullName());
        logger.info(u.getBorrows().size() + "");
        for (Borrow b : u.getBorrows()) //get all borrows of selected user
            logger.info(b.getBorrowDate() + " " + b.getCopy().getCopyId());

        for (Appointment a : appointmentDao.findAll())
            logger.info(a.getUser().getFullName() + " " + a.getBook().getTitle());
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private void insertTestValues() {
        libraryDao.save(new Library("BestLib", "libaddress", new Date(), "BestLib - best library ever")); //saves library data
        Book book = new Book("isbnstr1", "details1", 2010, "publisher1", "subtitle1", "title1", "author1", null); //book instance, no copies given
        Set<Copy> copies = new HashSet<>();
        for (int i = 0; i < 5; i++)
            copies.add(new Copy());
        //bookDao.save(book); //Optional: you can save books before adding copies
        book.setCopies(copies);
        copyDao.save(copies); //you need to save existing copies first
        bookDao.save(book); //saves updated book instance, with now existing copies

        User user = new User("user1", "User 1", "user1@mail.com", null, null, bCryptPasswordEncoder.encode("user1"), "ROLE_USER"); //user instance
        userDao.save(user); //need to save an user BEFORE adding borrows - else it throws PersistenceException
        Set<Copy> copies2 = bookDao.findOne(1L).getCopies(); //get copies by ID (Long)
        Set<Borrow> borrows = new HashSet<>();
        for (Copy c : copies2)
            borrows.add(new Borrow(user, c, new Date()));
        user.setBorrows(borrows);
        borrowDao.save(borrows); //you need to save existing borrows before user to avoid PersistenceException
        userDao.save(user); //save user instance
        userDao.save(new User("admin", "Adminisztrátor", "admin@bestlib.eu", null, null, bCryptPasswordEncoder.encode("admin"), "ROLE_ADMIN"));
        Appointment a = new Appointment(book, user);
        appointmentDao.save(a);
    }
}
