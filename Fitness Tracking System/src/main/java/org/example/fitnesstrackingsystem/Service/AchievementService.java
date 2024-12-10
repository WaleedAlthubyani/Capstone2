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

    public void goalAchievement(Integer userId,Integer totalCompletedGoals){
        if (achievementRepository.findAchievementsByName("Completed First Goal") == null) {
            Achievement achievement = new Achievement();
            achievement.setName("Completed First Goal");
            achievement.setDescription("Trainee completed their first Goal");
            achievementRepository.save(achievement);
            Achievement achievement2 = new Achievement();
            achievement2.setName("Completed Fifth Goal");
            achievement2.setDescription("Trainee completed five goals");
            achievementRepository.save(achievement2);
            Achievement achievement3 = new Achievement();
            achievement3.setName("Completed Tenth Goal");
            achievement3.setDescription("Trainee completed ten goals");
            achievementRepository.save(achievement);
        }
        UserAchievement userAchievement=new UserAchievement();

        switch (totalCompletedGoals){
            case 1:
                userAchievement.setAchievementId(achievementRepository.findAchievementsByName("Completed First Goal").getId());
                userAchievement.setUserId(userId);
                userAchievement.setReceivedAt(LocalDate.now());
                userAchievementRepository.save(userAchievement);
                break;
            case 5:
                userAchievement.setAchievementId(achievementRepository.findAchievementsByName("Completed Fifth Goal").getId());
                userAchievement.setUserId(userId);
                userAchievement.setReceivedAt(LocalDate.now());
                userAchievementRepository.save(userAchievement);
                break;
            case 10:
                userAchievement.setAchievementId(achievementRepository.findAchievementsByName("Completed Tenth Goal").getId());
                userAchievement.setUserId(userId);
                userAchievement.setReceivedAt(LocalDate.now());
                userAchievementRepository.save(userAchievement);
                break;
        }
    }
}
