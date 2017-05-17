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
public class UserListController {
        @RequestMapping("/user_list")
        public String user_list(Map<String, Object> model) {
                List<User> userList = createTestUserList();
                User user = new User("admin", "Admin", "admin@bestlib.hu", "06702222222", "Budapest", "admin");
                //User user = new User("valaki", "Valaki", "Valaki@bestlib.hu", "06702222222", "Budapest", "valami");
                model.put("userList", userList);
                model.put("user", user);
                return "user_list";
        }

        private List<User> createTestUserList() {
                List<User> userList = new ArrayList<>();
                User user1 = new User("sheenjek", "Vickey R. Frye", "VickeyRFrye@teleworm.us", "434-454-3937",
                                "1167 Worley Avenue 25", "66ZkYm");
                User user2 = new User("malheur", "Micah S. Church", "malheur@Smth.com", "830-249-0849",
                                "1401 Morris Street 1", "uYq7t3");
                User user3 = new User("rogue", "Frédérique Abma", "FrederiqueAbma@teleworm.us", "615-525-9635",
                                "4001 Green Street 5", "2ZbD9a");
                User user4 = new User("haley", "Iona Verhaag", "IonaVerhaag@armyspy.com", "040-536-400",
                                "5541 Parmova 17", "Z4gTK8");
                User user5 = new User("rosie", "Hedvige Daigneault", "HedvigeDaigneault@rhyta.com", "051-363-579",
                                "2223 Jurovski Dol 5", "V4yeME");
                User user6 = new User("norman", "Jun Fu", "JunFu@dayrep.com", "031-851-725",
                                "220 Skofja Loka Tavcarjeva 97", "EEpH3j");
                User user7 = new User("poppy", "Jens Heilmann", "JensHeilmann@teleworm.us", "031-397-528",
                                "6274 Smarje Tavcarjeva 9", "PdLq9L");
                User user8 = new User("eclipse", "Burnell Boncoeur", "BurnellBoncoeur@rhyta.com", "070-393-507",
                                "3314 Braslovce Trg revolucije 13", "qVzt5Z");
                User user9 = new User("saint", "Callum James", "CallumJames@dayrep.com", "031-085-623", "9224 Turnisce",
                                "Z73f4J");
                User user10 = new User("cocoa", "Bernd Herzog", "BerndHerzog@teleworm.us", "051-288-941",
                                "2331 Pragersko Turjaska 35", "aU7d9G");
                User user11 = new User("qt312", "Ariam Mewael", "AriamMewael@rhyta.com", "051-554-891",
                                "3264 Sveti Stefan Dunajska 113", "ZQj8E9");
                User user12 = new User("Aquarius", "Jimmy S. Barrera", "JimmySBarrera@jourrapide.com", "609-641-7808",
                                "2815 Moonlight Drive", "Bobeil");

                user1.setPaidAmount(315);
                user2.setPaidAmount(150);
                user3.setPaidAmount(0);
                user4.setPaidAmount(50);
                user5.setPaidAmount(0);
                user6.setPaidAmount(465);
                user7.setPaidAmount(175);
                user8.setPaidAmount(235);
                user9.setPaidAmount(250);
                user10.setPaidAmount(205);
                user11.setPaidAmount(320);
                user12.setPaidAmount(0);

                user1.setFeeAmount(315);
                user2.setFeeAmount(100);
                user3.setFeeAmount(930);
                user4.setFeeAmount(100);
                user5.setFeeAmount(0);
                user6.setFeeAmount(400);
                user7.setFeeAmount(50);
                user8.setFeeAmount(0);
                user9.setFeeAmount(0);
                user10.setFeeAmount(35);
                user11.setFeeAmount(5);
                user12.setFeeAmount(10);

                userList.add(user1);
                userList.add(user2);
                userList.add(user3);
                userList.add(user4);
                userList.add(user5);
                userList.add(user6);
                userList.add(user7);
                userList.add(user8);
                userList.add(user9);
                userList.add(user10);
                userList.add(user11);
                userList.add(user12);

                return userList;
        }
}
