package com.example.mspl_connect.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.DepartmentTableEntity;
import com.example.mspl_connect.Entity.RoleEntity;


@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {
    
    Optional<RoleEntity> findByRoleNameAndDepartment(String dept_name,int deptId);
    
    
    @Query(value = "SELECT r.role_id, r.role_name, r.description, r.created_date, r.dept_id, d.dept_name " +
            "FROM roles r " +
            "INNER JOIN departments d ON r.dept_id = d.dept_id", nativeQuery = true)
    List<Object[]> findAllRolesWithDeptName();
    
    List<RoleEntity> findByDepartment(int deptId);
    
    @Modifying
    @Query("DELETE FROM RoleEntity r WHERE r.role_id = :roleId")
    void deleteByRole_id(@Param("roleId") int roleId);
    
    @Query(nativeQuery = true,value="SELECT count(*) FROM roles")
    int totalNoOfRole();
    
}
