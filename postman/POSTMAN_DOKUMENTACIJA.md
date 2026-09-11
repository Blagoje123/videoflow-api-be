# Objavljivanje Postman dokumentacije

1. U Postmanu izabrati **Import** i učitati `VideoFlow.postman_collection.json`.
2. Pokrenuti backend, pa redom poslati Login admin, List projects i ostale zahteve. Kolekcija već sadrži sačuvane primere odgovora.
3. U vrednostima kolekcije obrisati eventualne stvarne vrednosti `accessToken` i `refreshToken` pre deljenja.
4. Otvoriti kolekciju → **Overview** → **View complete documentation** → **Publish docs** → **Publish**.
5. Kopirati javni link i predati ga zajedno sa odvojenim GitHub linkovima za BE i FE.

Promenljiva `baseUrl` je `http://localhost:8080`; login automatski pamti tokene, a kreiranje projekta pamti `projectId`.
