package com.example.mspl_connect.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.RoleEntity;
import com.example.mspl_connect.Repository.DepartmentRepo;
import com.example.mspl_connect.Repository.RoleRepo;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {
    @Autowired 
    private DepartmentRepo departmentRepo;
    
    @Autowired
    private RoleRepo roleRepo;
    
    public DepartmentTableEntity saveDepartment(DepartmentTableEntity department) {
        return departmentRepo.save(department);
    }
    
    public int totalNoOfDepts() {
    	return departmentRepo.totalNoDepartment();
    }
    
    public int totalNoOfRoles() {
    	return roleRepo.totalNoOfRole();
    }
    
    @Transactional
    public void updateDepartment(DepartmentTableEntity department) {
        // Fetch the existing department from the database
        DepartmentTableEntity existingDepartment = departmentRepo.findById(department.getDept_id())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Update the existing department details with the new data
        existingDepartment.setDeptName(department.getDeptName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setSubDepartment(department.getSubDepartment());
        // Save the updated department back to the database
        departmentRepo.save(existingDepartment);
    }
    
    public List<DepartmentTableEntity> getAllDepartments() {
        return departmentRepo.findAll();
    }
    
    public Optional<DepartmentTableEntity> findByDept_name(String dept_name) {
        return departmentRepo.findByDeptName(dept_name);
    }
    
    public RoleEntity saveRole(RoleEntity Role) {
        return roleRepo.save(Role);
    }
    
    // Method to update an existing role
    @Transactional
    public void updateRole(RoleEntity role) {
        // Fetch the existing role
        RoleEntity existingRole = roleRepo.findById(role.getRole_id())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Update the existing role
        existingRole.setRoleName(role.getRoleName());
        existingRole.setDescription(role.getDescription());
        existingRole.setDepartment(role.getDepartment());

        // Save the updated role
        roleRepo.save(existingRole);
    }
    
    
    public Optional<RoleEntity> findByRoleNameAndDepartmentId(String roleName,int deptId) {
        return roleRepo.findByRoleNameAndDepartment(roleName,deptId);
    } 
    
    public List<RoleEntity> getAllRolesWithDeptName() {
        List<Object[]> results = roleRepo.findAllRolesWithDeptName();
        List<RoleEntity> roles = new ArrayList<>();
        
        for (Object[] result : results) {
            RoleEntity role = new RoleEntity();
            role.setRole_id((Integer) result[0]);
            role.setRoleName((String) result[1]);
            role.setDescription((String) result[2]);
            
            // Convert Timestamp to LocalDateTime
            Timestamp timestamp = (Timestamp) result[3];
            role.setCreatedDate(timestamp != null ? timestamp.toLocalDateTime() : null);
            
            role.setDepartment((Integer) result[4]);
            role.setDeptName((String) result[5]);
            
            roles.add(role);
        }
        
        return roles;
    }
    
    @Transactional
    public void deleteRole(int roleId) {
        roleRepo.deleteByRole_id(roleId);
    }
    
    @Transactional
    public void deleteDepat(int deptId) {
    	 try {
    	        departmentRepo.deleteByDeptId(deptId);
    	    } catch (DataIntegrityViolationException e) {
    	        throw new DataIntegrityViolationException("Cannot delete department: foreign key constraint fails", e);
    	    }
    }
    
    @Transactional
    public void deleteDepartment(int deptId) {
        try {
            // Attempt to delete roles first
            deleteRoleByDeptId(deptId);
            // Then attempt to delete the department
            deleteRoleAndDept(deptId);
        } catch (DataIntegrityViolationException e) {
            // Handle exception, optionally rethrow or log it
            throw new DataIntegrityViolationException("Cannot delete department: foreign key constraint fails", e);
        }
    }

    @Transactional
    public void deleteRoleByDeptId(int deptId) {
        try {
            departmentRepo.deleteRoleByDeptId(deptId);
        } catch (Exception e) {
            // Handle exception, log it or rethrow if needed
            throw new RuntimeException("Failed to delete roles", e);
        }
    }

    @Transactional
    public void deleteRoleAndDept(int deptId) {
        try {
            departmentRepo.deleteByDeptId(deptId);
        } catch (DataIntegrityViolationException e) {
            // Handle exception, log it or rethrow if needed
            throw new DataIntegrityViolationException("Cannot delete department: foreign key constraint fails", e);
        }
    }
    
   
   
}