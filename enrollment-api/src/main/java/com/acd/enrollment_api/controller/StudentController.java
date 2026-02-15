package com.acd.enrollment_api.controller;

import com.acd.enrollment_api.model.Course;
import com.acd.enrollment_api.model.Student;
import com.acd.enrollment_api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // GET /students?page=0&size=10&search=john&is_active=true
    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean is_active) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size, search, is_active));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.softDeleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // Enrollment Endpoint: POST /students/{student_id}/enroll/{course_id}
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enrollInCourse(@PathVariable UUID studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.enrollStudentInCourse(studentId, courseId));
    }

    // GET /students/{student_id}/courses
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<Set<Course>> getEnrolledCourses(@PathVariable UUID studentId) {
        return ResponseEntity.ok(studentService.getStudentCourses(studentId));
    }
}
