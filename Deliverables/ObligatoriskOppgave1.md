# INF112 Gruppe 10 - Lag 4 Programmeratene
## Deloppgave 1 - Teamet
### Medlemmer
##### Mathias Naess Iversen (mathiasni) - TEAM LEAD
- Jobber som utvikler i en startup
- Datateknologi 2. år / Hobbynerd / Jobber som utvikler
- Python / F# / ELM / Java / HTML / JS / CSS / React
##### Jakob Slyngstadli (jakobdengode) - PROJECT MANAGER
- Har erfaring med prosjektledelse fra før og ønsker ansvar over fremdrift
- Datateknologi 2. år / Hobbynerd / Twitch Streamer
- Python / Java
##### Jan Erik Syltøy (jeriks31) - TECH LEAD
- Teamets beste på Java
- Datateknologi 2. år / Hobbynerd / Jobber som Webutvikler
- Java / Python / HTML / JS / CSS / Haskell
##### Simen Sørensen (sisorensen) - PRODUCT OWNER
- Teamets mest kvalitetsbeviste og strukturerte
- Datateknologi 2. år / Hobbynerd / En av Norges beste sjakktrenere
- Python / Java / Haskell
##### Erland Myklebust (emy004) - SUBJECT MATTER EXPERT
- Har mest erfaring fra produksjon av dataspill
- Datateknologi 2. år / Hobbynerd / Fulltid Student
- Python / Java / Haskell
#### Project Board
Vi har valgt å gå for GitHub sin innebygde project tracking. Der ønsker vi å bruke Projects, Issues med SCRUM som guide. Vi har en regel som sier at det ikke er lov å pushe til master uten Review av minst to andre medlemmer.
## Deloppgave 2 - Prosess
- Møter: Onsdager 1215-1400  + Tirsdager 1215-1400.
- Kommunikasjon: Discord + Zoom. All kommunikasjon må skjer her i åpen kanal.
- Metodikk: The Scrum Guide ([PDF](http://www.scrumguides.org/index.html) eller [YouTube](https://youtu.be/G8jE3pGfGZE))
- Platform: GitHub.
- Arbeidsfordeling: Brodelig delt og oppgaver deles på Issues i GitHub.
- Oppfølging: Sprint og retrospective hver uke.
- Felles filer: Dette vil ligge på GitHub, åpent for alle medlemmer.
## Deloppgave 3 - Produkt
#### Mål:
Målet for applikasjonen er å ha en velfungerende og brukervennlig versjon av RoboRally.
#### Brukerhistorier:
Brukerhistorienene er listet i prioritert rekkefølge
- "Som bruker ønsker å se et spillbrett fordi dette er nødveldig for å kunne spille RoboRally"
	- **Arbeidsoppgaver**
	- Implementere et vindu
	- Implementere et rute kart
	- Implementere grafikk
- "Som bruker ønsker jeg brikker på brettet for å kunne se hvor elementer er på brettet"
	- **Arbeidsoppgaver**
	- Lage tiles
	- Lage teksturer
	- Implementere grafikken
- "Som bruker ønsker jeg å flytte brikker på brettet fordi dette er et interaktivt spill"
	- **Arbeidsoppgaver**
	- Implementere logikk for bevegelse
- "Som bruker ønsker å besøke flaggene på brettet fordi dette er nødvendig for å vinne spillet"
	- **Arbeidsoppgaver**
	- Implementere visningsgrafikk for flagg
	- Implementere logikk for besøk av flagg
	- Implementere logikk for "WinCon"
- "Som bruker ønsker jeg å se andre spillere og se dem flytte seg for å kunne spille flespiller"
	- **Arbeidsoppgaver**
	- Implementere logikk for flesspiller
	- Implementere grafikk for flere spillere
	- Implementere "turnBase" logikk
- "Som bruker ønsker jeg å kunnne bli tildelt kort for å kunne programmere roboten min"
	- **Arbeidsoppgaver**
	- Implementere kort
	- Lage logikk for utdeling av kort
	- Vise spillerene sine kort
	- Implementere logikk slik at spillere får totalt 5 kort
- "Som bruker ønsker jeg å kunnne bevege min robot ved hjelp av kortene mine"
	- **Arbeidsoppgaver**
	- Implementere logikk for valgte kort og bevegelse
	- Implementere grafikk som viser at robot har blitt flyttet