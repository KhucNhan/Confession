package khucnhan.project.confession.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long postId;

    // Nếu muốn liên kết Category thay vì chỉ lưu categoryId, đổi sang ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp createAt;

    @Column(name = "views", nullable = false)
    private int views;

    @Column(name = "edit_token", nullable = false, unique = true)
    private String editToken;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeDislike> likeDislikes = new ArrayList<>();

    // Constructors
    public Post() {}

    public Post(long postId, Category category, String title, String content, Timestamp createAt, int views, String editToken, List<Comment> comments, List<LikeDislike> likeDislikes) {
        this.postId = postId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.views = views;
        this.editToken = editToken;
        this.comments = comments;
        this.likeDislikes = likeDislikes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters and setters
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getEditToken() {
        return editToken;
    }

    public void setEditToken(String editToken) {
        this.editToken = editToken;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LikeDislike> getLikeDislikes() {
        return likeDislikes;
    }

    public void setLikeDislikes(List<LikeDislike> likeDislikes) {
        this.likeDislikes = likeDislikes;
    }


}
