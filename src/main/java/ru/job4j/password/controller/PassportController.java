package ru.job4j.password.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.password.model.Passport;
import ru.job4j.password.repository.PassportRepository;
import ru.job4j.password.service.PassportService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService service;
    private final PassportRepository repository;

    public PassportController(PassportService service, PassportRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/save")
    public Passport save(@Valid @RequestBody Passport password) {
        return service.save(password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> findById(@Valid @PathVariable int id) {
        var password = service.findById(id);
        return new ResponseEntity<Passport>(
                password.orElse(new Passport()),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody Passport password) {
        this.service.save(password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return service.findAll();
    }

    @GetMapping("/find")
    public List<Passport> findBySeries(@Valid @RequestParam int seria) {
        return service.findBySeries(seria);
    }

    @GetMapping("/unavailable")
    public List<Passport> findUnAvailable() {
        return service.findUnAvailable();
    }

    @GetMapping("/replaceable")
    public List<Passport> findReplaceable() {
        return service.findReplaceable();
    }
}
