package com.example.garm;

import com.example.garm.controller.CommandController;
import com.example.garm.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.garm.PageableAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommandController.class)
class PersonControllerTest {

    @MockBean
    private PersonServiceImpl personService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluatesPageableParameter() throws Exception {

        mockMvc.perform(get("/person/page")
                        .param("page", "5")
                        .param("size", "10")
                        .param("sort", "id,desc") //  no space after comma
                        .param("sort", "name,asc")) // no space after comma
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService).findAllPage(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(5);
        assertThat(pageable).hasPageSize(10);
        assertThat(pageable).hasSort("name", Sort.Direction.ASC);
        assertThat(pageable).hasSort("id", Sort.Direction.DESC);
    }

    @Test
    void evaluatesQualifier() throws Exception {

        mockMvc.perform(get("/person/qualifier")
                        .param("my_page", "5")
                        .param("my_size", "10")
                        .param("my_sort", "id,desc") // <-- no space after comma!!!
                        .param("my_sort", "name,asc")) // <-- no space after comma!!!
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService).findAllPage(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(5);
        assertThat(pageable).hasPageSize(10);
        assertThat(pageable).hasSort("name", Sort.Direction.ASC);
        assertThat(pageable).hasSort("id", Sort.Direction.DESC);
    }

    @Test
    void setsUpperPageLimit() throws Exception {

        mockMvc.perform(get("/person/page")
                        .param("size", "10000"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService).findAllPage(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageSize(2000);
    }

    @Test
    void evaluatesPageableDefault() throws Exception {

        mockMvc.perform(get("/person/page"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService).findAllPage(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(0);
        assertThat(pageable).hasPageSize(20);
        assertThat(pageable).hasSort("name", Sort.Direction.DESC);
        assertThat(pageable).hasSort("id", Sort.Direction.ASC);
    }

    @Test
    void returnsSlice() throws Exception {

        mockMvc.perform(get("/person/slice")
                        .param("page", "5")
                        .param("size", "10")
                        .param("sort", "id,desc") // <-- no space after comma!!!
                        .param("sort", "name,asc")) // <-- no space after comma!!!
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService).findAllSlice(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(5);
        assertThat(pageable).hasPageSize(10);
        assertThat(pageable).hasSort("name", Sort.Direction.ASC);
        assertThat(pageable).hasSort("id", Sort.Direction.DESC);

    }

    @Test
    void evaluatesSortParameter() throws Exception {

        mockMvc.perform(get("/person/sorted")
                        .param("sort", "id,desc") // <-- no space after comma!!!
                        .param("sort", "name,asc")) // <-- no space after comma!!!
                .andExpect(status().isOk());

        ArgumentCaptor<Sort> sortCaptor = ArgumentCaptor.forClass(Sort.class);
        verify(personService).findAllSorted(sortCaptor.capture());
        Sort sort = sortCaptor.getValue();

        SortAssert.assertThat(sort).hasSort("name", Sort.Direction.ASC);
        SortAssert.assertThat(sort).hasSort("id", Sort.Direction.DESC);

    }

}