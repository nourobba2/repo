package tn.esprit.spring.repository;

import tn.esprit.spring.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeRepository extends JpaRepository<Employe, Long>{

}
