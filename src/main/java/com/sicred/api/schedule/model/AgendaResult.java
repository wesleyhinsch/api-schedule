package com.sicred.api.schedule.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AGENDA_RESULT")
@Getter
@Setter
public class AgendaResult implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotNull
    String agendaName;

    @NotNull
    long voteYes;

    @NotNull
    long voteNo;
}
