package hu.elteik.projecttools.libmgmt.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bázis on 2017. 04. 15..
 */
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @NotNull
    private String author;

    @NotNull
    private String title;

    private String subtitle;

    @NotNull
    private String publisher;

    @NotNull
    private Integer year;

    @NotNull
    private String isbn;

    private String details;

    @ManyToMany
    @JoinColumn(name = "copy_id")
    private Set<Copy> copies = new HashSet<>();

    public Book() {
    }

    public Book(String isbn, String details, Integer year, String publisher, String subtitle, String title, String author, Set<Copy> copies) {
        this.isbn = isbn;
        this.details = details;
        this.year = year;
        this.publisher = publisher;
        this.subtitle = subtitle;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }
}
