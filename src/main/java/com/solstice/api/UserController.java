package com.solstice.api;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.jvm.hotspot.debugger.AddressException;

@Controller
public class UserController {
    @Autowired
    ContactJdbcRepository repository;
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/add")
    @ResponseBody
    public String insert(@RequestParam(name="name") String user,
                         @RequestParam(name="email") String email,
                         @RequestParam(name="work") String number_work,
                         @RequestParam(name="personal", required = false, defaultValue = "") String number_personal,
                         @RequestParam(name="company", required = false, defaultValue = "") String company) {

        Contact newContact = new Contact(counter.incrementAndGet(), user, email, number_work, number_personal, company);

        Validatior.validate(newContact);
        repository.insert(newContact);
        return "saved\n";
    }

    @GetMapping(value="/get", params = {})
    @ResponseBody
    public List<Contact> getVal() {
        return repository.findAll();
    }

    @GetMapping(value = "/get", params = {"email"})
    @ResponseBody
    public Contact getVal(@RequestParam(name="email") String email) {
        return repository.findByEmail(email);
    }

    @GetMapping(value = "/get", params = {"work_number"})
    @ResponseBody
    public Contact getVal2(@RequestParam(name="work_number") String number) {
        return repository.findByWorkNumber(number);
    }
}