package rs.ac.singidunum.videoflow.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public final class Dtos {
    private Dtos() {}

    public record Login(@NotBlank @Email String email, @NotBlank String password) {}
    public record Refresh(@NotBlank String refreshToken) {}
    public record Tokens(String accessToken, String refreshToken, String name, String role) {}
    public record ProjectRequest(
            @NotBlank String name,
            @NotBlank String client,
            @NotBlank String status,
            @NotNull LocalDate deadline) {}
    public record ProjectResponse(
            Long id, String name, String client, String status, LocalDate deadline,
            String owner, List<String> members, int taskCount) {}
}
