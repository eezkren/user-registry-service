package com.isilona.registry.domain.service.notification;

public interface Notifier<T> {

    void notify(T notificationObject);
}
