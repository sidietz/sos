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

import de.oberamsystems.sos.model.MyHttpServiceRepository;

@WebMvcTest(MyHttpServiceController.class)
public class MyHttpServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MyHttpServiceRepository repo;

    @Test
    public void testServices() throws Exception {
        when(repo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/httpservices"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("allHttpServices"))
               .andExpect(view().name("httpservices"));
    }
}
