package uo.asw.kafkastream.listeners;

import javax.annotation.ManagedBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.dbmanagement.model.VoteComment;
import uo.asw.dbmanagement.repository.VoteCommentRepository;
import uo.asw.kafkastream.Topics;

import java.io.IOException;
import java.util.Map;

@ManagedBean(value = "positiveComment")
public class PositiveVoteCommentListener implements ApplicationEventPublisherAware {

    private static final Logger logger = Logger.getLogger(PositiveVoteCommentListener.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private VoteCommentRepository voteCommentRepository;

    @KafkaListener(topics = Topics.POSITIVE_VOTE_COMMENT)
    public void listen(String data) {
        logger.info("New message received in PositiveVoteComment: \"" + data + "\"");
        VoteComment vc = null;
        Map<String, Object> map = null;
        try {
            vc = mapper.readValue(data, VoteComment.class);
            map = mapper.readValue(data, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (vc != null) {
            Long citizenId =Long.parseLong(map.get("citizen_id").toString());
            Long commentId = Long.parseLong(map.get("comment_id").toString());
            VoteComment event = voteCommentRepository.findByCitizenIdAndCommentId(citizenId, commentId);
            publisher.publishEvent(event);
        }
    }

    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
