package ru.job4j.password.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.password.model.Passport;

import java.util.Calendar;
import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {


    List<Passport> findBySeria(int series);

    @Query("from Passport where calendar < current_timestamp")
    List<Passport> findUnAvailable();

    @Query("from Passport where calendar between current_timestamp and :current")
    List<Passport> findReplaceable(@Param("current") Calendar calendar);
}
