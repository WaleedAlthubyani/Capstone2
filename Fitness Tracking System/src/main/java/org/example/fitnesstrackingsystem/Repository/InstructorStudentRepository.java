package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.InstructorStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorStudentRepository extends JpaRepository<InstructorStudent,Integer> {

    List<InstructorStudent> findInstructorStudentsByInstructorId(Integer id);

    InstructorStudent findInstructorStudentByInstructorIdAndUserId(Integer instructorId,Integer UserId);
}
