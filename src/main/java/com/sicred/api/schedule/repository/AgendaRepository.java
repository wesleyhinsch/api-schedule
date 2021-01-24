package com.sicred.api.schedule.repository;

import com.sicred.api.schedule.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByActive(Boolean ativa);
    Boolean existsByName(String name);
    Optional<Agenda> findAgendaByName(String nameAgenda);
}
