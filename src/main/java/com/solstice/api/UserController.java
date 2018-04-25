package com.solstice.api;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                         @RequestParam(name="company", required = false, defaultValue = "") String company,
                         @RequestParam(name="street", required = false, defaultValue = "") String street,
                         @RequestParam(name="state", required = false, defaultValue = "") String state,
                         @RequestParam(name="city", required = false, defaultValue = "") String city,
                         @RequestParam(name="image", required = false, defaultValue = "") String image,
                         @RequestParam(name="bd", required = false, defaultValue = "") String bd) {

        Contact newContact = new Contact(counter.incrementAndGet(), user, email, number_work, number_personal, company,
                street, state, city, image, bd);

        repository.insert(newContact);
        return "Contact Added!\n";
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateRecord(@RequestParam(name="name") String user,
                         @RequestParam(name="email") String email,
                         @RequestParam(name="work") String number_work,
                         @RequestParam(name="personal", required = false, defaultValue = "") String number_personal,
                         @RequestParam(name="company", required = false, defaultValue = "") String company,
                         @RequestParam(name="street", required = false, defaultValue = "") String street,
                         @RequestParam(name="state", required = false, defaultValue = "") String state,
                         @RequestParam(name="city", required = false, defaultValue = "") String city,
                         @RequestParam(name="image", required = false, defaultValue = "") String image,
                         @RequestParam(name="bd", required = false, defaultValue = "") String bd) {


        Contact c = repository.findByEmail(email);
        if(c == null)
            throw new IllegalArgumentException("Cannot update a record that does not exist!");

        //TODO: Allow for changing email. Currently does not allow
        Contact newContact = new Contact(counter.incrementAndGet(), user, email, number_work, number_personal, company,
                street, state, city, image, bd);

        repository.update(newContact);
        return "Record Updated!\n";
    }

    @GetMapping(value="/get", params = {})
    @ResponseBody
    public List<Contact> getVal() {
        return repository.findAll();
    }

    @GetMapping(value = "/get", params = {"email"})
    @ResponseBody
    public Contact getVal(@RequestParam(name="email") String email) {
        Contact c = repository.findByEmail(email);
        if(c != null)
            return c;
        else{
            throw new IllegalArgumentException("That email is not registered to anyone");
        }
    }

    @GetMapping(value = "/get", params = {"work_number"})
    @ResponseBody
    public Contact getVal2(@RequestParam(name="work_number") String number) {
        //return repository.findByWorkNumber(number);

        Contact c = repository.findByWorkNumber(number);
        if(c != null)
            return c;
        else{
            throw new IllegalArgumentException("That number is not registered to anyone!");
        }

    }

    @GetMapping(value = "/getall", params = {"state"})
    @ResponseBody
    public List<Contact> getAllState(@RequestParam(name="state") String state) {
        //TODO: associate full name with state codes
        return repository.findAllState(state.toUpperCase());

    }

    @GetMapping(value = "/getall", params = {"city"})
    @ResponseBody
    public List<Contact> getAllCity(@RequestParam(name="city") String city) {
        return repository.findAllCity(city.toUpperCase());

    }

    @GetMapping(value = "/delete", params = {"email"})
    @ResponseBody
    public int deleteUser(@RequestParam(name="email") String userEmail) {
        return repository.deleteByEmail(userEmail);

    }

    //TODO: make a cooler looking error page for browsers
}