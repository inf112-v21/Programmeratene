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
		- Lage et spillbrett med Tiled
		- Vise spillbrettet med libgdx i java
	- **Akseptansekrav**
		- Når spillet starter så dukker det opp et spillbrett
- "Som bruker ønsker jeg å se elementene på brettet for å vite posisjonen til roboten min og posisjonene til andre elementer"
	- **Arbeidsoppgaver**
		- Lage tiles
		- Lage teksturer
		- Implementere grafikken
	- **Akseptansekrav**
		- Om det finnes elementer på brettet så vises disse på det grafiske spillbrettet
- "Som bruker ønsker jeg å flytte brikker på brettet fordi dette er et interaktivt spill"
	- **Arbeidsoppgaver**
		- Implementere logikk for bevegelse
	- **Akseptansekrav**
		- Det går an å gi input til spillet slik at spillerbrikken beveger seg på spillbrettet
- "Som bruker ønsker jeg å kunne besøke flaggene på brettet for å vinne spillet"
	- **Arbeidsoppgaver**
		- Implementere visningsgrafikk for flagg
		- Implementere logikk for besøk av flagg
		- Implementere logikk for seier når alle flagg er besøkt i riktig rekkefølge
	- **Akseptansekrav**
		- Spillerbrikken kan bevege seg til flagg på kartet
		- Om spillerbrikken besøker alle flaggene i riktig rekkefølge vinner spilleren spillet
- "Som bruker ønsker jeg å se andre spillere og se dem flytte seg for å kunne spille flerspiller"
	- **Arbeidsoppgaver**
		- Implementere logikk for flerspiller
		- Implementere grafikk for flere spillere
		- Implementere "turnBase" logikk
	- **Akseptansekrav**
		- To brukere kan være aktiv på samme spillbrett og se hverandres brikker i korrekt posisjon
- "Som bruker ønsker jeg å kunnne bli tildelt kort for å kunne programmere roboten min"
	- **Arbeidsoppgaver**
		- Implementere kort
		- Lage logikk for utdeling av kort
		- Lage logikk for visning av kort til brukeren
	- **Akseptansekrav**
		- Det finnes kort i spillet som kan deles ut til spillerene
		- Hver spiller kan se kortene de har fått tildelt
- "Som bruker ønsker jeg å kunnne "spille" et kort for å bevege roboten min"
	- **Arbeidsoppgaver**
		- Implementere logikk for valgte kort og bevegelse
		- Implementere grafikk som viser at robot har blitt flyttet
	- **Akseptansekrav**
		- Når en spiller legger et kort, beveger roboten seg med henhold til egenskapene til kortet som ble lagt