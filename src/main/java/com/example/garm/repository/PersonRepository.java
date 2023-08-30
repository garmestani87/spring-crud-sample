package com.example.garm.repository;

import com.example.garm.domain.dto.PersonDto;
import com.example.garm.domain.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    Page<PersonDto> findAllByName(String name, Pageable pageable);

    @Query("select new com.example.garm.domain.dto.PersonDto(p.id, p.username, p.email, p.name, p.version) from PersonEntity p")
    Page<PersonDto> findAllPage(Pageable pageable);

    @Query("select new com.example.garm.domain.dto.PersonDto(p.id, p.username, p.email, p.name, p.version) from PersonEntity p")
    Slice<PersonDto> findAllSlice(Pageable pageable);

    @Query("select new com.example.garm.domain.dto.PersonDto(p.id, p.username, p.email, p.name, p.version) from PersonEntity p")
    List<PersonDto> findAllSorted(Sort sort);

    @Query("select new com.example.garm.domain.dto.PersonDto(p.id, p.username, p.email, p.name, p.version) from PersonEntity p where p.username = :username")
    Slice<PersonDto> findByUsernamePaging(@Param("username") String username, Pageable pageable);

    @Query("select new com.example.garm.domain.dto.PersonDto(p.id, p.username, p.email, p.name, p.version) from PersonEntity p where p.email = :email")
    List<PersonDto> findByEmailSorted(@Param("email") String email, Sort sort);

}