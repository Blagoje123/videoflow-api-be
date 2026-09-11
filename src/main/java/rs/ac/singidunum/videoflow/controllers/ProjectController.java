package rs.ac.singidunum.videoflow.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.videoflow.dto.Dtos.*;
import rs.ac.singidunum.videoflow.services.ProjectService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    public ProjectController(ProjectService service) { this.service = service; }

    @GetMapping public List<ProjectResponse> all() { return service.all(); }
    @GetMapping("/{id}") public ProjectResponse one(@PathVariable Long id) { return service.one(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody ProjectRequest body, Principal principal) {
        return service.create(body, principal.getName());
    }
    @PutMapping("/{id}") public ProjectResponse update(@PathVariable Long id, @Valid @RequestBody ProjectRequest body) {
        return service.update(id, body);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
    @PostMapping("/{id}/members/{userId}")
    public ProjectResponse addMember(@PathVariable Long id, @PathVariable Long userId) {
        return service.addMember(id, userId);
    }
}
