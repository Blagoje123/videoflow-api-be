package rs.ac.singidunum.videoflow.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.singidunum.videoflow.dto.Dtos.*;
import rs.ac.singidunum.videoflow.entities.*;
import rs.ac.singidunum.videoflow.repositories.*;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projects;
    private final UserRepository users;

    public ProjectService(ProjectRepository projects, UserRepository users) {
        this.projects = projects; this.users = users;
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> all() { return projects.findAllByOrderByIdDesc().stream().map(this::view).toList(); }

    @Transactional(readOnly = true)
    public ProjectResponse one(Long id) { return view(find(id)); }

    public ProjectResponse create(ProjectRequest body, String email) {
        User owner = users.findByEmailIgnoreCase(email).orElseThrow();
        Project p = new Project();
        copy(body, p); p.setOwner(owner); p.getMembers().add(owner);
        return view(projects.save(p));
    }

    public ProjectResponse update(Long id, ProjectRequest body) {
        Project p = find(id); copy(body, p); return view(p);
    }

    public void delete(Long id) { projects.delete(find(id)); }

    public ProjectResponse addMember(Long id, Long userId) {
        Project p = find(id);
        User user = users.findById(userId).orElseThrow(() -> missing("Korisnik"));
        p.getMembers().add(user);
        return view(p);
    }

    private Project find(Long id) {
        return projects.findById(id).orElseThrow(() -> missing("Projekat"));
    }

    private void copy(ProjectRequest b, Project p) {
        p.setName(b.name()); p.setClient(b.client()); p.setStatus(b.status()); p.setDeadline(b.deadline());
    }

    private ProjectResponse view(Project p) {
        List<String> members = p.getMembers().stream().map(User::getName).sorted().toList();
        return new ProjectResponse(p.getId(), p.getName(), p.getClient(), p.getStatus(), p.getDeadline(),
                p.getOwner().getName(), members, p.getTasks().size());
    }

    private ResponseStatusException missing(String what) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, what + " nije pronadjen");
    }
}
