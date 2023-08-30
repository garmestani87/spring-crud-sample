package com.example.garm.controller;

import com.example.garm.domain.dto.PersonDto;
import com.example.garm.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class CommandController {

    private final PersonServiceImpl personService;

    @PostMapping("/create")
    public PersonDto create(@RequestBody PersonDto dto) {
        return personService.create(dto);
    }

    @PutMapping("/update")
    public PersonDto update(@RequestBody PersonDto dto) {
        return personService.create(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody Long id) {
        personService.delete(id);
    }

}
