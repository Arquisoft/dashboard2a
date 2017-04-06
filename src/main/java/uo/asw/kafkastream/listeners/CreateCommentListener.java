package uo.asw.kafkastream.listeners;

import javax.annotation.ManagedBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.dbmanagement.model.Comment;
import uo.asw.dbmanagement.repository.CommentRepository;
import uo.asw.kafkastream.Topics;

import java.io.IOException;

@ManagedBean(value = "createComment")
public class CreateCommentListener implements ApplicationEventPublisherAware {

    private static final Logger logger = Logger.getLogger(CreateCommentListener.class);
    private ApplicationEventPublisher publisher;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private CommentRepository commentRepository;

    @KafkaListener(topics = Topics.CREATE_COMMENT)
    public void listen(String data) {
        logger.info("New message received in CreateComment: \"" + data + "\"");
        Comment c = null;
        try {
            c = mapper.readValue(data, Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (c != null) {
            c = commentRepository.findOne(c.getId());
            if (c != null){
                System.out.println("ELCOMENTARIO = " + c);
                publisher.publishEvent(c);
            }
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
