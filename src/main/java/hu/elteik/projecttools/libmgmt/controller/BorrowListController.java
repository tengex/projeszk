package hu.elteik.projecttools.libmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import hu.elteik.projecttools.libmgmt.data.entity.*;

/**
 * Admin / user kezelése auth nélkül:
 * 
 * User user = new User("admin", "Admin", "admin@bestlib.hu", "06702222222", "Budapest", "admin");
 * vagy
 * User user = new User("valaki", "Valaki", "Valaki@bestlib.hu", "06702222222", "Budapest", "valami");
 * 
 * model.put("user", user);
 * 
 * Thymeleaf template jelenleg ez alapján dönti el a felhasználó jogkörét
 */

@Controller
public class BorrowListController {
        @RequestMapping("/borrow_list")
        public String borrow_list(Map<String, Object> model) {
                List<BorrowListData> borrowList = createTestBorrowList();
                User user = new User("admin", "Admin", "admin@bestlib.hu", "06702222222", "Budapest", "admin");
                //User user = new User("valaki", "Valaki", "Valaki@bestlib.hu", "06702222222", "Budapest", "valami");
                model.put("borrowList", borrowList);
                model.put("user", user);
                return "borrow_list";
        }

        private List<BorrowListData> createTestBorrowList() {
                List<BorrowListData> borrowList = new ArrayList<>();

                borrowList.add(new BorrowListData(1L, 2L, "haley", "Kathryn Croft", "A lány akinek nincs múltja", "",
                                "9786155676475", new Date(new GregorianCalendar(2017, 4, 17).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 7, 20).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 4, 26).getTimeInMillis())));
                borrowList.add(new BorrowListData(2L, 3L, "cocoa", "Vlagyimir Szutyejev", "Vidám mesék", "",
                                "9789634154686", new Date(new GregorianCalendar(2017, 4, 19).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 10).getTimeInMillis())));
                borrowList.add(new BorrowListData(3L, 11L, "poppy", "Clare Cassandra", "Csontváros", "",
                                "9789632450957", new Date(new GregorianCalendar(2017, 4, 20).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 4).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 1).getTimeInMillis())));
                borrowList.add(new BorrowListData(4L, 5L, "sheenjek", "Danielle Steel", "Az apartman", "",
                                "9789632033136", new Date(new GregorianCalendar(2017, 4, 13).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 30).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 4, 22).getTimeInMillis())));
                borrowList.add(new BorrowListData(5L, 3L, "qt312", "Vlagyimir Szutyejev", "Vidám mesék", "",
                                "9789634154686", new Date(new GregorianCalendar(2017, 4, 27).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 10).getTimeInMillis())));
                borrowList.add(new BorrowListData(6L, 7L, "eclipse", "Yrsa Sigurdardóttir", "Gének",
                                "A skandináv krimi", "9789633245040",
                                new Date(new GregorianCalendar(2017, 5, 1).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 14).getTimeInMillis())));
                borrowList.add(new BorrowListData(7L, 4L, "sheenjek", "W. Bruce Cameron", "Egy kutya négy élete", "",
                                "9789633999721", new Date(new GregorianCalendar(2017, 4, 24).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 30).getTimeInMillis())));
                borrowList.add(new BorrowListData(8L, 9L, "malheur", "Peiker Éva", "Útra kelni",
                                "Magyar írók a felnőtté válásról", "9789632936420",
                                new Date(new GregorianCalendar(2017, 4, 26).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 20).getTimeInMillis())));
                borrowList.add(new BorrowListData(9L, 16L, "norman", "Kalapos Éva", "D.A.C. 6", "Éretté nyilvánítva",
                                "978963403350", new Date(new GregorianCalendar(2017, 5, 4).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 10).getTimeInMillis())));
                borrowList.add(new BorrowListData(10L, 8L, "saint", "Jodi Ellen Malpas", "Egy éjszaka leplezetlenül",
                                "", "9786155617126", new Date(new GregorianCalendar(2017, 5, 8).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 13).getTimeInMillis())));
                borrowList.add(new BorrowListData(11L, 16L, "norman", "Kalapos Éva", "D.A.C. 6", "Éretté nyilvánítva",
                                "978963403350", new Date(new GregorianCalendar(2017, 5, 10).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 18).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 5, 13).getTimeInMillis())));
                borrowList.add(new BorrowListData(12L, 15L, "qt312", "Jamie McGuire", "Gyönyörű pillanat", "",
                                "9789632618524", new Date(new GregorianCalendar(2017, 5, 8).getTimeInMillis()),
                                new Date(new GregorianCalendar(2017, 6, 10).getTimeInMillis())));

                return borrowList;
        }

        class BorrowListData {
                private Long borrowId;
                private Long bookId;
                private String username;
                private String author;
                private String title;
                private String subtitle;
                private String isbn;
                private Date borrowDate;
                private Date expiryDate;
                private Date closeDate;

                public BorrowListData(Long borrowId, Long bookId, String username, String author, String title,
                                String subtitle, String isbn, Date borrowDate, Date expiryDate) {
                        this.borrowId = borrowId;
                        this.bookId = bookId;
                        this.username = username;
                        this.author = author;
                        this.title = title;
                        this.subtitle = subtitle;
                        this.isbn = isbn;
                        this.borrowDate = borrowDate;
                        this.expiryDate = expiryDate;
                }

                public BorrowListData(Long borrowId, Long bookId, String username, String author, String title,
                                String subtitle, String isbn, Date borrowDate, Date expiryDate, Date closeDate) {
                        this.borrowId = borrowId;
                        this.bookId = bookId;
                        this.username = username;
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

                public String getUsername() {
                        return username;
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
