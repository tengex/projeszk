package hu.elteik.projecttools.libmgmt.data.entity;

import hu.elteik.projecttools.libmgmt.util.CopyStatus;

import javax.persistence.*;

/**
 * Created by Bázis on 2017. 04. 15..
 */

/**
 * Object representation of database entity (row) from the "copies" table.
 */
@Entity
@Table(name = "copies")
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_id")
    private Long copyId;

    @Enumerated(EnumType.STRING)
    private CopyStatus copyStatus;

    /**
     * A copy instance.
     */
    public Copy() {
        this.copyStatus = CopyStatus.AVAILABLE;
    }

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public CopyStatus getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(CopyStatus copyStatus) {
        this.copyStatus = copyStatus;
    }
}
