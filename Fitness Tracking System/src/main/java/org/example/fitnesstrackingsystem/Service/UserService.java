package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.*;
import org.example.fitnesstrackingsystem.Repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final InstructorService instructorService;
    private final InstructorRepository instructorRepository;
    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;
    private final PostService postService;
    private final SuggestedPlanService suggestedPlanService;
    private final GoalService goalService;
    private final AchievementService achievementService;
    private final WorkoutPlanRepository workoutPlanRepository;
    private final UserWorkoutPlanRepository userWorkoutPlanRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserWorkoutPlanService userWorkoutPlanService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User oldUser=getUserById(id);


        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user=getUserById(id);

        userRepository.delete(user);
    }

    public User getUserById(Integer id){

        User user=userRepository.findUserById(id);

        if (user==null)
            throw new ApiException("User not found");

        return user;
    }

    public void addUserWorkoutPlan(Integer userId,Integer workoutPlanId){
        getUserById(userId);
        WorkoutPlan workoutPlan=workoutPlanRepository.findWorkoutPlanById(workoutPlanId);

        if (workoutPlan==null)
            throw new ApiException("Workout plan not found");

        UserWorkoutPlan userWorkoutPlan=new UserWorkoutPlan();

        userWorkoutPlan.setWorkoutPlanId(workoutPlanId);
        userWorkoutPlan.setStatus("not started");
        userWorkoutPlan.setUserId(userId);
        userWorkoutPlan.setExercises(exerciseRepository.findExercisesByWorkoutPlanId(workoutPlanId).size());
        userWorkoutPlan.setCompleted(0);

        userWorkoutPlanService.addWorkoutPlan(userWorkoutPlan);

    }

    public void shareMyWorkoutPlan(Integer userId,Integer workoutPlanId){
        getUserById(userId);
        if (!userWorkoutPlanRepository.findUserWorkoutPlanByUserId(userId).getWorkoutPlanId().equals(workoutPlanId))
            throw new ApiException("workout plan not found");

        postService.shareMyWorkoutPlan(userId,workoutPlanId);
    }

    public void decideOnWorkoutPlan(Integer userId,Integer suggestedPlanId, Boolean decision){
        getUserById(userId);

        suggestedPlanService.decideOnWorkoutPlan(userId,suggestedPlanId,decision);
    }

    public void approveInstructor(Integer adminId,Integer instructorId){
        User admin=getUserById(adminId);

        if (!admin.getRole().equalsIgnoreCase("admin"))
            throw new ApiException("Not authorized to do this action");

        Instructor instructor=instructorService.getInstructorById(instructorId);

        instructor.setStatus("active");
        instructorRepository.save(instructor);

    }

    public void updateMyGoalProgress(Integer userId,Integer goalId,Integer progress){
        getUserById(userId);
        goalService.updateProgress(userId,goalId,progress);
    }

    public void createAchievement(Integer adminId,String name,String description){

        User admin=getUserById(adminId);

        if (admin.getRole().equalsIgnoreCase("trainee"))
            throw new ApiException("Not authorized to do this action");

        Achievement achievement = new Achievement();

        achievement.setName(name);
        achievement.setDescription(description);
        achievementService.addAchievement(achievement);
    }

    public void receiveAchievement(Integer userId,String achievementName){
        getUserById(userId);

        Achievement achievement=achievementRepository.findAchievementsByName(achievementName);

        if (achievement==null)
            throw new ApiException("achievement not found");

        UserAchievement userAchievement=new UserAchievement();

        userAchievement.setAchievementId(achievement.getId());
        userAchievement.setUserId(userId);
        userAchievement.setReceivedAt(LocalDate.now());

        userAchievementRepository.save(userAchievement);
    }

    public void advanceExerciseStatus(Integer userId,Integer workoutPlanId, Integer exerciseId){
        getUserById(userId);

        UserWorkoutPlan userWorkoutPlan=userWorkoutPlanRepository.findUserWorkoutPlanByUserIdAndWorkoutPlanId(userId,workoutPlanId);
        if (userWorkoutPlan==null)
            throw new ApiException("workout plan not found");

        Exercise exercise=exerciseRepository.findExerciseById(exerciseId);
        if (exercise==null)
            throw new ApiException("exercise not found");

        if (exercise.getStatus().equalsIgnoreCase("not started"))
            exercise.setStatus("in progress");
        else {
            exercise.setStatus("completed");
            userWorkoutPlan.setCompleted(userWorkoutPlan.getCompleted()+1);
            userWorkoutPlanRepository.save(userWorkoutPlan);
        }

        exerciseRepository.save(exercise);
    }

    public String getWorkoutPlanCompletionRate(Integer userId,Integer workoutPlanId){
        getUserById(userId);

        UserWorkoutPlan userWorkoutPlan=userWorkoutPlanRepository.findUserWorkoutPlanByUserIdAndWorkoutPlanId(userId,workoutPlanId);
        if (userWorkoutPlan==null)
            throw new ApiException("workout plan not found");

        double completed=userWorkoutPlan.getCompleted();
        double rate=completed/userWorkoutPlan.getExercises();
        return rate+"%";
    }

    public List<UserAchievement> getUserAchievement(Integer userId){
        List<UserAchievement> achievements=userAchievementRepository.findUserAchievementsByUserId(userId);

        if (achievements.isEmpty())
            throw new ApiException("user doesn't have achievement");

        return achievements;
    }

    public String nextAchievement(Integer userId){
        if (goalService.getGoalsByUserIdAndCompleted(userId).size()>=10)
            return "You completed all goal achievements";
        else if (goalService.getGoalsByUserIdAndCompleted(userId).size()>=5)
            return "Need to complete "+(10-goalService.getGoalsByUserIdAndCompleted(userId).size())+"goals to get next achievement";
        else if (!goalService.getGoalsByUserIdAndCompleted(userId).isEmpty())
            return "Need to complete "+(5-goalService.getGoalsByUserIdAndCompleted(userId).size())+" goals to get next achievement";
        else
            return "Need to complete 1 goal to get achievement";
    }
}
