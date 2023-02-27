package com.team6.repository;

import org.springframework.data.repository.CrudRepository;

import com.team6.domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
