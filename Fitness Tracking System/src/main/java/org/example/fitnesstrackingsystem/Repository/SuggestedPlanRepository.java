package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.SuggestedPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestedPlanRepository extends JpaRepository<SuggestedPlan,Integer> {

    SuggestedPlan findSuggestedPlanById(Integer id);

    List<SuggestedPlan> findSuggestedPlansByInstructorIdAndStatus(Integer instructorId, String status);

    List<SuggestedPlan> findSuggestedPlansByUserId(Integer id);
}
