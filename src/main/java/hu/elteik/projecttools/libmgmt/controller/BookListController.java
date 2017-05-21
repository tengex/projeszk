package hu.elteik.projecttools.libmgmt.controller;

import hu.elteik.projecttools.libmgmt.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

import hu.elteik.projecttools.libmgmt.data.entity.*;
import hu.elteik.projecttools.libmgmt.util.*;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Admin / user kezelése auth nélkül:
 * <p>
 * User user = new User("admin", "Admin", "admin@bestlib.hu", "06702222222", "Budapest", "admin");
 * vagy
 * User user = new User("valaki", "Valaki", "Valaki@bestlib.hu", "06702222222", "Budapest", "valami");
 * <p>
 * model.put("user", user);
 * <p>
 * Thymeleaf template jelenleg ez alapján dönti el a felhasználó jogkörét
 */

@Controller
public class BookListController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/book_list", method = RequestMethod.GET)
    public String book_list(Map<String, Object> model, Principal principal) {
        List<Book> bookList = createTestBookList();
        model.put("bookList", bookList);
        if (principal != null) {
            model.put("user", userDao.findByUsername(principal.getName()));
            model.put("currentUser", principal.getName());
        }
        return "book_list";
    }

    private List<Book> createTestBookList() {
        List<Book> bookList = new ArrayList<>();

        Copy copy1 = new Copy();
        copy1.setCopyId(1L);
        copy1.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy2 = new Copy();
        copy2.setCopyId(2L);
        copy2.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy3 = new Copy();
        copy3.setCopyId(3L);
        copy3.setCopyStatus(CopyStatus.BORROWED);
        Copy copy4 = new Copy();
        copy4.setCopyId(4L);
        copy4.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy5 = new Copy();
        copy5.setCopyId(5L);
        copy5.setCopyStatus(CopyStatus.BORROWED);
        Copy copy6 = new Copy();
        copy6.setCopyId(6L);
        copy6.setCopyStatus(CopyStatus.BORROWED);
        Copy copy7 = new Copy();
        copy7.setCopyId(7L);
        copy7.setCopyStatus(CopyStatus.BORROWED);
        Copy copy8 = new Copy();
        copy8.setCopyId(8L);
        copy8.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy9 = new Copy();
        copy9.setCopyId(9L);
        copy9.setCopyStatus(CopyStatus.BORROWED);
        Copy copy10 = new Copy();
        copy10.setCopyId(10L);
        copy10.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy11 = new Copy();
        copy11.setCopyId(11L);
        copy11.setCopyStatus(CopyStatus.BORROWED);
        Copy copy12 = new Copy();
        copy12.setCopyId(12L);
        copy12.setCopyStatus(CopyStatus.BORROWED);
        Copy copy13 = new Copy();
        copy13.setCopyId(13L);
        copy13.setCopyStatus(CopyStatus.BORROWED);
        Copy copy14 = new Copy();
        copy14.setCopyId(14L);
        copy14.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy15 = new Copy();
        copy15.setCopyId(15L);
        copy15.setCopyStatus(CopyStatus.BORROWED);
        Copy copy17 = new Copy();
        copy17.setCopyId(17L);
        copy17.setCopyStatus(CopyStatus.BORROWED);
        Copy copy18 = new Copy();
        copy18.setCopyId(18L);
        copy18.setCopyStatus(CopyStatus.BORROWED);
        Copy copy19 = new Copy();
        copy19.setCopyId(19L);
        copy19.setCopyStatus(CopyStatus.BORROWED);
        Copy copy20 = new Copy();
        copy20.setCopyId(20L);
        copy20.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy21 = new Copy();
        copy21.setCopyId(21L);
        copy21.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy22 = new Copy();
        copy22.setCopyId(22L);
        copy22.setCopyStatus(CopyStatus.AVAILABLE);
        Copy copy24 = new Copy();
        copy24.setCopyId(24L);
        copy24.setCopyStatus(CopyStatus.AVAILABLE);

        Set<Copy> book1Copies = new HashSet<>();
        book1Copies.add(copy2);
        Set<Copy> book2Copies = new HashSet<>();
        book2Copies.add(copy3);
        Set<Copy> book3Copies = new HashSet<>();
        book3Copies.add(copy5);
        book3Copies.add(copy9);
        Set<Copy> book4Copies = new HashSet<>();
        book4Copies.add(copy12);
        Set<Copy> book5Copies = new HashSet<>();
        book5Copies.add(copy1);
        book5Copies.add(copy7);
        Set<Copy> book6Copies = new HashSet<>();
        book6Copies.add(copy8);
        Set<Copy> book7Copies = new HashSet<>();
        book7Copies.add(copy11);
        Set<Copy> book8Copies = new HashSet<>();
        book8Copies.add(copy17);
        Set<Copy> book9Copies = new HashSet<>();
        book9Copies.add(copy13);
        Set<Copy> book10Copies = new HashSet<>();
        book10Copies.add(copy4);
        book10Copies.add(copy22);
        Set<Copy> book11Copies = new HashSet<>();
        book11Copies.add(copy6);
        Set<Copy> book12Copies = new HashSet<>();
        book12Copies.add(copy10);
        Set<Copy> book13Copies = new HashSet<>();
        book13Copies.add(copy20);
        Set<Copy> book14Copies = new HashSet<>();
        book14Copies.add(copy14);
        book14Copies.add(copy21);
        Set<Copy> book15Copies = new HashSet<>();
        book15Copies.add(copy19);
        Set<Copy> book16Copies = new HashSet<>();
        book16Copies.add(copy15);
        book16Copies.add(copy18);
        Set<Copy> book17Copies = new HashSet<>();
        book17Copies.add(copy24);

        Book book1 = new Book("9786155638589", "367", 2017, "21. Század Kiadó", "", "A víz mélyén",
                "Paula Hawkins", book1Copies);
        Book book2 = new Book("9786155676475", "366", 2017, "Művelt Nép Könyvkiadó", "",
                "A lány akinek nincs múltja", "Kathryn Croft", book2Copies);
        Book book3 = new Book("9789634154686", "160", 2016, "Móra Ferenc Könyvkiadó Kft", "", "Vidám mesék",
                "Vlagyimir Szutyejev", book3Copies);
        Book book4 = new Book("9789633999721", "352", 2017, "Könyvmolyképző Kiadó Kft", "",
                "Egy kutya négy élete ", "W. Bruce Cameron", book4Copies);
        Book book5 = new Book("9789632033136", "327", 2017, "Maecenas Könyvkiadó Kft", "", "Az apartman",
                "Danielle Steel", book5Copies);
        Book book6 = new Book("9789446532824", "568", 2017, "General Press Kiadó", "", "Kéz a kézben",
                "Nicholas Sparks", book6Copies);
        Book book7 = new Book("9789633245040", "448", 2015, "Animus Kiadó Kft.", "A skandináv krimi", "Gének",
                "Yrsa Sigurdardóttir", book7Copies);
        Book book8 = new Book("9786155617126", "526", 2016, "Művelt Nép Könyvkiadó", "",
                "Egy éjszaka leplezetlenül", "Jodi Ellen Malpas", book8Copies);
        Book book9 = new Book("9789632936420", "208", 2014, "Athenaeum Kiadó Kft",
                "Magyar írók a felnőtté válásról", "Útra kelni ", "Peiker Éva", book9Copies);
        Book book10 = new Book("9789630796316", "750", 2016, "Európa Könyvkiadó", "Az örökség III.",
                "Brisingr ", "Christopher Paolini", book10Copies);
        Book book11 = new Book("9789632450957", "470", 2016, "Könyvmolyképző Kiadó Kft", "", "Csontváros",
                "Clare Cassandra", book11Copies);
        Book book12 = new Book("9789632452616", "260", 2015, "Könyvmolyképző Kiadó Kft", "",
                "Tizenhárom okom volt...", "Jay Asher", book12Copies);
        Book book13 = new Book("9789630988537", "176", 2017, "Kossuth Kiadó", "", "A különlegesek regéi",
                "Ransom Riggs", book13Copies);
        Book book14 = new Book("9789634430773", "173", 2017, "Publio Kiadó", "", "A varázsgömb titka",
                "Lovász Erika", book14Copies);
        Book book15 = new Book("9789632618524", "176", 2017, "Maxim Könyvkiadó", "", "Gyönyörű pillanat",
                "Jamie McGuire", book15Copies);
        Book book16 = new Book("978963403350", "274", 2017, "Menő Könyvek", "Éretté nyilvánítva", "D.A.C. 6",
                "Kalapos Éva", book16Copies);
        Book book17 = new Book("9789630988537", "176", 2017, "Kossuth Kiadó", "", "A különlegesek regéi",
                "Ransom Riggs", book17Copies);
        book1.setBookId(1L);
        book2.setBookId(2L);
        book3.setBookId(3L);
        book4.setBookId(4L);
        book5.setBookId(5L);
        book6.setBookId(6L);
        book7.setBookId(7L);
        book8.setBookId(8L);
        book9.setBookId(9L);
        book10.setBookId(10L);
        book11.setBookId(11L);
        book12.setBookId(12L);
        book13.setBookId(13L);
        book14.setBookId(14L);
        book15.setBookId(15L);
        book16.setBookId(16L);
        book17.setBookId(17L);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        bookList.add(book7);
        bookList.add(book8);
        bookList.add(book9);
        bookList.add(book10);
        bookList.add(book11);
        bookList.add(book12);
        bookList.add(book13);
        bookList.add(book14);
        bookList.add(book15);
        bookList.add(book16);
        bookList.add(book17);

        return bookList;
    }
}
