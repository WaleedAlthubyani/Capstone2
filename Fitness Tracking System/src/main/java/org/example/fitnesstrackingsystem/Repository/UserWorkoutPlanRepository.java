package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.UserWorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkoutPlanRepository extends JpaRepository<UserWorkoutPlan,Integer> {

    UserWorkoutPlan findUserWorkoutPlanByUserId(Integer id);

    UserWorkoutPlan findUserWorkoutPlanByUserIdAndWorkoutPlanId(Integer user,Integer plan);
}
