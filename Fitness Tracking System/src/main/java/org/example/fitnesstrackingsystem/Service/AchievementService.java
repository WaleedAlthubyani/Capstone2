package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.Model.Achievement;
import org.example.fitnesstrackingsystem.Model.UserAchievement;
import org.example.fitnesstrackingsystem.Repository.AchievementRepository;
import org.example.fitnesstrackingsystem.Repository.UserAchievementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;


    public void addAchievement(Achievement achievement){
        achievementRepository.save(achievement);
    }

    public void goalAchievement(Integer userId,Integer totalCompletedGoals){

        UserAchievement userAchievement=new UserAchievement();

        switch (totalCompletedGoals){
            case 1:
                userAchievement.setAchievementId(achievementRepository.findAchievementsByName("Completed First Goal").getId());
                userAchievement.setUserId(userId);
                userAchievement.setReceivedAt(LocalDate.now());
                userAchievementRepository.save(userAchievement);
                break;
            case 5:
                userAchievement.setAchievementId(achievementRepository.findAchievementsByName("Completed Five Goals").getId());
                userAchievement.setUserId(userId);
                userAchievement.setReceivedAt(LocalDate.now());
                userAchievementRepository.save(userAchievement);
                break;
            case 10:
                userAchievement.setAchievementId(achievementRepository.findAchievementsByName("Completed Ten Goals").getId());
                userAchievement.setUserId(userId);
                userAchievement.setReceivedAt(LocalDate.now());
                userAchievementRepository.save(userAchievement);
                break;
        }
    }
}
