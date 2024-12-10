package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    Instructor findInstructorById(Integer id);

    List<Instructor> findAllByStatus(String status);
}
