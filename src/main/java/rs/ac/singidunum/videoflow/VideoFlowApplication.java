package rs.ac.singidunum.videoflow;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.singidunum.videoflow.entities.*;
import rs.ac.singidunum.videoflow.repositories.*;
import java.time.LocalDate;

@SpringBootApplication
public class VideoFlowApplication {
    public static void main(String[] args) { SpringApplication.run(VideoFlowApplication.class, args); }

    @Bean
    CommandLineRunner demoData(UserRepository users, ProjectRepository projects, PasswordEncoder encoder) {
        return args -> {
            User admin = users.findByEmailIgnoreCase("admin@videoflow.rs")
                    .orElseGet(() -> users.save(user("Strahinja Blagojevic", "admin@videoflow.rs", "admin123", "ROLE_ADMIN", encoder)));
            User editor = users.findByEmailIgnoreCase("editor@videoflow.rs")
                    .orElseGet(() -> users.save(user("Video editor", "editor@videoflow.rs", "user123", "ROLE_USER", encoder)));
            if (projects.count() == 0) {
                Project project = new Project();
                project.setName("AI reklama");
                project.setClient("Demo klijent");
                project.setStatus("U TOKU");
                project.setDeadline(LocalDate.now().plusDays(14));
                project.setOwner(admin);
                project.getMembers().add(admin);
                project.getMembers().add(editor);
                Task task = new Task(); task.setTitle("Prva montaza");
                Comment comment = new Comment(); comment.setText("Skratiti uvod.");
                task.addComment(comment); project.addTask(task);
                Media media = new Media(); media.setFileName("reklama-v1.mp4"); project.addMedia(media);
                projects.save(project);
            }
        };
    }

    private User user(String name, String email, String password, String role, PasswordEncoder encoder) {
        User user = new User();
        user.setName(name); user.setEmail(email); user.setPassword(encoder.encode(password)); user.setRole(role);
        return user;
    }
}
