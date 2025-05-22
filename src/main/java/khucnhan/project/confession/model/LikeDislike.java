package khucnhan.project.confession.model;

import javax.persistence.*;

@Entity
@Table(name = "likedislikes")
public class LikeDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "is_like", nullable = false)
    private boolean isLike;

    // Constructors
    public LikeDislike() {}

    public LikeDislike(long id, Post post, boolean isLike) {
        this.id = id;
        this.post = post;
        this.isLike = isLike;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Override
    public String toString() {
        return "LikeDislike{" +
                "id=" + id +
                ", post=" + post +
                ", isLike=" + isLike +
                '}';
    }
}
