package ru.job4j.password.service;

import org.springframework.stereotype.Service;
import ru.job4j.password.model.Passport;
import ru.job4j.password.repository.PassportRepository;
import java.util.*;

@Service
public class PassportService {

    private final PassportRepository repository;

    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    public Passport save(Passport password) {
        return repository.save(password);
    }

    public Optional<Passport> findById(int id) {
        return repository.findById(id);
    }

    public void delete(int id) {
        Passport password = new Passport();
        password.setId(id);
        repository.delete(password);
    }

    public List<Passport> findAll() {
        return (List<Passport>) repository.findAll();
    }

    public List<Passport> findBySeries(int seria) {
        return repository.findBySeria(seria);
    }

    public List<Passport> findUnAvailable() {
        return repository.findUnAvailable();
    }

    public List<Passport> findReplaceable() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.roll(Calendar.MONTH, 3);
        return repository.findReplaceable(calendar);
    }

}
