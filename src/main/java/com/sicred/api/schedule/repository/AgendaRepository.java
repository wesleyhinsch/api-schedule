package com.sicred.api.schedule.repository;

import com.sicred.api.schedule.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Agenda findAgendaByName(String nome);
    //List<Agenda> findByAtiva(Boolean ativa);
}
