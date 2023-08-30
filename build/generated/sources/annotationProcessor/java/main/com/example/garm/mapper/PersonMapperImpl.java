package com.example.garm.mapper;

import com.example.garm.domain.dto.PersonDto;
import com.example.garm.domain.entity.PersonEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-30T15:49:15+0330",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Private Build)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonDto toDto(PersonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        String name = null;
        Integer version = null;

        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();
        name = entity.getName();
        version = entity.getVersion();

        PersonDto personDto = new PersonDto( id, username, email, name, version );

        return personDto;
    }

    @Override
    public PersonEntity toEntity(PersonDto dto) {
        if ( dto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( dto.id() );
        personEntity.setVersion( dto.version() );
        personEntity.setUsername( dto.username() );
        personEntity.setEmail( dto.email() );
        personEntity.setName( dto.name() );

        return personEntity;
    }
}
