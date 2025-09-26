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

import jakarta.transaction.Transactional;


@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentTableEntity, Integer> {
	
    Optional<DepartmentTableEntity> findByDeptName(String dept_name);
    
    @Query(nativeQuery = true, value = "SELECT * FROM departments where dept_id <> 0 order by dept_name asc")
    List<DepartmentTableEntity> findAllDeptsExceptSuperAdmin();
    
    @Modifying
    @Query(nativeQuery = true,value="delete from departments where dept_id=:deptId")    
	void deleteByDeptId(int deptId);
    
    @Modifying
    @Query(nativeQuery = true,value="delete from roles where dept_id=:deptId")
    void deleteRoleByDeptId(int deptId);
    
    @Query(nativeQuery = true,value="SELECT count(*) FROM departments")
    int totalNoDepartment();
    
    @Query(nativeQuery = true,value="select * from departments where dept_id=:deptId")
    List<DepartmentTableEntity> findByDepartment(int deptId);
    	
	@Query(nativeQuery = true,
	value="SELECT dept_name FROM mspl_connect.departments where dept_id=:deptId")
	String findDeptNameByDeptId(int deptId);
	
	@Query(nativeQuery = true,value= "SELECT d.dept_id FROM departments d WHERE d.dept_name = :deptName")
    Integer findIdByDeptName(@Param("deptName") String deptName);
    
}
