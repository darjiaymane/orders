package com.example.demo.service;


import com.example.demo.domain.Command;
import com.example.demo.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CommandService {

    @Autowired
    CommandRepository commandRepository;

    @Autowired
    OrderItemService orderItemService;
    public Command save(Command command){
        command.setUuid(UUID.randomUUID());
        command.setCreatedAt(LocalDateTime.now());
        command =  commandRepository.save(command);
        Command finalCommand = command;
        command.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(finalCommand);
            orderItemService.save(orderItem);
        });
        return command;
    }
}
