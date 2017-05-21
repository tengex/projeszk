package hu.elteik.projecttools.libmgmt.data.entity;

import hu.elteik.projecttools.libmgmt.util.BorrowStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bázis on 2017. 04. 15..
 */
@Entity
@Table(name = "borrows")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Long borrowId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @Transient
    private Book book;

    private Date borrowDate;

    private Date expiryDate;

    private Date closeDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    public Borrow() {
    }

    public Borrow(User user, Copy copy, Date expiryDate) {
        this.user = user;
        this.copy = copy;
        this.borrowDate = new Date();
        this.expiryDate = expiryDate;
        this.status = BorrowStatus.ACTIVE;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
