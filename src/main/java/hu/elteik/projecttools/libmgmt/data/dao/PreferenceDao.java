package hu.elteik.projecttools.libmgmt.data.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import hu.elteik.projecttools.libmgmt.data.entity.Preference;

/**
 * Created by Bázis on 2017. 04. 14..
 */
@Transactional
public interface PreferenceDao extends CrudRepository<Preference, String> {

}
