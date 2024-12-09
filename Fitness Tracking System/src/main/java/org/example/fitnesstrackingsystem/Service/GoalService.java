package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.Model.Goal;
import org.example.fitnesstrackingsystem.Repository.GoalRepository;
import org.example.fitnesstrackingsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final AchievementService achievementService;

    public List<Goal> getAllGoals(){
        return goalRepository.findAll();
    }

    public void addGoal(Goal goal){
        if (userRepository.findUserById(goal.getUserId())==null)
            throw new ApiException("User not found");

        goalRepository.save(goal);
    }

    public void updateGoal(Integer id,Goal goal){
        if (userRepository.findUserById(goal.getUserId())==null)
            throw new ApiException("User not found");

        Goal oldGoal=getGoalById(id);

        if (!oldGoal.getUserId().equals(goal.getUserId()))
            throw new ApiException("new user ID doesn't match original user ID");

        oldGoal.setTarget(goal.getTarget());
        oldGoal.setProgress(goal.getProgress());
        oldGoal.setTargetDate(goal.getTargetDate());
        oldGoal.setUpdatedAt(LocalDateTime.now());

        goalRepository.save(oldGoal);
    }

    public void deleteGoal(Integer id){
        Goal goal=getGoalById(id);

        goalRepository.delete(goal);
    }

    public Goal getGoalById(Integer id){
        Goal goal=goalRepository.findGoalById(id);

        if (goal==null)
            throw new ApiException("Goal not found");

        return goal;
    }

    public List<Goal> getGoalsByUserId(Integer id){
        if (userRepository.findUserById(id)==null)
            throw new ApiException("User not found");

        List<Goal> goals=goalRepository.findGoalsByUserId(id);

        if (goals.isEmpty())
            throw new ApiException("Goals not found");

        return goals;
    }

    public List<Goal> getGoalsByUserIdAndCompleted(Integer id){
        if (userRepository.findUserById(id)==null)
            throw new ApiException("User not found");

        List<Goal> goals=goalRepository.findGoalsByUserIdAndProgress(id,100);

        return goals;
    }

    public List<Goal> getUserGoalsByProgress(Integer id, Integer progress){
        getGoalById(id);

        List<Goal> goals=goalRepository.userGoalsByProgress(id,progress);

        if (goals.isEmpty())
            throw new ApiException("No goals with this progress were found");

        return goals;
    }

    public List<Goal> getUserGoalsByProgressRange(Integer id, Integer progressMin, Integer progressMax){
        getGoalById(id);

        List<Goal> goals = goalRepository.userGoalsByProgressRange(id,progressMin,progressMax);

        if (goals.isEmpty())
            throw new ApiException("No goals in this progress range were found");

        return goals;
    }


    public void updateProgress(Integer userId,Integer goalId,Integer progress){
        if (progress<0 || progress>100)
            throw new ApiException("Progress must be between 0 and 100");

        Goal goal=goalRepository.findGoalById(goalId);

        if (goal==null)
            throw new ApiException("goal not found");

        if (!goal.getUserId().equals(userId))
            throw new ApiException("Gaol doesn't match user ID");

        goal.setProgress(progress);
        goal.setUpdatedAt(LocalDateTime.now());
        goalRepository.save(goal);

        if (progress==100){
            achievementService.goalAchievement(userId,getUserGoalsByProgress(userId,100).size());
        }
    }
}
