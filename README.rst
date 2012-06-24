===============================
Dokumentation (Team Challenge)
===============================
By Tim Schmiedl, Tarek Saier, Milos Babic


Struktur Challenges
====================
Allgemein
---------
Eine Challenge stellt eine in sich geschlossene Aufgabe oder Menge von Teilaufgaben dar, die es, von Seiten des Anwenders, zu lösen gilt.

Denkbar sind sowohl kurze Challenges, die innerhalb kurzer Zeit lösbar sind, als auch Challenges in Form eines Szenarios, welches sich aus mehreren, aufeinander aufbauenden Herausforderungen (im Folgenden "Stages" genannt) zusammensetzt. Eine Challenge soll immer einem Bereich der IT-Sicherheit zugeordnet sein. 

Weiterhin soll eine Challenge über eine Einführung verfügen, die vor dem eigentlichen Start der Challenge einsehbar ist. Im Falle einer kurzen Challenge ist eine einfache Erläuterung der Sachlage und des Ziels ausreichend. Liegt ein Szenario vor, so ist ein kurzer Einleitungstext sowie optional aufrufbare Tipps für jede Stage wünschenswert.

Das Bestehen einer Challenge oder Stage wird mittels einer Flag bestätigt. Diese findet der Anwender an den entsprechenden Stellen innerhalb der Challenge vor (in das Szenario eingearbeitet oder schlicht als Flag dargestellt), worauf er sie beim Seclab-internen Webserver abgeben kann.

Bereiche
--------
Der Fokus einer Challenge sollte immer auf einem abgrenzbaren Bereich der IT-Sicherheit liegen. Die zum Lösen der Challenge nötigen Kenntnisse und Fähigkeiten sollten folglich vor allem aus diesem Bereich kommen. Darstellung und Einteilung der Bereiche zeigen sich bei der Auswahl der Challenge auf dem Seclab-internen Webserver. Hier wählt man zuerst einen Bereich und dann eine der dazu vorhandenen Challenges.

Mögliche Bereiche können sein sind jedoch nicht begrenzt auf:

- Websicherheit (XSS, CSRF, Cookie-/Session-Stealing, etc.)
- Reverse Engineering
- Forensik
- <insert moar (hatten wir da nicht mal analog was zusammengetragen?)>

Einführung
----------
<moar>

Flags
-----
Eine Flag ist im Grunde nichts anderes als eine Zeichenkette/ein String. Eine Flag Beginnt mit "flag_" worauf 20 Zeichen folgen, die Groß- und Kleinbuchstaben sowie die Ziffern 0-9 enthalten können.

- Beispiel: "flag_CPyI54ejA6Fn7NNW7aM9"
- Regex: /^flag_[a-zA-Z0-9]{20}$/

Zum generieren von Flags kann der Flaggenerator (flaggenerator.php <Angabe wo zu finden>) verwendet werden. Die Validierung der Syntax einer Flag erfolgt beim Seclab-internen Webserver nach dem Pasten einer Flag in das entsprechende Formularfeld mittels JavaScript. Die wirkliche Prüfung der Validität einer Falg und Zuordnung zu einer Challenge erfolgt nach Absenden des Formulars mittels PHP. Valide Flags und zugehörige Challenges sind in einer Datenbank hinterlegt. 

Virtuelle Maschinen
-------------------
Es ist vorgesehen, dass eine Challenge in Form einer — oder, wenn nötig, mehrerer interagierender — virtuellen Maschine (VM) vorliegt. Beginnt ein Anwender eine Challenge, wird eine solche VM gestartet und dem Anwender die nötigen Zugangsdaten übergeben. Es besteht also eine VM pro Challenge pro aktivem Anwender. Denkbare Zugangsmethoden sind SSH, VNC, x2go, etc. Die Wahl der Methode hängt von der Natur der Challenge ab.

Checkliste
----------
- Zuordnung zu einem Bereich der IT-Sicherheit
- In sich geschlossene Aufgabe
- Einführung (evtl. Hints)
- Flag(s) bei Erfolg
- Vorlegend in Form einer VM
- Zugangsmethode zur VM

Reverse-Engineering Challenge
==============================
Beispiel für künfige Challenges


Thema & Motivation
-------------------
- Einführung Reverse-Engineering
- Analysieren von Dateien
- Einführung Auslesen aus Binärdateien
- Vertiefung von Java-Kentnissen (aus anderer Sicht wie in den meisten Vorlesungen)

Beschreibung
-------------
--> Einführungstext einfügen <--

- 3 stages --> 3 flags
- in VM: Webserver (Highscore+submit) + spiel (JRE)
- Szenariobeschreibung + Hints
- komplettes Spiel (ohne Lizenschlussel und Flags etc.) als Download nach beenden des Szenarios
- Licence-Key: 4M8K-15BM-16JN-2342
- bla bla bla


Verlauf des Projektes
======================
wer hat wann was gemacht?

wohl nicht so wichtig, ka ob mans rein machen soll
