package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.Exercise;
import org.example.fitnesstrackingsystem.Repository.ExerciseRepository;
import org.example.fitnesstrackingsystem.Repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutPlanRepository workoutPlanRepository;

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

    public void addExercise(Exercise exercise){
        if (workoutPlanRepository.findWorkoutPlanById(exercise.getWorkoutPlanId())==null)
            throw new ApiException("Workout plan not found");

        exerciseRepository.save(exercise);
    }

    public void updateExercise(Integer id,Exercise exercise){
        if (workoutPlanRepository.findWorkoutPlanById(exercise.getWorkoutPlanId())==null)
            throw new ApiException("Workout plan not found");

        Exercise oldExercise=getExerciseById(id);


        oldExercise.setName(exercise.getName());
        oldExercise.setDescription(exercise.getDescription());
        oldExercise.setSets(exercise.getSets());
        oldExercise.setReps(exercise.getReps());
        oldExercise.setDuration(exercise.getDuration());
        oldExercise.setWorkoutPlanId(exercise.getWorkoutPlanId());

        exerciseRepository.save(oldExercise);
    }

    public void deleteExercise(Integer id){
        Exercise exercise=getExerciseById(id);

        exerciseRepository.delete(exercise);
    }

    public Exercise getExerciseById(Integer id){
        Exercise exercise= exerciseRepository.findExerciseById(id);

        if (exercise==null)
            throw new ApiException("Exercise not found");

        return exercise;
    }

    public List<Exercise> getExercisesByWorkoutPlanId(Integer id){
        if (workoutPlanRepository.findWorkoutPlanById(id)==null)
            throw new ApiException("Workout plan not found");

        List<Exercise> exercises = exerciseRepository.findExercisesByWorkoutPlanId(id);

        if (exercises.isEmpty())
            throw new ApiException("Exercises not found");

        return exercises;
    }
}
