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
        //testDbValues();
        logger.info("Application started");
    }

    //todo scheduled task which sets expired borrow status and adds delay fee to user!

    private void testDbValues() {
        for (Book b : bookDao.findAll()) { //get all persisted books
            logger.info(b.getTitle());
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
        //Lib
        libraryDao.save(new Library("BestLib", "libaddress", new Date(), "BestLib - best library ever")); //saves library data

        //users
        userDao.save(new User("user1", "User 1", "user1@mail.com", null, null, bCryptPasswordEncoder.encode("user1"), "ROLE_USER"));
        userDao.save(new User("admin", "Adminisztrátor", "admin@bestlib.eu", null, null, bCryptPasswordEncoder.encode("admin"), "ROLE_ADMIN"));

        //books
        Book book = new Book("9786155638589", "367 oldal", 2017, "21. Század Kiadó", "", "A víz mélyén",
                "Paula Hawkins", null);
        Set<Copy> copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9786155676475", "366 oldal", 2017, "Művelt Nép Könyvkiadó", "",
                "A lány akinek nincs múltja", "Kathryn Croft", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789634154686", "160 oldal", 2016, "Móra Ferenc Könyvkiadó Kft", "", "Vidám mesék",
                "Vlagyimir Szutyejev", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789633999721", "352 oldal", 2017, "Könyvmolyképző Kiadó Kft", "",
                "Egy kutya négy élete ", "W. Bruce Cameron", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789632033136", "327 oldal", 2017, "Maecenas Könyvkiadó Kft", "", "Az apartman",
                "Danielle Steel", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789446532824", "568 oldal", 2017, "General Press Kiadó", "", "Kéz a kézben",
                "Nicholas Sparks", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789633245040", "448 oldal", 2015, "Animus Kiadó Kft.", "A skandináv krimi", "Gének",
                "Yrsa Sigurdardóttir", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9786155617126", "526 oldal", 2016, "Művelt Nép Könyvkiadó", "",
                "Egy éjszaka leplezetlenül", "Jodi Ellen Malpas", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789632936420", "208 oldal", 2014, "Athenaeum Kiadó Kft",
                "Magyar írók a felnőtté válásról", "Útra kelni ", "Peiker Éva", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789630796316", "750 oldal", 2016, "Európa Könyvkiadó", "Az örökség III.",
                "Brisingr ", "Christopher Paolini", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789632450957", "470 oldal", 2016, "Könyvmolyképző Kiadó Kft", "", "Csontváros",
                "Clare Cassandra", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789632452616", "260 oldal", 2015, "Könyvmolyképző Kiadó Kft", "",
                "Tizenhárom okom volt...", "Jay Asher", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789630988537", "176 oldal", 2017, "Kossuth Kiadó", "", "A különlegesek regéi",
                "Ransom Riggs", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789634430773", "173 oldal", 2017, "Publio Kiadó", "", "A varázsgömb titka",
                "Lovász Erika", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789632618524", "176 oldal", 2017, "Maxim Könyvkiadó", "", "Gyönyörű pillanat",
                "Jamie McGuire", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("978963403350", "274 oldal", 2017, "Menő Könyvek", "Éretté nyilvánítva", "D.A.C. 6",
                "Kalapos Éva", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);

        book = new Book("9789630988537", "176 oldal", 2017, "Kossuth Kiadó", "", "A különlegesek regéi",
                "Ransom Riggs", null);
        copies = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            copies.add(new Copy());
        }
        book.setCopies(copies);
        copyDao.save(copies);
        bookDao.save(book);
    }
}
