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

/**
 * Интеграционные тесты для userController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build();
    }

    /**
     * Получить список пользователей по ид офиса
     * @throws Exception
     */
    @Test
    public void getListUsersByFilter() throws Exception {
        String jsonByFilter = "{\"officeId\": \"1\"}";
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].firstName", is("Ivan")))
                .andExpect(jsonPath("$.data[0].secondName", is("Ivanov")))
                .andExpect(jsonPath("$.data[0].middleName", is("Ivanovich")))
                .andExpect(jsonPath("$.data[0].position", is("Manager")));
    }

    /**
     * Получить пустой список по ид офиса
     * @throws Exception
     */
    @Test
    public void getListEmptyUsersByFilter() throws Exception {
        String jsonByFilter = "{\"officeId\": \"8\"}";
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    /**
     * Получить список пользователей по полному списку параметров
     * @throws Exception
     */
    @Test
    public void getListUsersByAllParameters() throws Exception {
        String jsonByFilter = "{\"officeId\": \"2\", \"firstName\":\"Max\",\"secondName\":\"Smirnov\"," +
                "\"middleName\":\"Alekseevich\", \"position\":\"Consultant\"}";
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(2)))
                .andExpect(jsonPath("$.data[0].firstName", is("Max")))
                .andExpect(jsonPath("$.data[0].secondName", is("Smirnov")))
                .andExpect(jsonPath("$.data[0].middleName", is("Alekseevich")))
                .andExpect(jsonPath("$.data[0].position", is("Consultant")));
    }

    /**
     * Получить ошибку при неверном формате параметров
     * @throws Exception
     */
    @Test
    public void getErrorByFilter() throws Exception {
        String jsonByFilter = "{\"officeId\": \"Abc\"}";
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    /**
     * Получить пользователя по ид
     * @throws Exception
     */
    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is("Ivan")))
                .andExpect(jsonPath("$.data.secondName", is("Ivanov")))
                .andExpect(jsonPath("$.data.middleName", is("Ivanovich")))
                .andExpect(jsonPath("$.data.position", is("Manager")))
                .andExpect(jsonPath("$.data.phone", is("12125463456")))
                .andExpect(jsonPath("$.data.citizenshipCode", is(64)))
                .andExpect(jsonPath("$.data.citizenshipName", is("USA")))
                .andExpect(jsonPath("$.data.docNumber", is("404426")))
                .andExpect(jsonPath("$.data.docDate", is("2015-09-28")))
                .andExpect(jsonPath("$.data.docName", is("Passport")))
                .andExpect(jsonPath("$.data.identified", is(true)));
    }

    /**
     * Получить сообщение об ошибке при не существующем пользователе
     * @throws Exception
     */
    @Test
    public void userNotFoundById() throws Exception {
        mockMvc.perform(get("/api/user/10"))
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
        mockMvc.perform(get("/api/user/a"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    /**
     * Удачное обновленние данных пользователя
     * @throws Exception
     */
    @Test
    public void successUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":1,\"firstName\":\"OLEG\",\"docDate\":\"2018-01-01T00:00:00.000+0000\"," +
                "\"position\":\"developer\",\"docName\":\"Military ID\",\"citizenshipCode\":77}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")));

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.firstName", is("OLEG")))
                .andExpect(jsonPath("$.data.secondName", is("Ivanov")))
                .andExpect(jsonPath("$.data.middleName", is("Ivanovich")))
                .andExpect(jsonPath("$.data.position", is("developer")))
                .andExpect(jsonPath("$.data.phone", is("12125463456")))
                .andExpect(jsonPath("$.data.citizenshipCode", is(77)))
                .andExpect(jsonPath("$.data.citizenshipName", is("Japan")))
                .andExpect(jsonPath("$.data.docNumber", is("404426")))
                .andExpect(jsonPath("$.data.docName", is("Military ID")))
                .andExpect(jsonPath("$.data.identified", is(true)))
                .andExpect(jsonPath("$.data.docDate").value("2018-01-01"));
    }

    /**
     * Неудачное обновленние данных пользователя
     * @throws Exception
     */
    @Test
    public void failedUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":1,\"firstName\":\"OLEG\",\"docDate\":\"2004/08/08\"," +
                "\"position\":\"developer\",\"docName\":\"Military ID\",\"citizenshipCode\":77}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    /**
     *
     * Удачное сохранение данных пользователя
     * @throws Exception
     */
    @Test
    public void successSave() throws Exception {
        String jsonForUpdate = "{\"officeId\":\"1\", \"firstName\":\"Emilia\",\"docDate\":\"2019-01-01\"," +
                "\"position\":\"developer\"}";
        mockMvc.perform(post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForUpdate))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")));

        mockMvc.perform(get("/api/user/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(5)))
                .andExpect(jsonPath("$.data.firstName", is("Emilia")))
                .andExpect(jsonPath("$.data.position", is("developer")))
                .andExpect(jsonPath("$.data.docDate").value("2019-01-01"));
    }

    /**
     * Неудачное сохранение данных пользователя
     * @throws Exception
     */
    @Test
    public void failedSave() throws Exception {
        String jsonForSave = "{\"firstName\":\"OLEG\"}";
        mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonForSave))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}