package hu.elteik.projecttools.libmgmt.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Object representation of database entity (row) from the "library" table.
 */
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id")
    private Long libraryId;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Date dateOfEstablishment;

    private String description;

    public Library() {
    }

    /**
     * A library instance
     *
     * @param name                name
     * @param address             address
     * @param dateOfEstablishment date of establishment
     * @param description         additional information
     */
    public Library(String name, String address, Date dateOfEstablishment, String description) {
        this.name = name;
        this.address = address;
        this.dateOfEstablishment = dateOfEstablishment;
        this.description = description;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(Date dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
