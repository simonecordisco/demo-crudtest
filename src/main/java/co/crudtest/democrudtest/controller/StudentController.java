package co.crudtest.democrudtest.controller;

import co.crudtest.democrudtest.entities.Student;
import co.crudtest.democrudtest.repositories.StudentRepositories;
import co.crudtest.democrudtest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@Controller
public class StudentController {

    @Autowired
    private StudentRepositories studentRepositories;
    @Autowired
    private StudentService StudentService;


/*1 C.CREATE*/
    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student){
        return studentRepositories.save(student);

    }
/*2 R.READ */
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent(){
        return studentRepositories.findAll();

    }
/*3 R.READ*/
    @GetMapping("/{id}/getStudentById")
    public Student getStudentById(@PathVariable long id) {
        return studentRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));

    }

        @GetMapping(path="/check")
                public @ResponseBody String CheckTest(){
            return "checkTest";
        }

    /*4 U.UPDATE */

    /*5 U.UPDATE*/
    @PatchMapping(path = {"/{id}/", "/{id}/isWorking"})
    public ResponseEntity<Student> updateStudentWorking(@PathVariable long id, /*isWorking?isWorking=true*/@RequestParam("isWorking") boolean isWorking){
         Optional<Student> existingStudentOptional = studentRepositories.findById(id);
        if(existingStudentOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
       // Student student = studentRepositories.findById(id).get();
        Student student = existingStudentOptional.get();
        student.setWorking(isWorking);
        Student updatedStudent = studentRepositories.save(student);
        return ResponseEntity.ok(updatedStudent);
    }


    /*6 D.DELETE */
    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable long id){
        try{
        studentRepositories.deleteById(id);
        return true;
    }catch (IllegalArgumentException e) {
        return false;
        }
        }
}
