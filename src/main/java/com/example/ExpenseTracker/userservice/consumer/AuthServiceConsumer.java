package com.example.ExpenseTracker.userservice.consumer;

import com.example.ExpenseTracker.userservice.entities.UserInfo;
import com.example.ExpenseTracker.userservice.entities.UserInfoDTO;
import com.example.ExpenseTracker.userservice.repository.UserRepository;
import com.example.ExpenseTracker.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer
{

    @Autowired
    private UserService userService;



    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDTO eventData) {
        try{
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc
            userService.createOrUpdateUser(eventData);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }

}
