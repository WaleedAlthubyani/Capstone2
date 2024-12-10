package org.example.fitnesstrackingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.example.fitnesstrackingsystem.Model.Goal;
import org.example.fitnesstrackingsystem.Service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness-tracking-system/goal")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Goal>>> getAllGoals() {
        return ResponseEntity.status(200).body(new ApiResponse<>(goalService.getAllGoals()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addGoal(@RequestBody @Valid Goal goal) {
        goalService.addGoal(goal);
        return ResponseEntity.status(201).body(new ApiResponse<>("Goal created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateGoal(@PathVariable Integer id, @RequestBody @Valid Goal goal) {

        goalService.updateGoal(id, goal);
        return ResponseEntity.status(200).body(new ApiResponse<>("Goal updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteGoal(@PathVariable Integer id) {
        goalService.deleteGoal(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("goal deleted successfully"));
    }

    @GetMapping("/get-goal-by-id/{id}")
    public ResponseEntity<ApiResponse<Goal>> getGoalById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new ApiResponse<>(goalService.getGoalById(id)));
    }

    @GetMapping("/get-goals-by-user-id/{id}")
    public ResponseEntity<ApiResponse<List<Goal>>> getGoalsByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(goalService.getGoalsByUserId(id)));
    }


    @GetMapping("/get-user-goals-by-progress/{id}/{progress}")
    public ResponseEntity<ApiResponse<List<Goal>>> getUserGoalsByProgress(@PathVariable Integer id,@PathVariable Integer progress){
        return ResponseEntity.status(200).body(new ApiResponse<>(goalService.getUserGoalsByProgress(id,progress)));
    }


    @GetMapping("/get-user-goals-by-progress-range/{id}/{min-progress}/{max-progress}")
    public ResponseEntity<ApiResponse<List<Goal>>> getUserGoalsByProgressRange(@PathVariable Integer id,@PathVariable(name = "min-progress") Integer minProgress,@PathVariable(name = "max-progress") Integer maxProgress){
        return ResponseEntity.status(200).body(new ApiResponse<>(goalService.getUserGoalsByProgressRange(id,minProgress,maxProgress)));
    }
}
