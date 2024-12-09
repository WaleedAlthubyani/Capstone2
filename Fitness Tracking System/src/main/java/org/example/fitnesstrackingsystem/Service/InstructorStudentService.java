package org.example.fitnesstrackingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstrackingsystem.Model.InstructorStudent;
import org.example.fitnesstrackingsystem.Repository.InstructorStudentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorStudentService {

    private final InstructorStudentRepository instructorStudentRepository;

    public void addStudent(InstructorStudent instructorStudent){
        instructorStudentRepository.save(instructorStudent);
    }
}
