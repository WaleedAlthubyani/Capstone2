package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {

    Exercise findExerciseById(Integer id);

    List<Exercise> findExercisesByWorkoutPlanId(Integer id);
}
