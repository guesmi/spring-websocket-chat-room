package com.messenger.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.messenger.model.Message;
import com.messenger.service.MessageService;

@RestController
@RequestMapping("/")
public class MessageController {

	@Autowired
	MessageService messageService;

	@Autowired
	SimpMessagingTemplate template;

	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<String>> hellos() {
		List<String> response = Arrays.asList("hello", "yeddek fi zebi");
		return new ResponseEntity<List<String>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Message>> findAll() {
		List<Message> response = messageService.findAll();
		return new ResponseEntity<List<Message>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/message", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Message> addMessage(@RequestBody Message message) {
		Message response = messageService.save(message);
		sendNotification();
		return new ResponseEntity<Message>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/message", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> delete(@RequestParam("id") int id) {
		messageService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@SendTo("/topic/notify")
	public void sendNotification() {
		template.convertAndSend("/topic/notify", "newData");
	}

}
