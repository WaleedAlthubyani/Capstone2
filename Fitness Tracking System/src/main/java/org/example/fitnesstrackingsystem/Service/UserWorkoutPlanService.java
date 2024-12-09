package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.Model.UserWorkoutPlan;
import org.example.fitnesstrackingsystem.Repository.UserWorkoutPlanRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWorkoutPlanService {

    private final UserWorkoutPlanRepository userWorkoutPlanRepository;

    public void addWorkoutPlan(UserWorkoutPlan userWorkoutPlan){
        userWorkoutPlanRepository.save(userWorkoutPlan);
    }
}
