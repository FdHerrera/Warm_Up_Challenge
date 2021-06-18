package com.alkemy.warmup.blog.service;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.service.AppUserService;
import com.alkemy.warmup.blog.model.Post;
import com.alkemy.warmup.blog.model.PostRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostRequestService {

    private final PostService postService;
    private final AppUserService userService;


    public Post createNewPost(PostRequest postRequest) {
        return postService.addPost(new Post(
                postRequest.getTitle()
                , postRequest.getContent()
                , postRequest.getUrlImage()
                , postRequest.getCategory()
                , LocalDateTime.now()
                , currentUser().getId()
        ));
    }

    private AppUser currentUser(){
        return userService.findByEmail(getCurrentUsername());
    }

    private String getCurrentUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
            return ((UserDetails) principal ).getUsername();
        if(principal instanceof Principal)
            return ((Principal) principal).getName();
        return String.valueOf(principal);
    }
}
