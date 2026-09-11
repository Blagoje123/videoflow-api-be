package rs.ac.singidunum.videoflow.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @ManyToOne(optional = false)
    private Project project;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) { comments.add(comment); comment.setTask(this); }
    public void setTitle(String title) { this.title = title; }
    public void setProject(Project project) { this.project = project; }
}
