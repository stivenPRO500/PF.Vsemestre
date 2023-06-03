package gt.edu.umg.task.services;

import gt.edu.umg.task.entities.Task;

import java.util.List;

public interface TaskService {

    public List<Task> findAll();
    public Task findById(Long id);
    public Task create(Task Task );
    void delete(Long id);
}
