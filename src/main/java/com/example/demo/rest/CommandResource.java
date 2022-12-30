package com.example.demo.rest;



import com.example.demo.domain.Command;
import com.example.demo.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class CommandResource {

    @Autowired
    CommandService commandService;

    @PostMapping @ResponseBody
    public Command save(@RequestBody @Valid Command command) {
        return commandService.save(command);
    }


}
