package gt.edu.umg.task.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import gt.edu.umg.task.entities.Task;
import gt.edu.umg.task.services.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TaskController.class})
@ExtendWith(SpringExtension.class)
class TaskControllerTest {
    @Autowired
    private TaskController taskController;

    @MockBean
    private TaskService taskService;

    /**
     * Method under test: {@link TaskController#createTask(Task)}
     */
    @Test
    void testCreateTask() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());

        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        String content = (new ObjectMapper()).writeValueAsString(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskController#findAll()}
     */
    @Test
    void testFindAll2() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskController#findById(Long)}
     */
    @Test
    void testFindById() throws Exception {
        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        when(taskService.findById((Long) any())).thenReturn(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks/{id}", 123L);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"tittle\":\"Tittle\",\"dueDate\":0,\"description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link TaskController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(taskService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/tasks/{id}", 123L);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TaskController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(taskService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/tasks/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TaskController#modifyTask(Long, Task)}
     */
    @Test
    void testModifyTask() throws Exception {
        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        when(taskService.modify((Long) any(), (Task) any())).thenReturn(task);

        Task task1 = new Task();
        task1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task1.setDueDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task1.setId(123L);
        task1.setTittle("Tittle");
        String content = (new ObjectMapper()).writeValueAsString(task1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/tasks/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(taskController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"tittle\":\"Tittle\",\"dueDate\":0,\"description\":\"The characteristics of someone or something\"}"));
    }
}

