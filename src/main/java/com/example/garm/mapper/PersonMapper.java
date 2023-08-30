package com.example.garm.mapper;

import com.example.garm.domain.dto.PersonDto;
import com.example.garm.domain.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDto(PersonEntity entity);

    PersonEntity toEntity(PersonDto dto);

}