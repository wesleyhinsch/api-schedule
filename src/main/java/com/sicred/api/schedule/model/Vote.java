package com.sicred.api.schedule.model;

import com.sicred.api.schedule.model.enums.EnumOption;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.sun.istack.NotNull;

@Entity
@Table(name="VOTE")
@Data
public class Vote implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGENDA_ID", nullable = false)
    private Agenda agenda;

    @NotNull
    private String cpf;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private EnumOption enumOption;


}
