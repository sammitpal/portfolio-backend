package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.model.Message;

public interface MessageRepository extends JpaRepository<Message, String>{

}
