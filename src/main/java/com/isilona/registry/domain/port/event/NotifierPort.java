package com.isilona.registry.domain.port.event;

import com.isilona.registry.domain.event.DomainEvent;

public interface NotifierPort {

    void notify(DomainEvent domainEvent);
}
