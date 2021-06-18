package com.alkemy.warmup.blog.service;

import com.alkemy.warmup.blog.model.Post;
import com.alkemy.warmup.repos.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo repo;

    public List<Post> getAllPosts() {
        return repo.findAll().stream().sorted().collect(Collectors.toList());
    }

    public List<Post> getPostsByTitle(String title) {
        return repo.findAll().stream().filter(post -> post.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    public List<Post> getPostsByCategory(String category) {
        return repo.findAll().stream().filter(post -> post.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public Post getPostById(Long id) {
        return repo.findById(id).orElseThrow(()-> new IllegalStateException("No se encontro un post con el id especificado"));
    }

    public Post addPost(Post post) {
        return repo.save(post);
    }

    public Post updatePost(Post post) throws Exception {
        try {
            return repo.save(post);
        } catch (Exception e){
            throw new Exception("No se pudo guardar el post, es posible que el id no exista");
        }
    }

    public void deletePostById(Long id) {
        repo.deleteById(id);
    }
}
