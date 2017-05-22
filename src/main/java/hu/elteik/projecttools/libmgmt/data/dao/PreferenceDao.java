package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.Preference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 14..
 */

/**
 * Data Access Object interface. Used to fetch from and insert into the "preferences" table.
 */
@Transactional
public interface PreferenceDao extends CrudRepository<Preference, String> {

}
