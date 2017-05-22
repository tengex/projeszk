package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Data Access Object interface. Used to fetch from and insert into the "library" table.
 */
@Transactional
public interface LibraryDao extends CrudRepository<Library, Long> {
    /**
     * Gets a Library by matching name. <br />
     * SQL equivalent: "SELECT * FROM library WHERE name=(parameter)name"
     *
     * @param name name of the library
     * @return A Library instance
     */
    Library findByName(String name);
}
