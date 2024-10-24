package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;

public class EmployeServiceImplMock {

    @Mock
    private EmployeRepository employeRepository;

    @InjectMocks
    private EmployeServiceImpl employeService;

    private Employe employe1, employe2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Préparation des données factices
        Departement dep = new Departement();
        dep.setId(1L);

        employe1 = new Employe(1L, "John", "Doe", "john.doe@mail.com", "1234", true, Role.EMPLOYEE);
        employe1.setDepartements(List.of(dep));

        employe2 = new Employe(2L, "Jane", "Smith", "jane.smith@mail.com", "5678", true, Role.MANAGER);
        employe2.setDepartements(List.of(dep));
    }

    @Test
    public void testGetEmployesByDepartmentAndRole() {
        when(employeRepository.findAll()).thenReturn(Arrays.asList(employe1, employe2));

        List<Employe> result = employeService.getEmployesByDepartmentAndRole(1L, "EMPLOYE");

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getPrenom());
    }

    @Test
    public void testUpdateEmployeeStatus() {
        List<Long> employeIds = Arrays.asList(1L, 2L);
        when(employeRepository.findAllById(employeIds)).thenReturn(Arrays.asList(employe1, employe2));

        employeService.updateEmployeeStatus(employeIds, false);

        assertFalse(employe1.isActif());
        assertFalse(employe2.isActif());

        verify(employeRepository, times(1)).saveAll(anyList());
    }
}
