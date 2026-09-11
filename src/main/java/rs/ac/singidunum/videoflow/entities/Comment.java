package rs.ac.singidunum.videoflow.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String text;
    @ManyToOne(optional = false)
    private Task task;

    public void setText(String text) { this.text = text; }
    public void setTask(Task task) { this.task = task; }
}
