package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.Borrow;
import hu.elteik.projecttools.libmgmt.data.entity.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Data Access Object interface. Used to fetch from and insert into the "borrows" table.
 */
@Transactional
public interface BorrowDao extends CrudRepository<Borrow, Long> {
    /**
     * Gets a Borrow by matching Copy. <br />
     * SQL equivalent: "SELECT * FROM borrows WHERE copy_id=(parameter)copy.id"
     * @param copy a Copy instance
     * @return a new Borrow instance containing values fetched from the "borrows" table.
     */
    Borrow findByCopy(Copy copy);
}
