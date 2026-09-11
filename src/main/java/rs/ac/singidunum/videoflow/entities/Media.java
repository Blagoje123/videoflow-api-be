package rs.ac.singidunum.videoflow.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "media_files")
public class Media {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fileName;
    @ManyToOne(optional = false)
    private Project project;

    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setProject(Project project) { this.project = project; }
}
