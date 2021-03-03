# INF112 Gruppe 10 - Lag 4 Programmeratene
## Deloppgave 1: Prosjekt og prosjektstruktur
- Rollene fungerer til nå slik som vi ønsker. Alle er tilfreds med sine roller og oppgaver.
- Vi se ingen grunn til å legge til eller å fjerne roller.
- Vi har sett at å delegere oppgaver tidligere er viktig. Da er det lettere for teamet å starte med eget arbeid. Dette ser vi på som "bedre planlegging".
- Gruppen har god dynamikk og alle bidrar med sitt.
- Per nå bruker vi Discord og dette vungerer veldig bra. Her bruker vi både voice og tekst.
- Ref Retrospective lengre nede.
- Vi jobber mye sammen. Vi deler skjerm, og alle andre ser på / kommenterer og hjelper til. Derfor vil commitsene vise litt skjeivfordeling. Vi prøver å dele mest mulig likt slik at alle får bidra.
- Referat ligger i egen folder */Meetings
- Tre punkter vi må jobbe med fra Retrospective (ref lengre nede i dokumentet)
  -- Tidligere utlevering av oppgaver til alle teamets medlemmer.
  -- Virdere arbeidsmengde bedre. Ett medlem fikk ekstra arbeid da issuet var stort.
  -- Flinkere på å fordele commints slik at alle får prøvd seg på Git. Dette selv om vi jobber mye sammen.

## Deloppgave 2 - Krav
#### Info:
Vi har valgt å beholde de samme brukerhistoriene fra oblig1 da de representerer alle de ni MVP kravene og det er de vi har jobbet ut i fra.
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

#### Arbeidsmetodikk
Når vi jobber med en oppgave tildeledes et medlem et issue på GitHub. Medlemmet kan dele opp issuet i flere biter om de føler det er bedre for oversikten. Dette er typisk Scrum mentalitet.

Kravet vi stiller teamets medlemmer er at brukerhistorien må kunne dekkes for at vi godtar at issuet lukkes. Vi holder oversikt over teamets oppgaver i Kanban-fanen i Projects på GitHub. Her kan vi se hva medlemmene driver med til en hver tid.

Vi har prioritert oppgaver basert på hva vi tror er nødveldig for videre utvikling. Først valgte vi å implementere kobling mellom server og klient, og etter dette gikk vi videre med kort og utdeling av objektene spillerene trenger.

Vi har valgt å ikke endre noe i våre krav da vi ønsket å fylle hele MVPen som er utgitt. Måler var fra start å klare alle boksene før Oblig2 måtte leveres inn.

## Commits/git
Commitsene er noe skjevt fordelt i første innlevering. Dette kommer av at vi har diskutert og
arbeidet mye sammen, mens en person skriver det vi kommer med. Vi har brukt en god del skjermdeling,
samt også reviewet hverandres arbeid etterpå.

## Retrospective for Oblig 2
- Vi er tilfreds med at alle medlemmer har stilt til samtlige timer. Totalt fikk vi inn et minimum fire timer i uken med møter. Viser til referatene våre.
- Vi har effektive møter, der alle får bidratt og alle kommer med gode innspill og meninger
- Kravene for obligen er dekket og vi har nådd målene vi satt oss. Dette gjelder også hele listen med MVP.
- Teamet har jobbet godt sammen, men vi ser at det er nødveldig med bedre planlegging fra start av obligen. Det nevnes at vi skal dele ut oppgaver tidligere.
- Denne gangen var det ekstra mye arbeid for Jan Erik siden han fikk oppgaven med å lage koblingen mellom Server og Klient. Denne oppgavene skulle vi delt på slik at det ble mindre arbeid på han.