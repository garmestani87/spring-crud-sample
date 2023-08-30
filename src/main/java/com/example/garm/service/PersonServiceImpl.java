package com.example.garm.service;

import com.example.garm.domain.dto.PersonDto;
import com.example.garm.domain.entity.AbstractPersistence;
import com.example.garm.domain.entity.PersonEntity;
import com.example.garm.mapper.PersonMapper;
import com.example.garm.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Override
    public PersonDto create(PersonDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public PersonDto update(PersonDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<PersonDto> findPaging(String name) {
        Pageable pageable = PageRequest.of(1, 3, Sort.by("name").ascending());
        return repository.findAllByName(name, pageable);
    }

    @Override
    public Page<PersonDto> findAllPage(Pageable pageable) {
        return repository.findAllPage(pageable);
    }

    @Override
    public List<PersonDto> findAllSorted(Sort sort) {
        return repository.findAllSorted(sort);
    }

    @Override
    public Slice<PersonDto> findAllSlice(Pageable pageable) {
        return repository.findAllSlice(pageable);
    }

}