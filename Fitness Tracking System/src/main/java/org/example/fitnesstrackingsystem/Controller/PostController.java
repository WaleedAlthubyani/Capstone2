package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.Post;
import org.example.fitnesstrackingsystem.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getAllPosts()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        postService.addPost(post);
        return ResponseEntity.status(201).body(new ApiResponse<>("Post created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse<>("Post updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("post deleted successfully"));
    }

    @GetMapping("/get-post-by-id/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostById(id)));
    }

    @GetMapping("/get-posts-by-user-id/{id}")
    public ResponseEntity<ApiResponse<List<Post>>> getPostsByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostsByUserId(id)));
    }

    @GetMapping("/get-posts-between-dates/{date1}/{date2}")
    public ResponseEntity<ApiResponse<List<Post>>> getPostsBetweenDates(@PathVariable LocalDate date1, @PathVariable LocalDate date2){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostsBetweenDates(date1,date2)));
    }

    @GetMapping("/get-posts-containing-phrase/{phrase}")
    public ResponseEntity<ApiResponse<List<Post>>> getPostsContainingPhrase(@PathVariable String phrase){
        return ResponseEntity.status(200).body(new ApiResponse<>(postService.getPostsContainingPhrase(phrase)));
    }

}
