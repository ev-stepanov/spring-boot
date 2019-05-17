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
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Интеграционные тесты для OfficeController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build();
    }

    /**
     * Получить список офисов по ид организации
     * @throws Exception
     */
    @Test
    public void getListOfficesByFilter() throws Exception {
        String jsonByFilter = "{\"orgId\": \"1\"}";
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Microsoft Washington")))
                .andExpect(jsonPath("$.data[0].active", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(2)))
                .andExpect(jsonPath("$.data[1].name", is("Microsoft New York")))
                .andExpect(jsonPath("$.data[1].active", is(true)));
    }

    /**
     * Получить пустой список по ид организации
     * @throws Exception
     */
    @Test
    public void getListEmptyOfficesByFilter() throws Exception {
        String jsonByFilter = "{\"orgId\": \"8\"}";
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    /**
     * Получить список офисов по полному списку параметров
     * @throws Exception
     */
    @Test
    public void getListOfficesByAllParameters() throws Exception {
        String jsonByFilter = "{\"name\": \"Microsoft New York\", \"orgId\":\"1\", \"active\":true}";
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].name", is("Microsoft New York")))
                .andExpect(jsonPath("$.data[0].active", is(true)));
    }

    /**
     * Получить ошибку при неверном формате параметров
     * @throws Exception
     */
    @Test
    public void getErrorByFilter() throws Exception {
        String jsonByFilter = "{\"orgId\": \"Abc\"}";
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    /**
     * Получить офис по ид
     * @throws Exception
     */
    @Test
    public void getOfficeById() throws Exception {
        mockMvc.perform(get("/api/office/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Microsoft Washington")))
                .andExpect(jsonPath("$.data.address", is("Washington 12")))
                .andExpect(jsonPath("$.data.phone", is("12126458365")))
                .andExpect(jsonPath("$.data.active", is(true)));
    }

    /**
     * Получить сообщение об ошибке при не существующем офисе
     * @throws Exception
     */
    @Test
    public void officeNotFoundById() throws Exception {
        mockMvc.perform(get("/api/office/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    /**
     * Получить ошибку 404 при неверно-заданном url
     * @throws Exception
     */
    @Test
    public void getErrorById() throws Exception {
        mockMvc.perform(get("/api/office/a"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    /**
     * Удачное обновленние данных офиса
     * @throws Exception
     */
    @Test
    public void successUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":1,\"name\":\"NEW OFFICE\",\"address\":\"groove street\",\"phone\":\"88005553535\",\"active\":false}";
        mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")));

        mockMvc.perform(get("/api/office/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("NEW OFFICE")))
                .andExpect(jsonPath("$.data.address", is("groove street")))
                .andExpect(jsonPath("$.data.phone", is("88005553535")))
                .andExpect(jsonPath("$.data.active", is(false)));
    }

    /**
     * Неудачное обновленние данных офиса
     * @throws Exception
     */
    @Test
    public void failedUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":10,\"name\":\"NEW OFFICE\",\"address\":\"groove street\",\"phone\":\"88005553535\",\"active\":false}";
        mockMvc.perform(post("/api/office/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    /**
     * Удачное сохранение данных офиса
     * @throws Exception
     */
    @Test
    public void successSave() throws Exception {
        String jsonForUpdate = "{\"orgId\":1,\"name\":\"NEW OFFICE\",\"address\":\"groove street\",\"phone\":\"+79085425081\",\"isActive\":true}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")));

        mockMvc.perform(get("/api/office/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(5)))
                .andExpect(jsonPath("$.data.name", is("NEW OFFICE")))
                .andExpect(jsonPath("$.data.address", is("groove street")))
                .andExpect(jsonPath("$.data.phone", is("+79085425081")))
                .andExpect(jsonPath("$.data.active", is(true)));
    }

    /**
     * Неудачное сохранение данных офиса
     * @throws Exception
     */
    @Test
    public void failedSave() throws Exception {
        String jsonForUpdate = "{\"orgId\":10,\"name\":\"@#$%\",\"phone\":\"911\"}";
        mockMvc.perform(post("/api/office/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}