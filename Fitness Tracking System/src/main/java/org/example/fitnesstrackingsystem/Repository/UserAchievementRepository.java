package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement,Integer> {

    List<UserAchievement> findUserAchievementsByUserId(Integer id);
}
