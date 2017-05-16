package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import java.util.*;
import hu.elteik.projecttools.libmgmt.data.entity.*;
import hu.elteik.projecttools.libmgmt.util.*;

/**
 * Created by Bázis on 2017. 04. 14..
 */
@Controller
public class DefaultContextController {
        @RequestMapping("/")
        public String redirectRoot() {
                return "redirect:index";
        }

        @RequestMapping("/index")
        public String index(Map<String, Object> model) {
                model.put("message", "Hello, World!");
                return "index";
        }

        @RequestMapping("/book_list")
        public String book_list(Map<String, Object> model) {
                List<Book> bookList = createTestBookList();
                User user = new User("admin", "Admin", "admin@bestlib.hu", "06702222222", "Budapest", "admin");
                //User user = new User("valaki", "Valaki", "Valaki@bestlib.hu", "06702222222", "Budapest", "valami");
                model.put("bookList", bookList);
                model.put("user", user);
                return "book_list";
        }

        /*@PostMapping("/book_list")
        public String addBook(@ModelAttribute Book book) {
                System.out.println(book);
                return "result";
        }*/

        @RequestMapping("/borrow_list")
        public String borrow_list(Map<String, Object> model) {
                List<BorrowListData> borrowList = createTestBorrowList();
                model.put("borrowList", borrowList);
                return "borrow_list";
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

                Set<Copy> book1Copies = new HashSet<Copy>();
                book1Copies.add(copy2);
                Set<Copy> book2Copies = new HashSet<Copy>();
                book2Copies.add(copy3);
                Set<Copy> book3Copies = new HashSet<Copy>();
                book3Copies.add(copy5);
                book3Copies.add(copy9);
                Set<Copy> book4Copies = new HashSet<Copy>();
                book4Copies.add(copy12);
                Set<Copy> book5Copies = new HashSet<Copy>();
                book5Copies.add(copy1);
                book5Copies.add(copy7);
                Set<Copy> book6Copies = new HashSet<Copy>();
                book6Copies.add(copy8);
                Set<Copy> book7Copies = new HashSet<Copy>();
                book7Copies.add(copy11);
                Set<Copy> book8Copies = new HashSet<Copy>();
                book8Copies.add(copy17);
                Set<Copy> book9Copies = new HashSet<Copy>();
                book9Copies.add(copy13);
                Set<Copy> book10Copies = new HashSet<Copy>();
                book10Copies.add(copy4);
                book10Copies.add(copy22);
                Set<Copy> book11Copies = new HashSet<Copy>();
                book11Copies.add(copy6);
                Set<Copy> book12Copies = new HashSet<Copy>();
                book12Copies.add(copy10);
                Set<Copy> book13Copies = new HashSet<Copy>();
                book13Copies.add(copy20);
                Set<Copy> book14Copies = new HashSet<Copy>();
                book14Copies.add(copy14);
                book14Copies.add(copy21);
                Set<Copy> book15Copies = new HashSet<Copy>();
                book15Copies.add(copy19);
                Set<Copy> book16Copies = new HashSet<Copy>();
                book16Copies.add(copy15);
                book16Copies.add(copy18);
                Set<Copy> book17Copies = new HashSet<Copy>();
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

        private List<BorrowListData> createTestBorrowList() {
                List<BorrowListData> borrowList = new ArrayList<>();

                borrowList.add(new BorrowListData(1L, 2L, "Kathryn Croft", "A lány akinek nincs múltja", "",
                                "9786155676475", new Date(new GregorianCalendar(2017, 4, 17).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 7, 20).getTimeInMillis())));
                borrowList.add(new BorrowListData(2L, 3L, "Vlagyimir Szutyejev", "Vidám mesék", "", "9789634154686",
                                new Date(new GregorianCalendar(2017, 4, 19).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 10).getTimeInMillis())));
                borrowList.add(new BorrowListData(3L, 11L, "Clare Cassandra", "Csontváros", "", "9789632450957",
                                new Date(new GregorianCalendar(2017, 4, 20).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 4).getTimeInMillis())));
                borrowList.add(new BorrowListData(4L, 5L, "Danielle Steel", "Az apartman", "", "9789632033136",
                                new Date(new GregorianCalendar(2017, 4, 13).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 30).getTimeInMillis())));
                borrowList.add(new BorrowListData(5L, 3L, "Vlagyimir Szutyejev", "Vidám mesék", "", "9789634154686",
                                new Date(new GregorianCalendar(2017, 4, 27).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 10).getTimeInMillis())));
                borrowList.add(new BorrowListData(6L, 7L, "Yrsa Sigurdardóttir", "Gének", "A skandináv krimi",
                                "9789633245040", new Date(new GregorianCalendar(2017, 5, 1).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 14).getTimeInMillis())));
                borrowList.add(new BorrowListData(7L, 4L, "W. Bruce Cameron", "Egy kutya négy élete", "",
                                "9789633999721", new Date(new GregorianCalendar(2017, 4, 24).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 30).getTimeInMillis())));
                borrowList.add(new BorrowListData(8L, 9L, "Peiker Éva", "Útra kelni", "Magyar írók a felnőtté válásról",
                                "9789632936420", new Date(new GregorianCalendar(2017, 4, 26).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 20).getTimeInMillis())));
                borrowList.add(new BorrowListData(9L, 16L, "Kalapos Éva", "D.A.C. 6", "Éretté nyilvánítva",
                                "978963403350", new Date(new GregorianCalendar(2017, 5, 4).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 10).getTimeInMillis())));
                borrowList.add(new BorrowListData(10L, 8L, "Jodi Ellen Malpas", "Egy éjszaka leplezetlenül", "",
                                "9786155617126", new Date(new GregorianCalendar(2017, 5, 8).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 13).getTimeInMillis())));
                borrowList.add(new BorrowListData(11L, 16L, "Kalapos Éva", "D.A.C. 6", "Éretté nyilvánítva",
                                "978963403350", new Date(new GregorianCalendar(2017, 5, 10).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 18).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 13).getTimeInMillis())));
                borrowList.add(new BorrowListData(12L, 15L, "Jamie McGuire", "Gyönyörű pillanat", "", "9789632618524",
                                new Date(new GregorianCalendar(2017, 5, 8).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 10).getTimeInMillis())));

                return borrowList;
        }

        /*class BorrowListData {
                private Borrow borrow;
                private Book book;
                private Copy copy;
        
                public BorrowListData(Borrow borrow, Book book, Copy copy) {
                        this.borrow = borrow;
                        this.book = book;
                        this.copy = copy;
                }
        
                public Borrow getBorrow() {
                        return borrow;
                }
        
                public Book getBook() {
                        return book;
                }
        
                public Copy getCopy() {
                        return copy;
                }
        }*/

        class BorrowListData {
                private Long borrowId;
                private Long bookId;
                private String author;
                private String title;
                private String subtitle;
                private String isbn;
                private Date borrowDate;
                private Date expiryDate;
                private Date closeDate;

                public BorrowListData(Long borrowId, Long bookId, String author, String title, String subtitle,
                                String isbn, Date borrowDate, Date expiryDate) {
                        this.borrowId = borrowId;
                        this.bookId = bookId;
                        this.author = author;
                        this.title = title;
                        this.subtitle = subtitle;
                        this.isbn = isbn;
                        this.borrowDate = borrowDate;
                        this.expiryDate = expiryDate;
                }

                public BorrowListData(Long borrowId, Long bookId, String author, String title, String subtitle,
                                String isbn, Date borrowDate, Date expiryDate, Date closeDate) {
                        this.borrowId = borrowId;
                        this.bookId = bookId;
                        this.author = author;
                        this.title = title;
                        this.subtitle = subtitle;
                        this.isbn = isbn;
                        this.borrowDate = borrowDate;
                        this.expiryDate = expiryDate;
                        this.closeDate = closeDate;
                }

                public Long getBorrowId() {
                        return borrowId;
                }

                public Long getBookId() {
                        return bookId;
                }

                public String getAuthor() {
                        return author;
                }

                public String getTitle() {
                        return title;
                }

                public String getSubtitle() {
                        return subtitle;
                }

                public String getIsbn() {
                        return isbn;
                }

                public Date getBorrowDate() {
                        return borrowDate;
                }

                public Date getExpiryDate() {
                        return expiryDate;
                }

                public Date getCloseDate() {
                        return closeDate;
                }
        }
}
