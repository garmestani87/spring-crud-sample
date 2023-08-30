package com.example.garm.controller;

import com.example.garm.domain.dto.PersonDto;
import com.example.garm.service.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class QueryController {

    private final PersonServiceImpl personService;

    @GetMapping("/ping")
    public String ping() {
        return "Pong !";
    }

    @GetMapping("/find-paging")
    public Page<PersonDto> findPaging(@RequestParam String name) {
        return personService.findPaging(name);
    }

    @GetMapping(path = "/page")
    Page<PersonDto> loadCharactersPage(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return personService.findAllPage(pageable);
    }

    @GetMapping(path = "/qualifier")
    Page<PersonDto> loadPageWithQualifier(
            @Qualifier("my") Pageable pageable) {
        return personService.findAllPage(pageable);
    }

    @GetMapping(path = "/sorted")
    List<PersonDto> loadSorted(Sort sort) {
        return personService.findAllSorted(sort);
    }

    @GetMapping(path = "/slice")
    Slice<PersonDto> loadSlice(Pageable pageable) {
        return personService.findAllSlice(pageable);
    }

}
