package gt.edu.umg.task.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String tittle;

    private String Description;
    private Date dueDate;


}
