package com.sicred.api.schedule.repository;

import com.sicred.api.schedule.model.AgendaResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaResultRepository extends JpaRepository<AgendaResult, Long> {
}
