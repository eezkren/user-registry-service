package com.isilona.registry.infrastracture.adapter.notification;

import com.isilona.registry.domain.event.DomainEvent;
import com.isilona.registry.domain.model.Registration;
import com.isilona.registry.domain.port.event.NotifierPort;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@CommonsLog
public class KafkaNotifierAdapter implements NotifierPort {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;
    private final String topicName;

    public KafkaNotifierAdapter(String topicName, KafkaTemplate<String, DomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void notify(DomainEvent message) {

        ListenableFuture<SendResult<String, DomainEvent>> future =
            kafkaTemplate.send(topicName, message);


        //            kafkaTemplate.send(message.getTopic(), message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, DomainEvent> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
