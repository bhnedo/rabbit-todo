package org.rabbitstack.rabbit.todo.data.repository;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.rabbitstack.rabbit.todo.data.Task;
import org.rabbitstack.rabbit.todo.data.TaskPriority;
import org.rabbitstack.rabbit.todo.data.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Unit tests for <code>TodoRepository</code>.
 *
 * @author Nedim Sabic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dataApplicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoRepositoryUnitTests {

    @Autowired
    private TodoRepository todoRepository;


    @Before
    public void setUp() {

    }

    @Test
    public void testCreateEmptyTodoList() {
        Todo todo = new Todo();
        todo.setId("550bf35e17af42c9626f5cae");
        todo.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        todo.setName("Rabbit Todo user stories");
        todo.setDescription("Captures the user requirements for the implementation of Rabbit Todo application");

        todoRepository.insert(todo);

        Todo t = todoRepository.findOne(todo.getId());

        assertNotNull(t);
        assertThat(t.getName(), is("Rabbit Todo user stories"));
    }

    @Test
    public void testUpdateTodoList() {
        Todo todo = new Todo();
        todo.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        todo.setName("Rabbit Todo user stories");

        Todo t = todoRepository.insert(todo);

        assertNotNull(t);

        t.setName("Rabbit Todo user stories!");
        Todo updatedTodo = todoRepository.save(todo);

        assertNotNull(updatedTodo);
        assertThat(updatedTodo.getName(), is("Rabbit Todo user stories!"));

    }

    @Test
    public void testDeleteTodoList() {


        Todo todo = new Todo();
        todo.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        todo.setName("Rabbit Todo user stories");

        Todo t = todoRepository.insert(todo);

        todoRepository.delete(t);

        Todo deletedTodo = todoRepository.findOne(t.getId());
        assertNull(deletedTodo);
    }

    @Test
    public void testFindAllTodoLists() {
        List<Todo> todos = todoRepository.findAll();
        assertThat(todos.size(), greaterThan(0));
        assertThat(todos, hasItems(hasProperty("description",
                is("Captures the user requirements for the implementation of Rabbit Todo application"))));

    }

    @Test
    public void test1CreateTodoWithTasks() {
        Todo todo = new Todo();
        todo.setId("550bf35e17af42c9626f6cae");
        todo.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        todo.setName("Rabbit Todo user stories");
        todo.setDescription("Captures the user requirements for the implementation of Rabbit Todo application");

        Task task1 = new Task();
        task1.setId("38ae2055-fe5f-4953-9400-eb5665bc8d2c");
        task1.setTitle("Build MongoDB backend store");
        task1.setOrder(1);

        Task task2 = new Task();
        task2.setId(UUID.randomUUID().toString());
        task2.setTitle("Integrate Angular and Spring MVC");
        task2.setOrder(2);

        Task task3 = new Task();
        task3.setId("38ae2055-fe5f-4953-9400-eb5665bc8d25");
        task3.setTitle("Implement realtime user experience");
        task3.setOrder(3);

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        todo.setTasks(tasks);

        todoRepository.insert(todo);

        Todo createdTodo = todoRepository.findOne(todo.getId());
        assertNotNull(createdTodo);
        assertThat(createdTodo.getTasks().size(), is(3));

    }

    @Test
    public void testAddNewTaskToList() {
        Todo todo = todoRepository.findOne("550bf35e17af42c9626f6cae");

        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTitle("Build up Bourbon based user interface");
        task.setOrder(3);

        assertThat(todo.getTasks().size(), is(3));

        todo.getTasks().add(task);
        todoRepository.save(todo);

        assertThat(todo.getTasks().size(), is(4));

    }

    @Test
    public void testDeleteTaskFromList() {

        Todo todo = todoRepository.findOne("550bf35e17af42c9626f6cae");
        assertNotNull(todo);
        assertThat(todo.getTasks().size(), is(4));

        todo.getTasks().removeIf(t -> t.getId().equals("38ae2055-fe5f-4953-9400-eb5665bc8d25"));

        Todo modifiedTodo = todoRepository.save(todo);
        assertThat(modifiedTodo.getTasks().size(), is(3));
    }

    @Test
    public void testMarkTaskAsDone() {

        Todo todo = todoRepository.findOne("550bf35e17af42c9626f6cae");
        assertNotNull(todo);

        todo.getTasks().stream()
                        .filter(t -> t.getId().equals("38ae2055-fe5f-4953-9400-eb5665bc8d2c"))
                        .findFirst()
                        .get().setDone(true);

        Todo modifiedTodo = todoRepository.save(todo);
        assertThat(modifiedTodo.getTasks(), hasItems(hasProperty("done", is(true))));


    }

    @Test
    public void testChangeTaskPriority() {

        Todo todo = todoRepository.findOne("550bf35e17af42c9626f6cae");
        assertNotNull(todo);

        todo.getTasks().stream()
                        .filter(t -> t.getId().equals("38ae2055-fe5f-4953-9400-eb5665bc8d2c"))
                        .findFirst()
                        .get().setPriority(TaskPriority.HIGH);

        Todo modifiedTodo = todoRepository.save(todo);
        assertThat(modifiedTodo.getTasks(), hasItems(hasProperty("priority", is(TaskPriority.HIGH))));
    }

    @Test
    public void testUpdateTaskTitle() {

        Todo todo = todoRepository.findOne("550bf35e17af42c9626f6cae");
        assertNotNull(todo);

        todo.getTasks().stream()
                .filter(t -> t.getId().equals("38ae2055-fe5f-4953-9400-eb5665bc8d2c"))
                .findFirst()
                .get().setTitle("Integrate Angular and Spring MVC and SockJS");

        Todo modifiedTodo = todoRepository.save(todo);
        assertThat(modifiedTodo.getTasks(), hasItems(
                            allOf(
                                    hasProperty("id", is("38ae2055-fe5f-4953-9400-eb5665bc8d2c")),
                                    hasProperty("title", is("Integrate Angular and Spring MVC and SockJS"))
                            )));

    }

}
