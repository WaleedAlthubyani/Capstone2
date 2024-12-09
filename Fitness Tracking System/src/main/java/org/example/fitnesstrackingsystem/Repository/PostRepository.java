package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostById(Integer id);

    List<Post> findPostsByUserId(Integer id);

    @Query("select p from Post p where p.createdAt between ?1 and ?2")
    List<Post> postsBetweenDates(LocalDate date1,LocalDate date2);

    List<Post> findPostsByContentContaining(String phrase);
}
