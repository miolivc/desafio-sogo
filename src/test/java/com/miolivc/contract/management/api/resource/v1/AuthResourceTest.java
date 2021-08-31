package com.miolivc.contract.management.api.resource.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miolivc.contract.management.api.resource.v1.value.AuthUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class AuthResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void authenticateWorksForAllLayers() throws Exception {
        var user = new AuthUser("gestor", "gestor@123");
        var requestJson = mapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/authenticate")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void authenticateUnauthorizedError() throws Exception {
        var invalidUser = new AuthUser("gestor", "gestor");
        var requestJson = mapper.writeValueAsString(invalidUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/authenticate")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}