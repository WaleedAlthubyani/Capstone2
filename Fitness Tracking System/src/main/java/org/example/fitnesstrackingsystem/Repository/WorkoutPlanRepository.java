package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan,Integer> {

    WorkoutPlan findWorkoutPlanById(Integer id);
}
