package co.crudtest.democrudtest.repositories;

// Unica INTERFACCIA che può comunicare avanti/indietro con il db

import co.crudtest.democrudtest.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositories extends JpaRepository<Student, Long> {
}
