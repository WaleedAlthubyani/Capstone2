package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.Comment;
import org.example.fitnesstrackingsystem.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Comment>>> getAllComments() {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getAllComments()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addComment(@RequestBody @Valid Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.status(201).body(new ApiResponse<>("Comment created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment) {

        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse<>("Comment updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("comment deleted successfully"));
    }
    
    @GetMapping("/get-Comment-by-id/{id}")
    public ResponseEntity<ApiResponse<Comment>> getCommentById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentById(id)));
    }

    @GetMapping("/get-Comments-by-user-id/{id}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsByUserId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentsByUserId(id)));
    }

    @GetMapping("/get-Comments-by-post-id/{id}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsByPostId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentsByPostId(id)));
    }

    @GetMapping("/get-comments-between-dates/{date1}/{date2}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsBetweenDates(@PathVariable LocalDate date1, @PathVariable LocalDate date2) {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentsBetweenDates(date1, date2)));
    }

    @GetMapping("/get-comments-containing-phrase/{phrase}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsContainingPhrase(@PathVariable String phrase) {
        return ResponseEntity.status(200).body(new ApiResponse<>(commentService.getCommentsContainingPhrase(phrase)));
    }
}
