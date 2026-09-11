# VideoFlow backend

Završni projekat — Strahinja Blagojević, 2023205026. REST aplikacija za vođenje video-produkcije.

## Pokretanje

Potreban je JDK 17. Otvoriti ovaj folder u IntelliJ IDEA i pokrenuti `VideoFlowApplication`, ili izvršiti:

```bash
./mvnw spring-boot:run
```

Podrazumevano radi ugrađena H2 baza. H2 konzola je na `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:file:./data/videoflow`, korisnik `sa`, bez lozinke). Za MySQL: `./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql`.

| Uloga | Email | Lozinka |
|---|---|---|
| ADMIN | admin@videoflow.rs | admin123 |
| USER | editor@videoflow.rs | user123 |

## Šta je realizovano

- 5 entiteta: User, Project, Task, Media i Comment; spojna tabela `project_members` je šesta tabela.
- OneToMany: Project–Task, Project–Media i Task–Comment.
- ManyToMany: Project–User.
- Potpun CRUD za Project: `GET/POST /api/projects`, `GET/PUT/DELETE /api/projects/{id}`.
- JWT prijava i refresh: `POST /api/auth/login` i `POST /api/auth/refresh`.
- `ROLE_USER` može da čita; `ROLE_ADMIN` može i da menja podatke.
- Slojevi: controller → service → repository → baza.

MySQL skripta je `videoflow_db.sql`, a Postman kolekcija i kratko uputstvo su u folderu `postman`.
