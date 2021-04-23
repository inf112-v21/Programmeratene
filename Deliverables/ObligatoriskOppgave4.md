# INF112 Gruppe 10 - Lag 4 Programmeratene
# Deloppgave 1: Prosjekt og prosjektstruktur

Møtereferatene ligger i en egen folder -> Documents/Meetings
Vi har valgt å beholde de samme rollene som de vi valgte i starten av prosjektet. I starten av prosjektet brukte vi god tid på å sette oss inn i forskjellige roller som kan være i et team. Fra dette valgte vi roller som vi mener passet best for prosjektet vi hadde foran oss. Etter rollene var bestemt, snakket vi om hvilke erfaring hvert medlem hadde, og brukte denne informasjonen til å sette roller i teamet. Dette har ført til at hvert medlem har fått mer eierskap til sin rolle, og hvert medlem har jobbet hardt for å gjennomføre sine arbeidsoppgaver i teamet. På bakgrunn av dette har vi ikke sett noen grunn til å endre på rollene.

Team Leader (Mathias Iversen): Lagets leder. Det er han som holder orden på møter, passer på at alle blir hørt og gjør at samarbeid mellom medlemmer går som det skal.
Tech Lead (Jan Erik Syltøy): Teknisk support for hele laget. Han står for kodens oppsett og struktur. Han har god kunnskap om hvordan hele prosjektet er bundet sammen, og har hjulpet alle medlemmene når de sitter fast i sin egen kode.
Project Manager(Jakob Slyngstadli): Har hatt ansvar for sentrale oppgaver i prosjektet. Har hjulpet teamet med å prioritere arbeidsoppgaver basert på hva som er viktig for release.
Product Owner(Simen Sørensen): Den som holder kontroll på produktet. Han har passet på at spillet kjører som det skal, og at ingen store bugs ødelegger kundens opplevelse.
Subject Matter Expert(Erland Myklebust): Eksperten innenfor spillets regler. Han har satt seg inn i hvordan spillet fungerer, og har passet på at det digitale spillet er opp til standarden til det originale.

### Retrospektiv og Erfaringer for Programmeratene og RoboRally:

Programmeratene har kommet veldig langt som et team siden starten av prosjektet. Vi har lært mye angående det å jobbe i team, delegere ansvar, samt å vurdere størrelse på arbeidsoppgaver. I starten av prosjektet falt dessverre mye av programmeringen på teamets tech lead, Jan Erik. Grunnet til dette er fordi han hadde best kunnskap innenfor java. Videre i prosjektet lærte vi oss å dele opp arbeidsoppgaver i mer gjennomførbare biter, noe som medførte til at det var lettere å jobbe sammen og samkjøre koden som ble laget. Vi ble også  mye bedre på parprogrammering, der én sitter med koden og streamer dette via discord til andre medlemmer som kommer med innskudd og har skapt mye god diskusjon og løsningsmetoder.

Teamet har alltid hatt en veldig god tone. Fra dag 1 har alle medlemmene vært komfortable med å si sin mening, og alle har blitt hørt når de har hatt noe å si. Vi har jobbet hardt for å passe på at ikke kom noen twister oss i mellom, selv om arbeidsoppgavene kunne vært litt skjevfordelt til tider. Det har vært veldig viktig for oss å opprettholde dette gjennom hele prosjektet, da vi mener dette har gjort oss mer produktive og gitt oss med lyst til å jobbe sammen.

Vi har valgt å gjøre hele prosjektet bottom-up. Det vil si at vi har fokusert på at hele spillet skal fungere backend før vi startet å implementere nye ting. Vi har alltid ønsket å kunne levere et produkt som føles fullstendig, og ikke et produkt der det er mange kjente bugs. Det startet med at vi jobbet veldig hardt for å få opp multiplayer. Vi hadde stort fokus på det til 1. og 2. oblig, noe som vi fikk til. Etter det har vi fokusert på at vi skulle gjennomføre MVP til oblig 3. Dette innebar at vi skulle vise robotene på alle sin skjerm, win-condition og loss-condition. Vi klarte å gjennomføre dette, selv om ikke alle løsninger ble helt optimale. For siste sprinten av prosjektet har vi valgt å fokusere på å gjennomføre et produkt som fungerer 100% i stedet for å fokusere på mange extra “features” som ikke nødvendigvis gjør kundens opplevelse av produktet bedre. Vår tanke er at du ikke starter å pynte kaken før den er ferdig bakt.

Hvis vi hadde startet prosjektet på nytt, hadde det selvfølgelig vært ting vi hadde gjort annerledes.
Vi hadde vært flinkere på å dele inn arbeidsoppgaver i mindre oppgaver
Ha mer fysiske møter (Hvis covid ikke var her.)

Skjermbilde av project board kan finnes under Programmeratene/Deliverables/ProjectBoardFinal.png

# Deloppgave 2: Krav
Siden vi ble ferdig med de forrige kravene våre, som var å gjøre ferdig MVP for Oblig 3, så har vi valgt å prioritere elementer som gjør at spillet vårt føles mer ut som et fullstendig produkt. Det vi har fokusert på for den siste sprinten vår er:
Minimere antall kjente bugs
Fullføre forskjellige features, derav: Collisions, Vegger, Flere flagg samt interaktive kort for spilleren å spille gjennom.

Fra de nye kravene vi har valgt å jobbe med har vi kommet med følgende brukerhistorier:

- "Som bruker ønsker jeg å bli stoppet av vegger for å ha hindringer som skal gjøre spillet mer utfordrende"
    - **Arbeidsoppgaver**
    - Implementere logikk for å stoppe roboten å gå gjennom vegger.
    - **Akseptansekrav**
    - Når en spiller legger et kort som vil beveger roboten gjennom en vegg, blir den stoppet.

- "Som bruker ønsker jeg å besøke flere flagg for å gi spillet mer kompleksitet og øke gjennomsnittlig spilletid"
    - **Arbeidsoppgaver**
    - Implementere logikk for å lagre hvilke flagg spilleren har besøkt.
    - Implementere logikk for å stoppe spillet når en spiller har besøkt alle flaggene i rekkefølge.
    -  **Akseptansekrav**
    - Når en spiller har besøkt alle flaggene i rekkefølge blir spillet avsluttet og den spilleren har vunnet.

- "Som bruker ønsker jeg å ha måter å tape spillet på for å gi spilleren en følelse av spenning"
    - **Arbeidsoppgaver**
    - Implementere logikk for at spilleren mister liv.
    - Implementere logikk for at spilleren taper spillet hvis man har mistet alle livene sine.
    - **Akseptansekrav**
    - Når spilleren har fått tildelt alle damagetokens blir spillerens robot ødelagt, spilleren mister et liv og blir plassert tilbake på sin spawntile.
    - Når spilleren har mistet alle sine liv, er spilleren ute og kan ikke lenger velge kort.

- "Som bruker ønsker jeg å ha farlige hindringer på brettet for å ha utfordringer for å komme til flagget.
    - **Arbeidsoppgaver**
    - Implementere logikk for ulike hinder som kan skade eller ødelegge spillerens robot.
    -  **Akseptansekrav**
    - Når spillerens robot går i et farlig hinder, blir roboten ødelagt.
    - Når spillerens robot blir truffet av et farlig hinder, tar roboten skade.

- "Som bruker ønsker jeg å bli plassert på ulike steder for å ha ulike utgangspunkt for å komme seg til flaggene.
    -  **Arbeidsoppgaver**
    - Plassere spawntiles på kartet.
    - Implementere logikk slik at spillerne blir plassert ulikt når spillet starter, og når en spillers robot blir ødelagt
    -  **Akseptansekrav**
    - Når spillet starter blir spillerne plassert på ulike spawntiles
    - Når spilleren mister et liv blir spillerens robot plassert tilbake på sin spawntile.

- " Som bruker ønsker jeg å dytte andre spilleres roboter for gi spillet et snev av kaos slik at selv de best programmerte robotene ikke nødvendigvis ender opp hvor de skal"
    - Arbeidsoppgaver
    - Implementere logikk for dytte andre roboter
    -  Akseptansekrav
    - Når en robot er i veien til spillerens robot,  blir den dyttet gitt at man ikke dytter den andre roboten gjennom en vegg.

Vi har valgt å bruke GitHub sitt project board for å holde styr på arbeidsoppgaver og kartlegge hvordan vi ligger ann i forhold til prosjektet. Dette har fungert veldig fint, og gjort det veldig enkelt og oversiktlig for oss å legge riktige prioriteringer for å sikre at sluttproduktet samsvarer med det vi visualiserte oss i starten av prosjektet.

Kjente bugs:
- Join menyen tar oss til terminalen, og ikke et dedikert input. (Gdx.input.getTextInput() fungerer ikke som den skal)

# Deloppgave 3: Produktleveranse og kodekvalitet

Klassediagram kan finnes under Documents/Class Diagram
Møtereferat kan finnes under Documents/Meetings
Manuelle tester kan finnes under Documents/Manuelle Tester

Hvordan kjøre Robo Rally, samt hvordan man gjør de manuelle testene kan finnes i README.md

Prosjektet har blitt kjørt og testet i OSX, Windows og Linux. Og skal fungere i alle sammen.

I denne obligen har vi gjort ferdig:
Fjernet kjente bugs (Spillet krasjer ved død spiller/Gå ut av banen etc)
Satt logikk for vegger
Logikk for kollisjoner
Fikset movement slik at man beveger seg et steg av gangen og ikke teleporterer.
Satt opp logikk for besøk av flere flagg

Robo Rally av Programmeratene features:
- Spillere kan programmere roboten sin med kort valgt gjennom terminalen
- Spillet har klikkbar meny for å starte eller bli med i et spill
- Spillet er multiplayer, og kjører bra over nett
- Spillere kan kollidere inn i hverandre og flytte andre spillere
- Robotene blir stoppet av vegger, og blir dermed på banen.
- Robotene besøker flere flagg for å vinne, disse må gå i riktig rekkefølge.
- Hvis man faller oppi et hull mister man 1 av 3 liv, og blir sendt tilbake til spawn.
- Mister man alle livene sine, dør man. Spillet fortsetter til noen har vunnet eller alle har mistet 3 hp.
- Hver robot har hvert sitt spawn point
