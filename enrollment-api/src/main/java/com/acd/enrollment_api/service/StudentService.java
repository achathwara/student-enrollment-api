package com.acd.enrollment_api.service;

import com.acd.enrollment_api.model.Course;
import com.acd.enrollment_api.model.Student;
import com.acd.enrollment_api.repository.CourseRepository;
import com.acd.enrollment_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // Get all students with pagination and search
    public Page<Student> getAllStudents(int page, int size, String search, Boolean isActive) {
        return studentRepository.findAllWithFilters(search, isActive, PageRequest.of(page, size));
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(UUID id, Student details) {
        Student student = getStudentById(id);
        student.setFirstName(details.getFirstName());
        student.setLastName(details.getLastName());
        student.setEmail(details.getEmail());
        student.setAge(details.getAge());
        return studentRepository.save(student);
    }

    // Requirement: Soft Delete (set is_active = false)
    public void softDeleteStudent(UUID id) {
        Student student = getStudentById(id);
        student.setActive(false);
        student.setEnrolledCourses(null); // Optional: Clear enrollments on delete
        studentRepository.save(student);
    }

    // Enrollment Logic
    @Transactional
    public Student enrollStudentInCourse(UUID studentId, Long courseId) {
        Student student = getStudentById(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        student.getEnrolledCourses().add(course);
        return studentRepository.save(student);
    }

    public Set<Course> getStudentCourses(UUID studentId) {
        Student student = getStudentById(studentId);
        return student.getEnrolledCourses();
    }
}