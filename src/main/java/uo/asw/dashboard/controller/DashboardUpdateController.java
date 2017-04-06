package uo.asw.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import uo.asw.dashboard.GetComments;
import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.model.Suggestion;
import uo.asw.dbmanagement.model.VoteComment;
import uo.asw.dbmanagement.model.VoteSuggestion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DashboardUpdateController {
    private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<SseEmitter>());
    private static final Logger logger = Logger.getLogger(DashboardUpdateController.class);

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private GetComments getComments;

    @RequestMapping("/newComment")
    @EventListener
    public void newComentary(Comment data){
        SseEmitter.SseEventBuilder newCommentEvent = SseEmitter
                .event().name("event")
                .data("{ \"eventId\": \"newComnent\" ,  \"data\":" + objectToJSON(data.toMap()) +" }");
        logger.info("Enviando evento comentario" + data.getCode());
        sendEvent(newCommentEvent);
    }

    @RequestMapping("/newSuggestion")
    @EventListener
    public void newSuggestion(Suggestion data){
        SseEmitter.SseEventBuilder newSuggestionEvent = SseEmitter
                .event().name("event")
                .data("{ \"eventId\": \"newSuggestion\" ,  \"data\":" + objectToJSON(data.toMap()) +" }");
        sendEvent(newSuggestionEvent);
    }

    @RequestMapping("/newVoteComment")
    @EventListener
    public void newSuggestion(VoteComment data){
        SseEmitter.SseEventBuilder newSuggestionEvent = SseEmitter
                .event().name("event")
                .data("{ \"eventId\": \"newVoteComment\" ,  \"data\":" + objectToJSON(data.toMap()) +" }");
        sendEvent(newSuggestionEvent);
    }

    @RequestMapping("/newVoteSuggestion")
    @EventListener
    public void newSuggestion(VoteSuggestion data){
        SseEmitter.SseEventBuilder newSuggestionEvent = SseEmitter
                .event().name("event")
                .data("{ \"eventId\": \"newVoteSuggestion\" ,  \"data\":" + objectToJSON(data.toMap()) +" }");
        sendEvent(newSuggestionEvent);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/updates")
    public SseEmitter getUpdates(){
        SseEmitter sseEmitter = new SseEmitter();
        synchronized (this.sseEmitters) {
            this.sseEmitters.add(sseEmitter);
            sseEmitter.onCompletion(() -> {
                synchronized (this.sseEmitters) {
                    this.sseEmitters.remove(sseEmitter);
                }
            });
        }
        return sseEmitter;
    }

    private void sendEvent(SseEmitter.SseEventBuilder event){
        synchronized (sseEmitters) {
            for(SseEmitter emitter: sseEmitters){
                try {
                    logger.info("Sending event");
                    emitter.send(event);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    private String objectToJSON(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
