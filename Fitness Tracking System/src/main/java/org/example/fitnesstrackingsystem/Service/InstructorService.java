package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.*;
import org.example.fitnesstrackingsystem.Repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final SuggestedPlanService suggestedPlanService;
    private final InstructorStudentRepository instructorStudentRepository;
    private final WorkoutPlanService workoutPlanService;
    private final UserRepository userRepository;
    private final UserWorkoutPlanRepository userWorkoutPlanRepository;
    private final WorkoutPlanRepository workoutPlanRepository;
    private final ExerciseService exerciseService;

    public List<Instructor> getAllActiveInstructors() {

        return instructorRepository.findAllByStatus("active");
    }

    public List<Instructor> getAllInActiveInstructor() {
        return instructorRepository.findAllByStatus("inactive");
    }

    public void addInstructor(Instructor instructor){
        instructorRepository.save(instructor);
    }

    public void updateInstructor(Integer id, Instructor instructor){
        Instructor oldInstructor=getInstructorById(id);

        if (oldInstructor==null)
            throw new ApiException("Instructor not found");

        oldInstructor.setName(instructor.getName());
        oldInstructor.setEmail(instructor.getEmail());
        oldInstructor.setPassword(instructor.getPassword());
        if (instructor.getStatus().equalsIgnoreCase("active")&&!instructor.getCertificateURL().isEmpty())
            oldInstructor.setStatus(instructor.getStatus());
        oldInstructor.setCertificateURL(instructor.getCertificateURL());

        instructorRepository.save(oldInstructor);
    }

    public void deleteInstructor(Integer id){
        Instructor instructor=getInstructorById(id);

        if (instructor==null)
            throw new ApiException("Instructor not found");

        instructorRepository.delete(instructor);
    }

    public Instructor getInstructorById(Integer id){
        Instructor instructor=instructorRepository.findInstructorById(id);

        if (instructor==null)
            throw new ApiException("Instructor not found");

        return instructor;
    }

    public void createWorkoutPlan(Integer id, WorkoutPlan workoutPlan) {
        Instructor instructor = getInstructorById(id);
        if (instructor.getStatus().equalsIgnoreCase("inactive"))
            throw new ApiException("Wait for an admin to activate your account");

        workoutPlan.setCreatedBy("Instructor: " + getInstructorById(id).getName());
        workoutPlan.setPlanType("Certified plan");

        workoutPlanService.addWorkoutPlan(workoutPlan);
    }

    public List<WorkoutPlan> getMyCreatedWorkoutPlans(Integer id) {
        Instructor instructor = getInstructorById(id);

        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findWorkoutPlansByCreatedBy(instructor.getName());

        if (workoutPlans.isEmpty())
            throw new ApiException("Workout plans not found");

        return workoutPlans;
    }

    public void updateMyWorkoutPlan(Integer id, WorkoutPlan workoutPlan) {
        Instructor instructor = getInstructorById(id);

        if (!workoutPlan.getCreatedBy().equalsIgnoreCase(instructor.getName()))
            throw new ApiException("Workout plan wasn't created by you");

        workoutPlanService.updateWorkoutPlan(workoutPlan.getId(), workoutPlan);
    }

    public void deleteMyCreatedWorkoutPlan(Integer instructorId, Integer workoutPlanId) {
        Instructor instructor = getInstructorById(instructorId);

        if (!workoutPlanRepository.findWorkoutPlanById(workoutPlanId).getCreatedBy().equalsIgnoreCase(instructor.getName()))
            throw new ApiException("Workout plan wasn't created by you");

        workoutPlanService.deleteWorkoutPlan(workoutPlanId);
    }

    public void addExercise(Integer id, Exercise exercise) {
        Instructor instructor = getInstructorById(id);

        WorkoutPlan workoutPlan = workoutPlanRepository.findWorkoutPlanByIdAndCreatedBy(exercise.getWorkoutPlanId(), instructor.getName());

        if (workoutPlan == null)
            throw new ApiException("Workout plan wasn't created by you");

        exerciseService.addExercise(exercise);
    }

    public void updateMyExercise(Integer id, Exercise exercise) {
        Instructor instructor = getInstructorById(id);

        WorkoutPlan workoutPlan = workoutPlanRepository.findWorkoutPlanByIdAndCreatedBy(exercise.getWorkoutPlanId(), instructor.getName());

        if (workoutPlan == null)
            throw new ApiException("Exercise wasn't created by you");

        exerciseService.updateExercise(exercise.getId(), exercise);
    }

    public void deleteMyExercise(Integer userId, Integer exerciseId) {
        Instructor instructor = getInstructorById(userId);

        WorkoutPlan w = workoutPlanRepository.findWorkoutPlanByIdAndCreatedBy(exerciseService.getExerciseById(exerciseId).getWorkoutPlanId(), instructor.getName());

        if (w == null)
            throw new ApiException("exercise wasn't created by you");

        exerciseService.deleteExercise(exerciseId);
    }

    public void suggestWorkoutPlan(Integer instructorId, Integer workoutPlanId, Integer userId){
        Instructor instructor = getInstructorById(instructorId);
        if (instructor.getStatus().equalsIgnoreCase("inactive"))
            throw new ApiException("Please wait for an admin to approve your account");

        workoutPlanService.getWorkoutPlanById(workoutPlanId);
        User user=userRepository.findUserById(userId);

        if (user==null)
            throw new ApiException("User not found");

        suggestedPlanService.addSuggestedPlan(instructorId,workoutPlanId,userId);
    }

    public List<InstructorStudent> getMyStudents(Integer id){
        List<InstructorStudent> students=instructorStudentRepository.findInstructorStudentsByInstructorId(id);

        getInstructorById(id);

        if (students.isEmpty())
            throw new ApiException("Students not found");

        return students;
    }

    public void deleteAStudent(Integer instructorId,Integer userId){
        getInstructorById(instructorId);

        User user=userRepository.findUserById(userId);

        if (user==null)
            throw new ApiException("User not found");

        InstructorStudent student=instructorStudentRepository.findInstructorStudentByInstructorIdAndUserId(instructorId,userId);

        if (student==null)
            throw new ApiException("Instructor doesn't teach this user");

        instructorStudentRepository.delete(student);
    }

    public List<User> getStudentsCompletedWorkoutPlan(Integer instructorId){
        getInstructorById(instructorId);
        List<User> users=new ArrayList<>();
        List<SuggestedPlan> suggestedPlans=suggestedPlanService.getSuggestedPlanByInstructorId(instructorId);

        if (suggestedPlans.isEmpty())
            throw new ApiException("No suggested plan accepted");

        for (SuggestedPlan s:suggestedPlans){
            if (userWorkoutPlanRepository.findUserWorkoutPlanByUserIdAndWorkoutPlanId(s.getUserId(),s.getWorkoutPlanId())!=null){
                if (userWorkoutPlanRepository.findUserWorkoutPlanByUserIdAndWorkoutPlanId(s.getUserId(),s.getWorkoutPlanId()).getStatus().equalsIgnoreCase("complete")){
                    users.add(userRepository.findUserById(s.getUserId()));
                }
            }
        }

        if (users.isEmpty())
            throw new ApiException("No student completed workout plan");

        return users;
    }
}
