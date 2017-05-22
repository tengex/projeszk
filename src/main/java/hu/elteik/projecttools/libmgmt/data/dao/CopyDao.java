package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Data Access Object interface. Used to fetch from and insert into the "copies" table.
 */
@Transactional
public interface CopyDao extends CrudRepository<Copy, Long> {
}
