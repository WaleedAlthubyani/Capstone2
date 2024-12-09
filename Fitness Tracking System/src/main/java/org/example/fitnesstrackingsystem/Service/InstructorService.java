package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.Instructor;
import org.example.fitnesstrackingsystem.Model.InstructorStudent;
import org.example.fitnesstrackingsystem.Model.SuggestedPlan;
import org.example.fitnesstrackingsystem.Model.User;
import org.example.fitnesstrackingsystem.Repository.InstructorRepository;
import org.example.fitnesstrackingsystem.Repository.InstructorStudentRepository;
import org.example.fitnesstrackingsystem.Repository.UserRepository;
import org.example.fitnesstrackingsystem.Repository.UserWorkoutPlanRepository;
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

    public List<Instructor> getAllInstructors(){
        return instructorRepository.findAll();
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

    public void suggestWorkoutPlan(Integer instructorId, Integer workoutPlanId, Integer userId){
        getInstructorById(instructorId);
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
