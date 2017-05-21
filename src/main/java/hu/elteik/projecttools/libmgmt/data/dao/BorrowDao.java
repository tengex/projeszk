package hu.elteik.projecttools.libmgmt.data.dao;

import hu.elteik.projecttools.libmgmt.data.entity.Borrow;
import hu.elteik.projecttools.libmgmt.data.entity.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bázis on 2017. 04. 15..
 */
@Transactional
public interface BorrowDao extends CrudRepository<Borrow, Long> {
    Borrow findByCopy(Copy copy);
}
