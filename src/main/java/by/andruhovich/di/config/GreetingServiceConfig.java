package by.andruhovich.di.config;

import by.andruhovich.di.datasource.FakeDataSource;
import by.andruhovich.di.repository.EnglishGreetingRepository;
import by.andruhovich.di.repository.EnglishGreetingRepositoryImpl;
import by.andruhovich.di.service.*;
import com.andruhovich.di.PetService;
import com.andruhovich.di.PetServiceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(ConstructorConfigurationBean.class)
@ImportResource("classpath:di-config.xml")
@Configuration
public class GreetingServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(ConstructorConfigurationBean constructorConfigurationBean) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(constructorConfigurationBean.getUsername());
        fakeDataSource.setPassword(constructorConfigurationBean.getPassword());
        fakeDataSource.setJdbcurl(constructorConfigurationBean.getJdbcurl());
        return fakeDataSource;
    }

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Profile({"cat"})
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishGreetingService() {
        return new I18nSpanishGreetingService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean("i18nService")
    I18nEnglishGreetingService i18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    /*@Bean
    ConstructorGreetingService constructorGreetingService() {
        return new ConstructorGreetingService();
    }*/

    @Bean
    PropertyGreetingService propertyGreetingService() {
        return new PropertyGreetingService();
    }

    @Bean
    SetterGreetingService setterGreetingService() {
        return new SetterGreetingService();
    }
}
