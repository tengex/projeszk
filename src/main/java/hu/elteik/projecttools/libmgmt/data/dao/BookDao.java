package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Data Access Object interface. Used to fetch from and insert into the "books" table.
 */
@Transactional
public interface BookDao extends CrudRepository<Book, Long> {
}
