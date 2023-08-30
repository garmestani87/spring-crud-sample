package com.example.garm;

import com.example.garm.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void returnsPage() {
        assertThat(personRepository.findAllPage(PageRequest.of(0, 10))
                .getContent()
                .size())
                .isEqualTo(10);
    }

    @Test
    void returnsSlice() {
        assertThat(personRepository.findAllSlice(PageRequest.of(0, 10))
                .getContent()
                .size())
                .isEqualTo(10);

    }

}