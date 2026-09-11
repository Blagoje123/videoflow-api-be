# Kratka odbrana projekta

VideoFlow je REST aplikacija za video-produkciju. Frontend šalje JSON zahteve Spring Boot backendu, backend prolazi kroz controller, service i repository sloj, a JPA čuva podatke u bazi.

Model ima pet entiteta. Vlasnik projekta i njegovi zadaci, fajlovi i komentari pokazuju OneToMany veze. Članovi projekta pokazuju ManyToMany vezu između Project i User, koja pravi tabelu `project_members`.

`ProjectController` i `ProjectService` realizuju svih pet CRUD operacija. Korisnik se prijavljuje emailom i lozinkom i dobija kratkotrajni access token i dugotrajniji refresh token. Spring Security proverava access token na svakom zahtevu. USER može da pregleda projekte, dok ADMIN može da ih dodaje, menja i briše. Frontend skriva administratorske kontrole i automatski traži novi par tokena pre isteka ili posle odgovora 401.

Na odbrani pokazati: prijavu obe uloge, CRUD kao admin, zabranu izmene kao USER, automatski refresh, tabele u H2 konzoli i primere odgovora u objavljenoj Postman dokumentaciji.
