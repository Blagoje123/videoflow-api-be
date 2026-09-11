package rs.ac.singidunum.videoflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.videoflow.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
}
