package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.Post;
import org.example.fitnesstrackingsystem.Model.WorkoutPlan;
import org.example.fitnesstrackingsystem.Repository.PostRepository;
import org.example.fitnesstrackingsystem.Repository.UserRepository;
import org.example.fitnesstrackingsystem.Repository.UserWorkoutPlanRepository;
import org.example.fitnesstrackingsystem.Repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final WorkoutPlanRepository workoutPlanRepository;
    private final UserWorkoutPlanRepository userWorkoutPlanRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        if (userRepository.findUserById(post.getUserId())==null)
            throw new ApiException("User not found");

        postRepository.save(post);
    }

    public void updatePost(Integer id,Post post){
        Post oldPost=getPostById(id);

        oldPost.setContent(post.getContent());
        oldPost.setUpdatedAt(LocalDateTime.now());

        postRepository.save(oldPost);
    }

    public void deletePost(Integer id){
        Post post=getPostById(id);

        postRepository.delete(post);
    }

    public Post getPostById(Integer id){
        Post post=postRepository.findPostById(id);

        if (post==null)
            throw new ApiException("Post not found");

        return post;
    }

    public List<Post> getPostsByUserId(Integer id){
        List<Post> posts=postRepository.findPostsByUserId(id);

        if (userRepository.findUserById(id)==null)
            throw new ApiException("User not found");

        if (posts==null)
            throw new ApiException("Posts not found");

        return posts;
    }

    public List<Post> getPostsBetweenDates(LocalDate date1,LocalDate date2){
        List<Post> posts=postRepository.postsBetweenDates(date1,date2);

        if (posts==null)
            throw new ApiException("No posts found");

        return posts;
    }

    public List<Post> getPostsContainingPhrase(String phrase){
        List<Post> posts=postRepository.findPostsByContentContaining(phrase);

        if (posts==null)
            throw new ApiException("No posts found");

        return posts;
    }

    public void shareMyWorkoutPlan(Integer userId,Integer workoutPlanId){
        WorkoutPlan workoutPlan=workoutPlanRepository.findWorkoutPlanById(userWorkoutPlanRepository.findUserWorkoutPlanByUserIdAndWorkoutPlanId(userId,workoutPlanId).getWorkoutPlanId());

        if (workoutPlan==null)
            throw new ApiException("Workout plan not found");

        Post post=new Post();
        post.setUserId(userId);
        post.setCreatedAt(LocalDateTime.now());

        post.setContent(workoutPlan.toString());

        postRepository.save(post);
    }
}
