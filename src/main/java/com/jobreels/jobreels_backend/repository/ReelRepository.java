package com.jobreels.jobreels_backend.repository;

import com.jobreels.jobreels_backend.model.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReelRepository extends JpaRepository<Reel, Long> {
    List<Reel> findByRemovedFalse();
}