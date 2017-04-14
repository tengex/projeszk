package hu.elteik.projecttools.libmgmt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by Bázis on 2017. 04. 14..
 */
@Service
@Transactional
public class MainService {
    private static final Logger logger = Logger.getLogger(MainService.class.getName());

    @PostConstruct
    public void init(){
        //initialization goes here; todo: some exception handling if these fail
        logger.info("Application started");
    }
}
