package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    List<Comment> findCommentsByUserId(Integer id);

    @Query("select c from Comment c where c.postId=?1")
    List<Comment> commentsByPostId(Integer id);

    @Query("select c from Comment c where c.createdAt between ?1 and ?2")
    List<Comment> CommentsBetweenDates(LocalDate date1, LocalDate date2);

    List<Comment> findCommentsByContentContaining(String phrase);
}
