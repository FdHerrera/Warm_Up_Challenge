package com.alkemy.warmup.blog.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE post SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Post implements Comparable<Post>{

    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    private String content;
    @Column(nullable = false)
    private String urlImage;
    private String category;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(updatable = false)
    private Long userId;
    @Column(nullable = false)
    private boolean deleted = false;

    public Post(String title, String content, String urlImage, String category, LocalDateTime createdAt, Long userId) {
        this.title = title;
        this.content = content;
        this.urlImage = urlImage;
        this.category = category;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int compareTo(Post p){
        if(this.getCreatedAt() == p.getCreatedAt())
            return 0;
        else if (this.getCreatedAt().isAfter(p.getCreatedAt()))
            return 1;
        else
            return -1;
    }
}
