package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.EmployeRepository;


import java.util.List;

@Service
public class EmployeServiceImpl implements IEmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    // Create
    @Override
    public Employe addEmploye(Employe employe){
        Employe e = employeRepository.save(employe);
        return e ;
    }

    // Update
    @Override
    public Employe updateEmploye(Employe employe) {
        if (employeRepository.existsById(employe.getId())) {
            return employeRepository.save(employe);
        } else {
            throw new RuntimeException("Employe not found with ID: " + employe.getId());
        }
    }


    // Delete
    @Override
    public void deleteEmploye(Long id) {
        if (employeRepository.existsById(id)) {
            employeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employe not found with ID: " + id);
        }
    }

    // Retrieve by ID
    @Override
    public Employe getEmployeById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employe not found with ID: " + id));
    }

    // Retrieve all employees
    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public List<Employe> getEmployesByDepartmentAndRole(Long departmentId, String role) {
        return employeRepository.findAll().stream()
                .filter(employe -> employe.getDepartements().stream()
                        .anyMatch(dep -> dep.getId().equals(departmentId)) &&
                        employe.getRole().toString().equalsIgnoreCase(role))
                .toList();
    }

    @Override
    public void updateEmployeeStatus(List<Long> employeIds, boolean status) {
        List<Employe> employes = employeRepository.findAllById(employeIds);
        if (employes.isEmpty()) {
            throw new RuntimeException("No employees found with the provided IDs");
        }
        employes.forEach(employe -> employe.setActif(status));
        employeRepository.saveAll(employes);
    }
}