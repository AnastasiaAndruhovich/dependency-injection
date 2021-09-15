package by.andruhovich.di.service;

import org.springframework.stereotype.Service;

public class SetterGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello World! - Setter";
    }
}
