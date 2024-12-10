package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan,Integer> {

    WorkoutPlan findWorkoutPlanById(Integer id);

    List<WorkoutPlan> findAllByIsPublic(Boolean isPublic);

    List<WorkoutPlan> findWorkoutPlansByCreatedBy(String creatorName);

    WorkoutPlan findWorkoutPlanByIdAndCreatedBy(Integer id, String creatorName);
}
