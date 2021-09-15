package by.andruhovich.di.service;

public class PrimaryGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World! - From the Primary Bean";
    }
}
