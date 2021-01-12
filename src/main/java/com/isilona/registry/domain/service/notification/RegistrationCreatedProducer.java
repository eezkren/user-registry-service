package com.isilona.registry.domain.service.notification;

import com.isilona.registry.domain.model.Registration;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@CommonsLog
public class RegistrationCreatedProducer implements Notifier<Registration> {

    private final KafkaTemplate<String, Registration> kafkaTemplate;
    private final String topicName;

    public RegistrationCreatedProducer(String topicName, KafkaTemplate<String, Registration> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void notify(Registration message) {

        ListenableFuture<SendResult<String, Registration>> future =
            kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Registration> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
