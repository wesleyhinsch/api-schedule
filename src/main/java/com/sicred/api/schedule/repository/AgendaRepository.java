package com.sicred.api.schedule.repository;

import com.sicred.api.schedule.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Agenda findAgendaByName(String nome);
    List<Agenda> findByActive(Boolean ativa);
}
