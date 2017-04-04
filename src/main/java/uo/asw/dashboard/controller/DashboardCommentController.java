package uo.asw.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import uo.asw.dashboard.GetComments;
import uo.asw.dbmanagement.model.Comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DashboardCommentController {
    private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<SseEmitter>());

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private GetComments getComments;

    @RequestMapping("/newComment")
    @EventListener
    public void newComentary(Long data){
        Comment c = getComments.getCommentById(data);
        SseEmitter.SseEventBuilder newCommentEvent = SseEmitter
                .event().name("evento")
                .data("{ \"tipo\": \"newComnent\" ,  \"comment\":" + objectToJSON(c.toMap()) +" }");
        sendEvent(newCommentEvent);
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
                    System.out.println("Enviando el evento");
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
