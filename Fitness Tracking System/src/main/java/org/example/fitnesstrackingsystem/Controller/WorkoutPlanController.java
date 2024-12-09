package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.WorkoutPlan;
import org.example.fitnesstrackingsystem.Service.WorkoutPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/workout-plan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<WorkoutPlan>>> getAllWorkoutPlans() {
        return ResponseEntity.status(200).body(new ApiResponse<>(workoutPlanService.getAllWorkoutPlans()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addWorkoutPlan(@RequestBody @Valid WorkoutPlan workoutPlan, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        workoutPlanService.addWorkoutPlan(workoutPlan);
        return ResponseEntity.status(201).body(new ApiResponse<>("WorkoutPlan created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateWorkoutPlan(@PathVariable Integer id, @RequestBody @Valid WorkoutPlan workoutPlan, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        workoutPlanService.updateWorkoutPlan(id, workoutPlan);
        return ResponseEntity.status(200).body(new ApiResponse<>("WorkoutPlan updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteWorkoutPlan(@PathVariable Integer id) {
        workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("workoutPlan deleted successfully"));
    }
}
