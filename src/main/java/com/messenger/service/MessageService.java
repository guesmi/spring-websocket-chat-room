package com.messenger.service;

import java.util.List;

import com.messenger.model.Message;

public interface MessageService {

	Message save(Message message);

	void delete(int id);

	Message findMessageById(int id);

	List<Message> findAll();
}
