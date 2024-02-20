package co.crudtest.democrudtest;

import co.crudtest.democrudtest.entities.Student;
import co.crudtest.democrudtest.repositories.StudentRepositories;
import co.crudtest.democrudtest.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentServicesTest {

    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepositories studentRepositories;

    @Test
    void checkStudentService() throws Exception {
        Student student = new Student();
        student.setWorking(true);
        student.setName("simone");
        student.setSurname("cordisco");

        Student studentDb = studentRepositories.save(student);
        assertThat(studentDb.getId()).isNotNull();

        Student studentFromService = studentService.setStudentIsWorking(student.getId(), true);
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentDb.getId()).isEqualTo(studentDb.getId());

    }
}
