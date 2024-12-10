package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.*;
import org.example.fitnesstrackingsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addUser(@RequestBody @Valid User user) {
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse<>("User created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable Integer id, @RequestBody @Valid User user) {
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse<>("User updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("user deleted successfully"));
    }

    @PostMapping("/create-a-workout-plan/{id}")
    public ResponseEntity<ApiResponse<String>> createAWorkoutPlan(@PathVariable Integer id, @RequestBody @Valid WorkoutPlan workoutPlan) {
        userService.createAWorkoutPlan(id, workoutPlan);
        return ResponseEntity.status(201).body(new ApiResponse<>("Plan created successfully"));
    }

    @GetMapping("/get-my-created-workout-plans/{id}")
    public ResponseEntity<ApiResponse<List<WorkoutPlan>>> getMyCreatedWorkoutPlans(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getMyCreatedPlans(id)));
    }

    @PutMapping("/update-my-workout-plan/{id}")
    public ResponseEntity<ApiResponse<String>>updateMyWorkoutPlan(@PathVariable Integer id,@RequestBody @Valid WorkoutPlan workoutPlan){
        userService.updateMyWorkoutPlan(id,workoutPlan);
        return ResponseEntity.status(200).body(new ApiResponse<>("Plan updated successfully"));
    }

    @DeleteMapping("/delete-my-created-workout-plan/{user-id}/{workout-plan-id}")
    public ResponseEntity<ApiResponse<String>>deleteMyCreatedWorkoutPlan(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId){
        userService.deleteMyCreatedWorkoutPlan(userId,workoutPlanId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Workout plan deleted successfully"));
    }

    @PostMapping("/create-an-exercise/{id}")
    public ResponseEntity<ApiResponse<String>> createAnExercise(@PathVariable Integer id, @RequestBody @Valid Exercise exercise) {
        userService.addExercise(id, exercise);
        return ResponseEntity.status(201).body(new ApiResponse<>("Exercise added successfully"));
    }

    @PutMapping("/update-my-exercise/{id}")
    public ResponseEntity<ApiResponse<String>> updateMyExercise(@PathVariable Integer id, @RequestBody @Valid Exercise exercise) {
        userService.updateMyExercise(id, exercise);
        return ResponseEntity.status(200).body(new ApiResponse<>("exercise updated successfully"));
    }

    @DeleteMapping("/delete-my-exercise/{user-id}/{exercise-id}")
    public ResponseEntity<ApiResponse<String>> deleteMyExercise(@PathVariable(name = "user-id") Integer userId, @PathVariable(name = "exercise-id") Integer exerciseId) {
        userService.deleteMyExercise(userId, exerciseId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Exercise deleted successfully"));
    }

    @PostMapping("/add-user-workout-plan/{user-id}/{workout-plan-id}")
    public ResponseEntity<ApiResponse<String>> addUserWorkoutPlan(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId){
        userService.addUserWorkoutPlan(userId,workoutPlanId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Workout plan added successfully"));
    }

    @PostMapping("/share-my-workout-plan/{user-id}/{workout-plan-id}")
    public ResponseEntity<ApiResponse<String>> shareMyWorkoutPlan(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId){
        userService.shareMyWorkoutPlan(userId,workoutPlanId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Shared workout plan successfully"));
    }

    @PutMapping("/decide-on-workout-suggested-plan/{user-id}/{suggested-plan-id}/{decision}")
    public ResponseEntity<ApiResponse<String>>decideOnWorkoutPlan(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "suggested-plan-id") Integer suggestedPlanId,@PathVariable Boolean decision){
        userService.decideOnWorkoutPlan(userId,suggestedPlanId,decision);
        return ResponseEntity.status(200).body(new ApiResponse<>("decided on suggested plan successfully"));
    }

    @PutMapping("/approve-instructor/{admin-id}/{instructor-id}")
    public ResponseEntity<ApiResponse<String>> approveInstructor(@PathVariable(name = "admin-id") Integer adminId,@PathVariable(name = "instructor-id") Integer instructorId){
        userService.approveInstructor(adminId,instructorId);
        return ResponseEntity.status(200).body(new ApiResponse<>("instructor approved successfully"));
    }

    @PutMapping("/update-my-goal-progress/{user-id}/{goal-id}/{progress}")
    public ResponseEntity<ApiResponse<String>>updateMyGoalProgress(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "goal-id") Integer goalId,@PathVariable Integer progress){
        userService.updateMyGoalProgress(userId,goalId,progress);
        return ResponseEntity.status(200).body(new ApiResponse<>("Goal updated successfully"));
    }

    @PutMapping("/advance-exercise-status/{user-id}/{workout-plan-id}/{exercise-id}")
    public ResponseEntity<ApiResponse<String>>advanceExerciseStatus(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId,@PathVariable(name = "exercise-id") Integer exerciseId){
        userService.advanceExerciseStatus(userId,workoutPlanId,exerciseId);
        return ResponseEntity.status(200).body(new ApiResponse<>("exercise advanced successfully"));
    }

    @GetMapping("/get-workout-plan-completion-rate/{user-id}/{workout-plan-id}")
    public ResponseEntity<ApiResponse<String>>getWorkoutPlanCompletionRate(@PathVariable(name = "user-id") Integer userId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getWorkoutPlanCompletionRate(userId,workoutPlanId)));
    }

    @GetMapping("/get-user-achievements/{user-id}")
    public ResponseEntity<ApiResponse<List<UserAchievement>>>getUserAchievement(@PathVariable(name = "user-id") Integer userId){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getUserAchievement(userId)));
    }

    @GetMapping("/next-goal-achievement/{user-id}")
    public ResponseEntity<ApiResponse<String>> nextGoalAchievement(@PathVariable(name = "user-id") Integer userId) {
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.nextGoalAchievement(userId)));
    }

    @GetMapping("/get-suggested-plans-for-user/{id}")
    public ResponseEntity<ApiResponse<List<SuggestedPlan>>> getUserSuggestedPlans(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getSuggestedPlanForUser(id)));
    }

    @GetMapping("/get-all-inactive-instructors/{id}")
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInactiveInstructors(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(userService.getAllInactiveInstructors(id)));
    }
}
