package com.acd.enrollment_api.repository;

import com.acd.enrollment_api.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM Student s WHERE " +
            "(:isActive IS NULL OR s.isActive = :isActive) AND " +
            "(:search IS NULL OR " +
            "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Student> findAllWithFilters(@Param("search") String search,
                                     @Param("isActive") Boolean isActive,
                                     Pageable pageable);
}

