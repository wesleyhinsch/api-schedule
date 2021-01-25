package com.sicred.api.schedule;

import com.sicred.api.schedule.controller.agenda.dto.AgendaDTO;
import com.sicred.api.schedule.controller.vote.dto.VoteDTO;
import com.sicred.api.schedule.model.Agenda;
import com.sicred.api.schedule.model.Vote;
import com.sicred.api.schedule.model.enums.EnumOption;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class TestUtils {

    public static final String INVALID_CPF = "11111111111";
    public static int DEFAULT_CLOSURE = 60;

    private static int random(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    private static int mod(int mod1, int mod2) {
        return (int) Math.round(mod1 - (Math.floor(mod1 / mod2) * mod2));
    }

    public static  String buildCpf() {
        int n = 9;
        int n1 = random(n);
        int n2 = random(n);
        int n3 = random(n);
        int n4 = random(n);
        int n5 = random(n);
        int n6 = random(n);
        int n7 = random(n);
        int n8 = random(n);
        int n9 = random(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        String cpf = null;

        if (d2 >= 10)
            d2 = 0;
        cpf = "";

        cpf = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return cpf;
    }

    public static int buildClosure() {
        long dgt1 = (int) Math.random();
        long dgt2 = (int) Math.random();
        long dgt3 = (int) Math.random();
        String concatDigts = dgt1+""+dgt2+""+dgt3;
        return Integer.parseInt(concatDigts);
    }


    public static ArrayList<Agenda> createAgendas() {

        Calendar current = Calendar.getInstance();
        Date date = new Date(current.getTimeInMillis());

        ArrayList<Agenda> agendas = new ArrayList<>();

        Agenda agenda1 = new Agenda();
        agenda1.setName("agenda "+Math.random());
        agenda1.setClosure(TestUtils.buildClosure());
        current.add(Calendar.SECOND,agenda1.getClosure());
        agenda1.setClosing(date);
        agenda1.setVotes(createVotes(agenda1));
        agenda1.setActive(Boolean.FALSE);
        agenda1.setStarted(Boolean.TRUE);
        agendas.add(agenda1);


        Agenda agenda2 = new Agenda();
        agenda2.setName("agenda "+Math.random());
        agenda2.setClosure(TestUtils.buildClosure());
        current.add(Calendar.SECOND,agenda1.getClosure());
        agenda2.setClosing(date);
        agenda2.setVotes(createVotes(agenda1));
        agenda2.setActive(Boolean.TRUE);
        agenda2.setStarted(Boolean.TRUE);
        agendas.add(agenda2);

        Agenda agenda3 = new Agenda();
        agenda3.setName("agenda "+Math.random());
        agenda3.setClosure(TestUtils.buildClosure());
        current.add(Calendar.SECOND,agenda1.getClosure());
        agenda3.setClosing(date);
        agenda3.setVotes(createVotes(agenda1));
        agenda3.setActive(Boolean.FALSE);
        agenda3.setStarted(Boolean.FALSE);
        agendas.add(agenda3);


        Agenda agenda4 = new Agenda();
        agenda4.setName("agenda "+Math.random());
        agenda4.setClosure(TestUtils.buildClosure());
        current.add(Calendar.SECOND,agenda1.getClosure());
        agenda4.setClosing(date);
        agenda4.setVotes(createVotes(agenda1));
        agenda4.setActive(Boolean.TRUE);
        agenda4.setStarted(Boolean.FALSE);
        agendas.add(agenda4);

        return agendas;
    }

    private static Set<Vote> createVotes(Agenda agenda) {
        Set<Vote> votes = new HashSet<>();

        Vote vote1 = new Vote();
        vote1.setAgenda(agenda);
        vote1.setCpf(TestUtils.buildCpf());
        vote1.setEnumOption(EnumOption.NAO);

        Vote vote2 = new Vote();
        vote2.setAgenda(agenda);
        vote2.setCpf(TestUtils.buildCpf());
        vote2.setEnumOption(EnumOption.NAO);

        Vote vote3 = new Vote();
        vote3.setAgenda(agenda);
        vote3.setCpf(TestUtils.buildCpf());
        vote3.setEnumOption(EnumOption.SIM);

        Vote vote4 = new Vote();
        vote4.setAgenda(agenda);
        vote4.setCpf(TestUtils.buildCpf());
        vote4.setEnumOption(EnumOption.SIM);

        return votes;
    }

    public static  AgendaDTO buildAgendaDto(String name) {
        AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setClosure(DEFAULT_CLOSURE);
        if (name==null) {
            agendaDTO.setName("agenda " + Math.random());
        } else {
            agendaDTO.setName(name);
        }
        return agendaDTO;
    }

    public static VoteDTO buildVoteDto(AgendaDTO agendaDTO, String cpf,boolean optionVote ) {
        VoteDTO voteDTO = new VoteDTO();

        if (cpf==null) {
            voteDTO.setCpf(TestUtils.buildCpf());
        } else {
            voteDTO.setCpf(cpf);
        }

        if (optionVote) {
            voteDTO.setEnumOption(EnumOption.NAO);
        } else {
            voteDTO.setEnumOption(EnumOption.SIM);
        }

        voteDTO.setNameAgenda(agendaDTO.getName());

        return voteDTO;
    }

}
