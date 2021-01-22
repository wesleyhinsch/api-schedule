package com.sicred.api.schedule.jms;

import com.sicred.api.schedule.model.AgendaResult;
import com.sicred.api.schedule.repository.AgendaResultRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.google.common.base.Charsets.UTF_8;

@Configuration
public class AgendaConsumer {

    private static String AGENDA_NAME = "agendaName";
    private static String VOTE_YES = "voteYes";
    private static String VOTE_NO = "voteNo";

    private static Logger logger = LoggerFactory.getLogger(AgendaConsumer.class);

    @Autowired
    AgendaResultRepository agendaResultRepository;
    
    @RabbitListener(queues = AgendaAMQPConfig.QUEUE)
    public void consumer(Message message) {
        String payloadAgendaResult = new String(message.getBody(), UTF_8);
        agendaResultRepository.save(bindPaylodToAgendaResult(payloadAgendaResult));
    }

    private AgendaResult bindPaylodToAgendaResult(String payloadAgendaResult) {
        AgendaResult agendaResult = new AgendaResult();
        try {
            JSONObject json = new JSONObject(payloadAgendaResult);
            String agendaName = json.getString(AGENDA_NAME);
            agendaResult.setAgendaName(agendaName);
            Long voteYes = json.getLong(VOTE_YES);
            agendaResult.setVoteYes(voteYes);
            Long voteNo = json.getLong(VOTE_NO);
            agendaResult.setVoteNo(voteNo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return agendaResult;
    }

}

