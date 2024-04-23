package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    public Map<Integer, Developer> developers = new HashMap<>();
    Taxable developerTax;

    @Autowired
    public DeveloperController(Taxable developerTax) {
        this.developerTax = developerTax;
    }

    @PostConstruct
    public void loadAll() {
        this.developers.put(2,new Developer(2,"Initial Developer",5000.0, Experience.JUNIOR));
    }

    public void init() {

    }

    @GetMapping("")
    public List<Developer> getAll(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getOne(@PathVariable int id){
        return developers.get(id);
    }

    @PostMapping("")
    public Developer load(@RequestBody Developer developer){
        developers.put(developer.getId(),developer);
        return developer;
    }

    @PutMapping("/{id}")
    public Developer update(@PathVariable int id, @RequestBody Developer developer){
        developers.replace(id,developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public Developer remove(@PathVariable int id){
        Developer developer = developers.get(id);
        developers.remove(id,developer);
        return developer;
    }

}
