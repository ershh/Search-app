package com.example.searchApp.search;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.searchApp.reciever.Receiver;

@Service
public class SearchService {
	
	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;
	

	public SearchService(RabbitTemplate rabbitTemplate, Receiver receiver) {
		this.rabbitTemplate = rabbitTemplate;
		this.receiver = receiver;
		}



	public List<Listing> getSearchResults(String searchKey) throws InterruptedException
	{
		rabbitTemplate.convertAndSend("search-queue",searchKey);
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

		//for setting up rabbitMQ later

		List<Listing> test = List.of(
				new Listing("1",20000,"car"),
				new Listing("2",1000,"bike"),
				new Listing("3",3000,"tv"),
				new Listing("4",9000,"boat"),
				new Listing("5",7000,"mobile"),
				new Listing("6",6000,"laptop")

				);
		
		List<Listing> results = new ArrayList<Listing>();
		
		System.out.println("Searching for: " + searchKey);
		
			
		if(searchKey.equals("")) {
			return test;
		}
		else
		{
			for (int i = 0; i < test.size(); i++) {
				if(test.get(i).title.contains(searchKey))
				{
					results.add(test.get(i));
				}
			}
		}
		return results;
	}
}
