package com.messenger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.model.Message;
import com.messenger.repository.MessageRepository;

@Service
public class MesageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Override
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public void delete(int id) {
		Message messageToDelete = messageRepository.findById(id);
		if (messageToDelete != null) {
			messageRepository.delete(messageToDelete);
		}
	}

	@Override
	public Message findMessageById(int id) {
		return messageRepository.findById(id);
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

}
