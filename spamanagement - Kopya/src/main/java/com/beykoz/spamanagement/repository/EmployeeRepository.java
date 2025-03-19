    package com.beykoz.spamanagement.repository;

    import com.beykoz.spamanagement.entity.Employee;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        @Query("SELECT e FROM Employee e WHERE e.spa.id = :spaId")
        List<Employee> findBySpaId(@Param("spaId") Long spaId);
    }
