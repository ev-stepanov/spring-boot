package ru.bell.practice.springboot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.bell.practice.springboot.Application;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build();
    }

    @Test
    public void getListOrganizationsByFilter() throws Exception {
        String jsonByFilter = "{\"name\": \"Microsoft\"}";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Microsoft")))
                .andExpect(jsonPath("$.data[0].active", is(true)));
    }

    @Test
    public void getListEmptyOrganizationsByFilter() throws Exception {
        String jsonByFilter = "{\"name\": \"Gazprom\"}";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void getListOrganizationsByAllParameters() throws Exception {
        String jsonByFilter = "{\"name\": \"Microsoft\", \"inn\":\"6449013711\", \"active\":true}";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Microsoft")))
                .andExpect(jsonPath("$.data[0].active", is(true)));
    }

    @Test
    public void getErrorByFilter() throws Exception {
        String jsonByFilter = "{\"name\": \"!@#$%^&&*\"}";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void getListOrganizationById() throws Exception {
        mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("Microsoft")))
                .andExpect(jsonPath("$.data.fullName", is("Microsoft Corporation")))
                .andExpect(jsonPath("$.data.inn", is("6449013711")))
                .andExpect(jsonPath("$.data.kpp", is("644901001")))
                .andExpect(jsonPath("$.data.address", is("USA")))
                .andExpect(jsonPath("$.data.phone", is("12121234567")))
                .andExpect(jsonPath("$.data.active", is(true)));
    }

    @Test
    public void notFoundById() throws Exception {
        mockMvc.perform(get("/api/organization/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void getErrorById() throws Exception {
        mockMvc.perform(get("/api/organization/a"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void successUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":1,\"name\":\"Nintendo 3\",\"fullName\":\"Nintendo Super New Version\"," +
                "\"inn\":\"1231231232\",\"kpp\":\"3334445552\",\"address\":\"groove street\",\"active\":false}";
        mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")));

        mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("Nintendo 3")))
                .andExpect(jsonPath("$.data.fullName", is("Nintendo Super New Version")))
                .andExpect(jsonPath("$.data.inn", is("1231231232")))
                .andExpect(jsonPath("$.data.kpp", is("3334445552")))
                .andExpect(jsonPath("$.data.address", is("groove street")))
                .andExpect(jsonPath("$.data.phone", is("12121234567")))
                .andExpect(jsonPath("$.data.active", is(false)));
    }

    @Test
    public void failedUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":7,\"name\":\"Nintendo 3\",\"fullName\":\"Nintendo Super New Version\"," +
                "\"inn\":\"1231231232\",\"kpp\":\"3334445552\",\"address\":\"groove street\",\"active\":false}";
        mockMvc.perform(post("/api/organization/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void successSave() throws Exception {
        String jsonForUpdate = "{\"name\":\"Nintendo 3\",\"fullName\":\"Nintendo Super New Version\"," +
                "\"inn\":\"1231231232\",\"kpp\":\"3334445552\",\"address\":\"groove street\",\"active\":true}";
        mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")));

        mockMvc.perform(get("/api/organization/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("Nintendo 3")))
                .andExpect(jsonPath("$.data.fullName", is("Nintendo Super New Version")))
                .andExpect(jsonPath("$.data.inn", is("1231231232")))
                .andExpect(jsonPath("$.data.kpp", is("3334445552")))
                .andExpect(jsonPath("$.data.address", is("groove street")))
                .andExpect(jsonPath("$.data.active", is(true)));
    }

    @Test
    public void failedSave() throws Exception {
        String jsonForUpdate = "{\"fullName\":\"Nintendo Super New Version\"," +
                "\"inn\":\"12313423234231232\",\"kpp\":\"3332342342342344445552\",\"address\":\"groove street\"}";
        mockMvc.perform(post("/api/organization/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}