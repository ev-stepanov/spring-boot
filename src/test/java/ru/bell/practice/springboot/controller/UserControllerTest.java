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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    @Test
    public void getListEmptyUsersByFilter() throws Exception {
        String jsonByFilter = "{\"officeId\": \"8\"}";
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void getListUsersByAllParameters() throws Exception {
        String jsonByFilter = "{\"officeId\": \"2\", \"firstName\":\"Max\",\"secondName\":\"Smirnov\"," +
                "\"middleName\":\"Alekseevich\", \"position\":\"Consultant\"}";
        mockMvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
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

    @Test
    public void getErrorByFilter() throws Exception {
        String jsonByFilter = "{\"officeId\": \"Abc\"}";
        mockMvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonByFilter))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }


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

    @Test
    public void userNotFoundById() throws Exception {
        mockMvc.perform(get("/api/user/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void getErrorById() throws Exception {
        mockMvc.perform(get("/api/user/a"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    public void successUpdate() throws Exception {
        String jsonForUpdate = "{\"id\":1,\"firstName\":\"OLEG\",\"docDate\":\"2018-01-01\"," +
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
                .andExpect(jsonPath("$.data.identified", is(true)));


        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = inputFormat.parse("2018-01-01");
        String formattedDate = outputFormat.format(date);

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.data.docDate", is(formattedDate)));
    }
}