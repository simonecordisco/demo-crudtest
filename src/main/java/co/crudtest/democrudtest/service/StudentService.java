package co.crudtest.democrudtest.service;

import co.crudtest.democrudtest.entities.Student;
import co.crudtest.democrudtest.repositories.StudentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
//
@Service
public class StudentService {

    @Autowired
    private StudentRepositories studentRepositories;
    public void setStudentIsWorking(Long id, boolean isWorking){
        Student student =  studentRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        student.setWorking(!student.isWorking());
        studentRepositories.save(student);
    }
}
