package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.Instructor;
import org.example.fitnesstrackingsystem.Model.InstructorStudent;
import org.example.fitnesstrackingsystem.Model.User;
import org.example.fitnesstrackingsystem.Service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors() {
        return ResponseEntity.status(200).body(new ApiResponse<>(instructorService.getAllInstructors()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addInstructor(@RequestBody @Valid Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        instructorService.addInstructor(instructor);
        return ResponseEntity.status(201).body(new ApiResponse<>("Instructor created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateInstructor(@PathVariable Integer id, @RequestBody @Valid Instructor instructor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        instructorService.updateInstructor(id, instructor);
        return ResponseEntity.status(200).body(new ApiResponse<>("Instructor updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("instructor deleted successfully"));
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
