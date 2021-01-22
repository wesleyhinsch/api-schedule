package com.sicred.api.schedule.jms.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgendaPayload {

    String agendaName;

    long voteYes;

    long voteNo;

}
