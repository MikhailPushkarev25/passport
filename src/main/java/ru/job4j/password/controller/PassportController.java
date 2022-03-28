package ru.job4j.password.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.password.model.Passport;
import ru.job4j.password.repository.PassportRepository;
import ru.job4j.password.service.PassportService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService service;

    public PassportController(PassportService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Passport save(@Valid @RequestBody Passport password) {
        return service.save(password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Valid @PathVariable int id) {
        var passport = this.service.findById(id);
        passport.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "ResponseStatusException in findByIdController"));
        return new ResponseEntity<>(
                passport.orElse(new Passport()),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody Passport password) {
        return new ResponseEntity<>(service.save(password) != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@Valid @RequestParam int id) {
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
