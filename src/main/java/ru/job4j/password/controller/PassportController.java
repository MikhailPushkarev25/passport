package ru.job4j.password.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.password.model.Passport;
import ru.job4j.password.service.PassportService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService service;

    public PassportController(PassportService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Passport save(@RequestBody Passport password) {
        return service.save(password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> findById(@PathVariable int id) {
        var password = service.findById(id);
        return new ResponseEntity<Passport>(
                password.orElse(new Passport()),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Passport password) {
        this.service.save(password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<Passport> findAll() {
        return StreamSupport.stream(
                this.service.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("/find")
    public List<Passport> findBySeries(@RequestParam int seria) {
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
