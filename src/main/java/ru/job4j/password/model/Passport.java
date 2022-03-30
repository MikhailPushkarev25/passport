package ru.job4j.password.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint
                (name = "UniqueSeriaAndNumber", columnNames = {
        "seria", "number"})})
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    @Column(unique = true)
    private int seria;

    @Column(unique = true)
    private int number;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd.mm.yyyy")
    private Calendar expirationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSeria() {
        return seria;
    }

    public void setSeria(int seria) {
        this.seria = seria;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Calendar getCalendar() {
        return expirationDate;
    }

    public void setCalendar(Calendar calendar) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", seria=" + seria +
                ", number=" + number +
                ", expirationDate=" + expirationDate.getTime() +
                '}';
    }
}
