package co.crudtest.democrudtest;

import co.crudtest.democrudtest.controller.StudentController;

import co.crudtest.democrudtest.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class StudentControllerTest {
@Autowired
private StudentController studentController;
@Autowired
    private MockMvc mvc;

@Autowired
private ObjectMapper objm;
    @Test
    void studentControllerLoad(){
assertThat(studentController).isNotNull();
    }
    private MvcResult addStudent() throws Exception{
        Student student = new Student();
        student.setWorking(true);
        student.setName("simone");
        student.setSurname("cordisco");
        return addStudent(student);
    }
    private MvcResult addStudent(Student student) throws Exception{
      if(student == null) return null;
        String studentJson = objm.writeValueAsString(student);

        return this .mvc.perform(post("/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void addStudentTest() throws Exception {
         MvcResult result = addStudent();
         Student studentTest = objm.readValue(result.getResponse().getContentAsString(), Student.class); /*leggi i lavore indicato e converti in student*/
                 assertThat(studentTest.getId()).isNotNull();

    }
    @Test
    void getAllStudent() throws Exception {
         addStudent();


        MvcResult result =  this .mvc.perform(get("/getAllStudent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<Student> studentListTest = objm.readValue(result.getResponse().getContentAsString(), List.class); /*leggi i lavore indicato e converti in student*/
        System.out.println("Student in db are: " + studentListTest.size());
        assertThat(studentListTest.size()).isNotZero();

    }


}
