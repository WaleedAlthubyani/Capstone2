package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.WorkoutPlan;
import org.example.fitnesstrackingsystem.Repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public List<WorkoutPlan> getAllWorkoutPlans(){
        return workoutPlanRepository.findAll();
    }

    public void addWorkoutPlan(WorkoutPlan workoutPlan){
        workoutPlanRepository.save(workoutPlan);
    }

    public void updateWorkoutPlan(Integer id, WorkoutPlan workoutPlan){
        WorkoutPlan oldWorkoutPlan=getWorkoutPlanById(id);


        oldWorkoutPlan.setName(workoutPlan.getName());
        oldWorkoutPlan.setDescription(workoutPlan.getDescription());
        oldWorkoutPlan.setStartDate(workoutPlan.getStartDate());
        oldWorkoutPlan.setEndDate(workoutPlan.getEndDate());
        oldWorkoutPlan.setUpdatedAt(LocalDateTime.now());

        workoutPlanRepository.save(oldWorkoutPlan);
    }

    public void deleteWorkoutPlan(Integer id){
        WorkoutPlan workoutPlan = getWorkoutPlanById(id);

        workoutPlanRepository.delete(workoutPlan);
    }

    public WorkoutPlan getWorkoutPlanById(Integer id){
        WorkoutPlan workoutPlan= workoutPlanRepository.findWorkoutPlanById(id);

        if (workoutPlan==null)
            throw new ApiException("Workout plan not found");

        return workoutPlan;
    }
}
