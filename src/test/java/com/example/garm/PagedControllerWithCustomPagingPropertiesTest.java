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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.garm.PageableAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommandController.class)
@TestPropertySource(properties = {
        "spring.data.web.pageable.prefix=prefix_",
        "spring.data.web.pageable.size-parameter=my-size",
        "spring.data.web.pageable.page-parameter=my-page"
})
class PagedControllerWithCustomPagingPropertiesTest {

    @MockBean
    private PersonServiceImpl personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluatesPageableParameter() throws Exception {

        mockMvc.perform(get("/person/page")
                        .param("prefix_my-page", "5")
                        .param("prefix_my-size", "10")
                        .param("sort", "id,desc") // <-- no space after comma!!!
                        .param("sort", "name,asc")) // <-- no space after comma!!!
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(personService).findAllPage(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(5);
        assertThat(pageable).hasPageSize(10);
        assertThat(pageable).hasSort("name", Sort.Direction.ASC);
        assertThat(pageable).hasSort("id", Sort.Direction.DESC);
    }

}