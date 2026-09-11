package rs.ac.singidunum.videoflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.videoflow.entities.Project;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByIdDesc();
}
