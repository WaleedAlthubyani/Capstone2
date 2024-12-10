package org.example.fitnesstrackingsystem.Controller;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.WorkoutPlan;
import org.example.fitnesstrackingsystem.Service.WorkoutPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/workout-plan")
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<WorkoutPlan>>> getAllPublicWorkoutPlans() {
        return ResponseEntity.status(200).body(new ApiResponse<>(workoutPlanService.getAllPublicWorkoutPlans()));
    }
}
