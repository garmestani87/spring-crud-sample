package com.example.garm.service;

import com.example.garm.domain.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PersonService {
    PersonDto create(PersonDto dto);
    PersonDto update(PersonDto dto);

    void delete(Long id);
    Page<PersonDto> findPaging(String name);

    Page<PersonDto> findAllPage(Pageable pageable);

    List<PersonDto> findAllSorted(Sort sort);

    Slice<PersonDto> findAllSlice(Pageable pageable);


}
