package model;

import javax.persistence.*;

@Entity
@Table(schema = "sda_system", name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String bookDescription;
    @Column(name = "genre")
    private String genre;
    // foreign key of authorId in book table
    @OneToOne
    @JoinColumn(name = "authorsId")
    private Author author;

    // foreign key of ReviewID in book table
    @OneToOne
    @JoinColumn(name = " reviewId")
    private Reviews review;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
