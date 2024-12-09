package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.Exercise;
import org.example.fitnesstrackingsystem.Service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Exercise>>> getAllExercises() {
        return ResponseEntity.status(200).body(new ApiResponse<>(exerciseService.getAllExercises()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addExercise(@RequestBody @Valid Exercise exercise, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        exerciseService.addExercise(exercise);
        return ResponseEntity.status(201).body(new ApiResponse<>("Exercise created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateExercise(@PathVariable Integer id, @RequestBody @Valid Exercise exercise, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        exerciseService.updateExercise(id, exercise);
        return ResponseEntity.status(200).body(new ApiResponse<>("Exercise updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteExercise(@PathVariable Integer id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("exercise deleted successfully"));
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
