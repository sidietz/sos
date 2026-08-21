package de.oberamsystems.sos.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import de.oberamsystems.sos.model.DbObjectRepository;

@WebMvcTest(DbObjectController.class)
public class DbObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DbObjectRepository repo;

    @Test
    public void testIndex() throws Exception {
        when(repo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/dbobjects"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("allDbObjects"))
               .andExpect(view().name("dbobjects"));
    }
}
