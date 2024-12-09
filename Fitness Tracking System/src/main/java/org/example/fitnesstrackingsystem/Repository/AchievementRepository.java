package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Integer> {

    Achievement findAchievementsByName(String name);
}
