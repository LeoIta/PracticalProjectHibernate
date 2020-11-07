package model;

import javax.persistence.*;

@Entity
@Table(schema = "sda_system", name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Long reviewId;

    @Column(name = "score")
    private int score;

    @Column(name = "review")
    private String review;

    // Mapping reviews to book table
    @OneToOne(mappedBy = "review")
    private Books book;

    public Reviews() {
    }

    public Reviews(int score, String review) {
        this.score = score;
        this.review = review;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
