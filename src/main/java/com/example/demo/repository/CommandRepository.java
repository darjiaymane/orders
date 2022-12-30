package com.example.demo.repository;

import com.example.demo.domain.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
    Command findByUuid(String reference);
}
