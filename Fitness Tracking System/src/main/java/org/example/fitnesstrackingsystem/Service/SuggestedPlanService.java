package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.InstructorStudent;
import org.example.fitnesstrackingsystem.Model.SuggestedPlan;
import org.example.fitnesstrackingsystem.Model.UserWorkoutPlan;
import org.example.fitnesstrackingsystem.Repository.ExerciseRepository;
import org.example.fitnesstrackingsystem.Repository.SuggestedPlanRepository;
import org.example.fitnesstrackingsystem.Repository.UserWorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestedPlanService {

    private final SuggestedPlanRepository suggestedPlanRepository;
    private final UserWorkoutPlanRepository userWorkoutPlanRepository;
    private final InstructorStudentService instructorStudentService;
    private final ExerciseRepository exerciseRepository;

    public void addSuggestedPlan(Integer instructorId, Integer workoutPlanId, Integer userId){
        SuggestedPlan suggestedPlan=new SuggestedPlan();
        suggestedPlan.setWorkoutPlanId(workoutPlanId);
        suggestedPlan.setInstructorId(instructorId);
        suggestedPlan.setUserId(userId);
        suggestedPlan.setSuggestedAt(LocalDateTime.now());
        suggestedPlan.setStatus("pending");
        suggestedPlanRepository.save(suggestedPlan);
    }

    public void decideOnWorkoutPlan(Integer userId,Integer suggestedPlanId, Boolean decision){
        SuggestedPlan suggestedPlan=suggestedPlanRepository.findSuggestedPlanById(suggestedPlanId);

        if (suggestedPlan==null)
            throw new ApiException("suggested plan not found");

        if (!suggestedPlan.getUserId().equals(userId))
            throw new ApiException("User ID doesn't belong to suggested plan");

        if (!decision){
            suggestedPlan.setStatus("rejected");
            suggestedPlan.setDecidedOn(LocalDateTime.now());
            suggestedPlanRepository.save(suggestedPlan);
            return;
        }

        UserWorkoutPlan userWorkoutPlan=new UserWorkoutPlan();
        userWorkoutPlan.setUserId(userId);
        userWorkoutPlan.setWorkoutPlanId(suggestedPlan.getWorkoutPlanId());
        userWorkoutPlan.setStatus("not started");
        userWorkoutPlan.setExercises(exerciseRepository.findExercisesByWorkoutPlanId(suggestedPlan.getWorkoutPlanId()).size());
        userWorkoutPlan.setCompleted(0);
        userWorkoutPlanRepository.save(userWorkoutPlan);

        InstructorStudent instructorStudent=new InstructorStudent();
        instructorStudent.setInstructorId(suggestedPlan.getInstructorId());
        instructorStudent.setUserId(userId);
        instructorStudent.setUserWorkoutPlanId(userWorkoutPlan.getWorkoutPlanId());
        instructorStudentService.addStudent(instructorStudent);

        suggestedPlan.setStatus("accepted");
        suggestedPlan.setDecidedOn(LocalDateTime.now());
        suggestedPlanRepository.save(suggestedPlan);
    }

    public List<SuggestedPlan> getSuggestedPlanByInstructorId(Integer instructorId){
        return suggestedPlanRepository.findSuggestedPlansByInstructorIdAndStatus(instructorId,"accepted");
    }

    public List<SuggestedPlan> getSuggestedPlanByUserId(Integer userId){
        return suggestedPlanRepository.findSuggestedPlansByUserId(userId);
    }
}
