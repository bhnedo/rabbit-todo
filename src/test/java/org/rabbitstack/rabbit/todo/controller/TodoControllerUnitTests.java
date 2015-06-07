package org.rabbitstack.rabbit.todo.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rabbitstack.rabbit.todo.data.Todo;
import org.rabbitstack.rabbit.todo.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Unit tests for <code>TodoController</code>.
 *
 * @author Nedim Sabic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/mvcApplicationContext.xml", "file:src/main/webapp/WEB-INF/dataApplicationContext.xml"})
public class TodoControllerUnitTests {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetAllTodos() throws Exception {
        mockMvc.perform(get("/api/v1/todos"))
                .andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is("Rabbit Todo user stories")))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTodoById() throws Exception {
        mockMvc.perform(get("/api/v1/todos/{id}", "550b48bb502ff3c963ddaaf7"))
                .andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(jsonPath("$.id", is("550b48bb502ff3c963ddaaf7")))
                .andExpect(jsonPath("$.name", is("Rabbit Todo user stories")))
                .andExpect(status().isOk());
    }

    @Test
    @Ignore
    public void testCreateTodoList() throws Exception {
        Todo todo = new Todo();
        todo.setName("Body building plan");
        mockMvc.perform(post("/api/v1/todos")
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .content(JsonConverter.serialize(todo)))
                    .andExpect(status().isNoContent());


    }

    public void testDeleteTodoList() throws Exception {

    }
}
