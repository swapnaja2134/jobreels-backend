package com.jobreels.jobreels_backend.repository;

import com.jobreels.jobreels_backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderBySentAtAsc();
}