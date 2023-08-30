package com.example.garm.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static com.example.garm.domain.entity.AbstractPersistence.ALLOCATION_SIZE;
import static com.example.garm.domain.entity.AbstractPersistence.DEFAULT_SEQ_GEN;

@Getter
@Setter
@Entity
@Table(name = PersonEntity.TABLE_NAME, schema = "garm")
@SequenceGenerator(name = DEFAULT_SEQ_GEN, sequenceName = PersonEntity.TABLE_NAME + "_SEQ", allocationSize = ALLOCATION_SIZE)
public class PersonEntity extends AbstractPersistence<Long> {

    public static final String TABLE_NAME = "PERSON_ENTITY";

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;


}