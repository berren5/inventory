package com.barclays.inventory.commands;

import org.springframework.http.ResponseEntity;

public interface CommandStrategy {

    ResponseEntity execute();

    CommandStrategy processCommand(String[] commandArray);
}
