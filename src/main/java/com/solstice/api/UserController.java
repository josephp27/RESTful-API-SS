package com.solstice.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    StudentJdbcRepository repository;
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/add")
    @ResponseBody
    public String insert(@RequestParam(name="name", defaultValue="JohnDoe") String user,
                         @RequestParam(name="email", defaultValue="AAAAAAAA") String email,
                         @RequestParam(name="work", defaultValue="AAAAAAAA") String number_work,
                         @RequestParam(name="personal", defaultValue="AAAAAAAA") String number_personal) {
        repository.insert(new Student(counter.incrementAndGet(), user, email, number_work, number_personal));
        return "saved";
    }

    @GetMapping(value="/get", params = {})
    @ResponseBody
    public List<Student> getVal() {
        return repository.findAll();
    }

    @GetMapping(value = "/get", params = {"email"})
    @ResponseBody
    public Student getVal(@RequestParam(name="email") String email) {
        return repository.findByEmail(email);
    }

    @GetMapping(value = "/get", params = {"work_number"})
    @ResponseBody
    public Student getVal2(@RequestParam(name="work_number") String number) {
        return repository.findByWorkNumber(number);
    }
}