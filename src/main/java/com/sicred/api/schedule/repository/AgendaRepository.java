package com.sicred.api.schedule.repository;

import com.sicred.api.schedule.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Optional<Agenda> findAgendaByName(String nome);
    //List<Agenda> findByAtiva(Boolean ativa);
}
