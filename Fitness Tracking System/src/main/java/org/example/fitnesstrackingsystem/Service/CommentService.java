package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.Comment;
import org.example.fitnesstrackingsystem.Repository.CommentRepository;
import org.example.fitnesstrackingsystem.Repository.PostRepository;
import org.example.fitnesstrackingsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        if (userRepository.findUserById(comment.getUserId())==null)
            throw new ApiException("User not found");

        if (postRepository.findPostById(comment.getPostId())==null)
            throw new ApiException("Post not found");

        commentRepository.save(comment);
    }

    public void updateComment(Integer id,Comment comment){
        if (userRepository.findUserById(comment.getUserId())==null)
            throw new ApiException("User not found");

        if (postRepository.findPostById(comment.getPostId())==null)
            throw new ApiException("Post not found");

        Comment oldComment=getCommentById(id);


        if (!oldComment.getUserId().equals(comment.getUserId()))
            throw new ApiException("New user Id doesn't match original user Id");

        oldComment.setContent(comment.getContent());
        oldComment.setPostId(comment.getPostId());
        oldComment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id){
        Comment comment=getCommentById(id);

        commentRepository.delete(comment);
    }

    public Comment getCommentById(Integer id){
        Comment comment=commentRepository.findCommentById(id);

        if (comment==null)
            throw new ApiException("Comment not found");

        return comment;
    }

    public List<Comment> getCommentsByUserId(Integer id){
        List<Comment> comments=commentRepository.findCommentsByUserId(id);

        if (userRepository.findUserById(id)==null)
            throw new ApiException("User not found");

        if (comments.isEmpty())
            throw new ApiException("Comments not found");

        return comments;
    }

    public List<Comment> getCommentsByPostId(Integer id){
        List<Comment> comments=commentRepository.commentsByPostId(id);

        if (postRepository.findPostById(id)==null)
            throw new ApiException("Post not found");

        if (comments.isEmpty())
            throw new ApiException("Comments not found");

        return comments;
    }

    public List<Comment> getCommentsBetweenDates(LocalDate date1,LocalDate date2){
        List<Comment> comments=commentRepository.CommentsBetweenDates(date1,date2);

        if (comments.isEmpty())
            throw new ApiException("Comments not found");

        return comments;
    }

    public List<Comment> getCommentsContainingPhrase(String phrase){
        List<Comment> comments=commentRepository.findCommentsByContentContaining(phrase);

        if (comments.isEmpty())
            throw new ApiException("Comments not found");

        return comments;
    }
}
