package ru.job4j.password.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.password.model.Passport;

import java.util.GregorianCalendar;

@EnableKafka
@Service
public class EmailService {

    @KafkaListener(topics = "unAvailable")
    public void orderListener(ConsumerRecord<Long, Passport> record) {
        System.out.println(new GregorianCalendar().getTime() + " Паспорт просрочен на:");
        System.out.println(record.value());
    }
}
