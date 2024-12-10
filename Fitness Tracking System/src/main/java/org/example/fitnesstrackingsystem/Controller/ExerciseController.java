package org.example.fitnesstrackingsystem.Controller;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.Exercise;
import org.example.fitnesstrackingsystem.Service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Exercise>>> getAllExercises() {
        return ResponseEntity.status(200).body(new ApiResponse<>(exerciseService.getAllExercises()));
    }

    @GetMapping("/get-exercise-by-id/{id}")
    public ResponseEntity<ApiResponse<Exercise>> getExerciseById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(exerciseService.getExerciseById(id)));
    }

    @GetMapping("/get-exercises-by-workout-plan-id/{id}")
    public ResponseEntity<ApiResponse<List<Exercise>>> getExercisesByWorkoutPlanId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(exerciseService.getExercisesByWorkoutPlanId(id)));
    }
}
