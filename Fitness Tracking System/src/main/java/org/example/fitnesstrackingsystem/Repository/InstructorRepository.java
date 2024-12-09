package org.example.fitnesstrackingsystem.Repository;

import org.example.fitnesstrackingsystem.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    Instructor findInstructorById(Integer id);

    Instructor findInstructorByEmailAndPassword(String email,String password);
}
