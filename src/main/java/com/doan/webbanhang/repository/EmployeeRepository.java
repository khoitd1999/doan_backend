package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Area;
import com.doan.webbanhang.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from Employee where role = ?1", nativeQuery = true)
    List<Employee> findAllByRole(String role);

    @Query(value = "select * from Employee where username = ?1 and password = ?2", nativeQuery = true)
    Employee checkLoginAdmin(String username, String password);
}
