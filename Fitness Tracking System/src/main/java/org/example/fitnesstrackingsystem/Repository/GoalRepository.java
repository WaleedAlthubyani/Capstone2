package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal,Integer> {

    Goal findGoalById(Integer id);

    List<Goal> findGoalsByUserId(Integer id);

    @Query("select g from Goal g where g.userId=?1 and g.progress=?2")
    List<Goal> userGoalsByProgress(Integer id,Integer progress);

    @Query("select g from Goal g where g.userId=?1 and (g.progress between ?2 and ?3)")
    List<Goal> userGoalsByProgressRange(Integer id,Integer progressMin,Integer progressMax);

    List<Goal> findGoalsByUserIdAndProgress(Integer id,Integer progress);

}
