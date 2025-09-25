package com.example.mspl_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    
}
