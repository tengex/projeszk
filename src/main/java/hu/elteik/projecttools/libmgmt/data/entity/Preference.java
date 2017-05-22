package hu.elteik.projecttools.libmgmt.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Bázis on 2017. 04. 14..
 */

/**
 * Object representation of database entity (row) from the "preferences" table. We can store application-specific settings here.
 */
@Entity
@Table(name = "preferences")
public class Preference {
    @Id
    private String key;
    private String value;

    public Preference() {
    }

    public Preference(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
