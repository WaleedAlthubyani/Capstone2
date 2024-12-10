package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.*;
import org.example.fitnesstrackingsystem.Service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors() {
        return ResponseEntity.status(200).body(new ApiResponse<>(instructorService.getAllActiveInstructors()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addInstructor(@RequestBody @Valid Instructor instructor) {
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(201).body(new ApiResponse<>("Instructor created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateInstructor(@PathVariable Integer id, @RequestBody @Valid Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
        return ResponseEntity.status(200).body(new ApiResponse<>("Instructor updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("instructor deleted successfully"));
    }

    @PostMapping("/create-a-workout-plan/{id}")
    public ResponseEntity<ApiResponse<String>> createAWorkoutPlan(@PathVariable Integer id, @RequestBody @Valid WorkoutPlan workoutPlan) {
        instructorService.createWorkoutPlan(id, workoutPlan);
        return ResponseEntity.status(201).body(new ApiResponse<>("Plan added successfully"));
    }

    @PutMapping("/update-my-workout-plan/{id}")
    public ResponseEntity<ApiResponse<String>>updateMyWorkoutPlan(@PathVariable Integer id,@RequestBody @Valid WorkoutPlan workoutPlan){
        instructorService.updateMyWorkoutPlan(id,workoutPlan);
        return ResponseEntity.status(200).body(new ApiResponse<>("Plan updated successfully"));
    }

    @DeleteMapping("/delete-my-created-workout-plan/{instructor-id}/{workout-plan-id}")
    public ResponseEntity<ApiResponse<String>>deleteMyCreatedWorkoutPlan(@PathVariable(name = "instructor-id") Integer instructorId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId){
        instructorService.deleteMyCreatedWorkoutPlan(instructorId,workoutPlanId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Workout plan deleted successfully"));
    }

    @GetMapping("/get-my-created-workout-plans/{id}")
    public ResponseEntity<ApiResponse<List<WorkoutPlan>>> getMyCreatedWorkoutPlans(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse<>(instructorService.getMyCreatedWorkoutPlans(id)));
    }

    @PostMapping("/create-an-exercise/{id}")
    public ResponseEntity<ApiResponse<String>> createAnExercise(@PathVariable Integer id, @RequestBody @Valid Exercise exercise) {
        instructorService.addExercise(id, exercise);
        return ResponseEntity.status(201).body(new ApiResponse<>("Exercise added successfully"));
    }

    @PutMapping("/update-my-exercise/{id}")
    public ResponseEntity<ApiResponse<String>> updateMyExercise(@PathVariable Integer id, @RequestBody @Valid Exercise exercise) {
        instructorService.updateMyExercise(id, exercise);
        return ResponseEntity.status(200).body(new ApiResponse<>("exercise updated successfully"));
    }

    @DeleteMapping("/delete-my-exercise/{instructor-id}/{exercise-id}")
    public ResponseEntity<ApiResponse<String>> deleteMyExercise(@PathVariable(name = "instructor-id") Integer instructorId, @PathVariable(name = "exercise-id") Integer exerciseId) {
        instructorService.deleteMyExercise(instructorId, exerciseId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Exercise deleted successfully"));
    }

    @PostMapping("/suggest-workout-plan/{instructor-id}/{workout-plan-id}/{user-id}")
    public ResponseEntity<ApiResponse<String>> suggestWorkoutPlan(@PathVariable(name = "instructor-id") Integer instructorId,@PathVariable(name = "workout-plan-id") Integer workoutPlanId,@PathVariable(name = "user-id") Integer userId){
        instructorService.suggestWorkoutPlan(instructorId,workoutPlanId,userId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Workout plan suggested successfully"));
    }

    @GetMapping("/get-instructor-students/{id}")
    public ResponseEntity<ApiResponse<List<InstructorStudent>>> getMyStudents(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(instructorService.getMyStudents(id)));
    }

    @DeleteMapping("/expel-a-student/{instructor-id}/{user-id}")
    public ResponseEntity<ApiResponse<String>> expelAStudent(@PathVariable(name = "instructor-id") Integer instructorId,@PathVariable(name = "user-id") Integer userId){
        instructorService.deleteAStudent(instructorId,userId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Student expelled successfully"));
    }

    @GetMapping("/get-students-completed-plan/{instructor-id}")
    public ResponseEntity<ApiResponse<List<User>>> getStudentsCompletedWorkoutPlan(@PathVariable(name = "instructor-id") Integer instructorId){
        return ResponseEntity.status(200).body(new ApiResponse<>(instructorService.getStudentsCompletedWorkoutPlan(instructorId)));
    }
}
