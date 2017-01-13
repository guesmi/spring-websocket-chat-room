package com.messenger.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messenger.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Serializable> {

	Message findById(int id);
}
