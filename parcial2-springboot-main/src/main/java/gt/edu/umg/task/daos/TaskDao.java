package gt.edu.umg.task.daos;

import gt.edu.umg.task.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<TaskDao,Long>{
}
