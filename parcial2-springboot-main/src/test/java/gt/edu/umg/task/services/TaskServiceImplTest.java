package gt.edu.umg.task.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import gt.edu.umg.task.daos.TaskDao;
import gt.edu.umg.task.entities.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TaskServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TaskServiceImplTest {
    @MockBean
    private TaskDao taskDao;

    @Autowired
    private TaskServiceImpl taskServiceImpl;



    /**
     * Method under test: {@link TaskServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        Optional<Task> ofResult = Optional.of(task);
        when(taskDao.findById((Long) any())).thenReturn(ofResult);
        assertSame(task, taskServiceImpl.findById(123L));
        verify(taskDao).findById((Long) any());
    }

    /**
     * Method under test: {@link TaskServiceImpl#create(Task)}
     */
    @Test
    void testCreate() {
        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        when(taskDao.save((Task) any())).thenReturn(task);

        Task task1 = new Task();
        task1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task1.setDueDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task1.setId(123L);
        task1.setTittle("Tittle");
        assertSame(task, taskServiceImpl.create(task1));
        verify(taskDao).save((Task) any());
    }

    /**
     * Method under test: {@link TaskServiceImpl#modify(Long, Task)}
     */
    @Test
    void testModify() {
        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        when(taskDao.save((Task) any())).thenReturn(task);
        when(taskDao.existsById((Long) any())).thenReturn(true);

        Task task1 = new Task();
        task1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task1.setDueDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task1.setId(123L);
        task1.setTittle("Tittle");
        assertSame(task, taskServiceImpl.modify(123L, task1));
        verify(taskDao).existsById((Long) any());
        verify(taskDao).save((Task) any());
        assertEquals(123L, task1.getId().longValue());
    }

    /**
     * Method under test: {@link TaskServiceImpl#modify(Long, Task)}
     */
    @Test
    void testModify2() {
        Task task = new Task();
        task.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        task.setDueDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        task.setId(123L);
        task.setTittle("Tittle");
        when(taskDao.save((Task) any())).thenReturn(task);
        when(taskDao.existsById((Long) any())).thenReturn(false);

        Task task1 = new Task();
        task1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        task1.setDueDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        task1.setId(123L);
        task1.setTittle("Tittle");
        assertNull(taskServiceImpl.modify(123L, task1));
        verify(taskDao).existsById((Long) any());
    }

    /**
     * Method under test: {@link TaskServiceImpl#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(taskDao).deleteById((Long) any());
        when(taskDao.existsById((Long) any())).thenReturn(true);
        taskServiceImpl.delete(123L);
        verify(taskDao).existsById((Long) any());
        verify(taskDao).deleteById((Long) any());
        assertTrue(taskServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link TaskServiceImpl#delete(Long)}
     */
    @Test
    void testDelete2() {
        doNothing().when(taskDao).deleteById((Long) any());
        when(taskDao.existsById((Long) any())).thenReturn(false);
        taskServiceImpl.delete(123L);
        verify(taskDao).existsById((Long) any());
        assertTrue(taskServiceImpl.findAll().isEmpty());
    }
}

