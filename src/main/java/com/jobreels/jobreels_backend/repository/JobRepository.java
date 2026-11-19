package com.jobreels.jobreels_backend.repository;

import com.jobreels.jobreels_backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatusOrderByPostedAtDesc(String status);
}