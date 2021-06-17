package com.alkemy.warmup.repos;

import com.alkemy.warmup.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
