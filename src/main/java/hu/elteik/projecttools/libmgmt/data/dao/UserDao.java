package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Data Access Object interface. Used to fetch from and insert into the "users" table.
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    /**
     * Gets a User by matching username. <br />
     * SQL equivalent: "SELECT * FROM users WHERE username=(parameter)username"
     * @param username username
     * @return A new User instance containing values fetched from the "users" table.
     */
    User findByUsername(String username);
}
