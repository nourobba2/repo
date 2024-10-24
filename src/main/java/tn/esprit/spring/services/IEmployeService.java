package tn.esprit.spring.services;
import java.util.List;
import tn.esprit.spring.entities.Employe;
public interface IEmployeService {
    Employe addEmploye(Employe employe);               // Create
    Employe updateEmploye(Employe employe);            // Update
    void deleteEmploye(Long id);                       // Delete
    Employe getEmployeById(Long id);                   // Retrieve by ID
    List<Employe> getAllEmployes();                    // Retrieve all

    List<Employe> getEmployesByDepartmentAndRole(Long departmentId, String role);

    void updateEmployeeStatus(List<Long> employeIds, boolean status);


}
