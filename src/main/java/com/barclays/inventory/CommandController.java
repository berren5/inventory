package com.barclays.inventory;

import com.barclays.inventory.commands.CommandFactory;
import com.barclays.inventory.commands.CommandStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CommandController {

    @Autowired
    private CommandFactory actionFactory;

    @RequestMapping(value = "/command", method = RequestMethod.POST)
    public ResponseEntity command(@RequestBody Map<String,String> command){
        String commandString = command.get("command");
        CommandStrategy commandStrategy = actionFactory.getCommand(commandString);
        return commandStrategy.execute();
    }
}
