package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;

@SpringBootTest
public class EmployeServiceImplTest {

    @Autowired
    private EmployeServiceImpl employeService;

    @Autowired
    private EmployeRepository employeRepository;

    @BeforeEach
    public void setUp() {
        employeRepository.deleteAll();  // Nettoyage de la base H2

        // Insertion de données de test
        Employe employe1 = new Employe("John", "Doe", "john.doe@mail.com", true, Role.EMPLOYEE);
        Employe employe2 = new Employe("Jane", "Smith", "jane.smith@mail.com", true, Role.MANAGER);

        employeRepository.save(employe1);
        employeRepository.save(employe2);
    }

    @Test
    public void testFindAllEmployees() {
        List<Employe> employees = employeService.getAllEmployes();
        assertEquals(2, employees.size());
    }

    @Test
    public void testUpdateEmployeeStatus() {
        List<Employe> employees = employeService.getAllEmployes();
        employeService.updateEmployeeStatus(List.of(employees.get(0).getId()), false);

        Employe updatedEmployee = employeRepository.findById(employees.get(0).getId()).orElse(null);
        assertNotNull(updatedEmployee);
        assertFalse(updatedEmployee.isActif());
    }
}
