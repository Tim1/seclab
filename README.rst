===============================
Dokumentation (Team Challenge)
===============================
By Tim Schmiedl, Tarek Saier, Milos Babic


Struktur Challenges
====================
Allgemein
---------
Eine Challenge stellt eine in sich geschlossene Aufgabe oder Menge von Teilaufgaben dar, die es, von Seiten des Anwenders, zu lösen gilt.

Denkbar sind sowohl kleine Challenges, die innerhalb kurzer Zeit lösbar sind, als auch Challenges in Form eines Szenarios, welches sich aus mehreren, aufeinander aufbauenden Herausforderungen (im Folgenden "Stages" genannt) zusammensetzt. Eine Challenge soll immer einem Bereich der IT-Sicherheit zugeordnet sein. 

Weiterhin soll eine Challenge über eine Einführung verfügen, die vor dem eigentlichen Start der Challenge einsehbar ist. Im Falle einer kurzen Challenge ist eine einfache Erläuterung der Sachlage und des Ziels ausreichend. Liegt ein Szenario vor, so ist ein kurzer Einleitungstext sowie optional aufrufbare Tipps ("Hints") für jede Stage wünschenswert.

Das Bestehen einer Challenge oder Stage wird mittels einer Flag bestätigt. Diese findet der Anwender an den entsprechenden Stellen innerhalb der Challenge vor (in das Szenario eingearbeitet oder schlicht als Flag dargestellt), worauf er sie beim Seclab-internen Webserver abgeben kann.

Bereiche
--------
Der Fokus einer Challenge sollte immer auf einem abgrenzbaren Bereich der IT-Sicherheit liegen. Die zum Lösen der Challenge nötigen Kenntnisse und Fähigkeiten sollten folglich vor allem aus diesem Bereich kommen. Darstellung und Einteilung der Bereiche zeigen sich bei der Auswahl der Challenge auf dem Seclab-internen Webserver. Hier wählt man zuerst einen Bereich und dann eine der dazu vorhandenen Challenges.

Mögliche Bereiche können sein sind jedoch nicht begrenzt auf:

- Websicherheit (XSS, CSRF, Cookie-/Session-Stealing, etc.)
- Reverse Engineering
- Forensik
- WLAN
- Kryptographie

Einführung und Hints
--------------------
Die Einführung zu einer Challenge soll dem Anwender in jedem Fall vermitteln, was das Ziel der Challenge ist. Handelt es sich bei der Challenge um ein ganzes Szenario, so soll die Einführung dieses Szenario beschreiben, dem Anwender also die Rolle, in die er schlüpft, näherbringen. Beschrieben werden also Ausgangssituation und Ziel, über die dafür nötigen Schritte wird keinerlei Auskunft gegeben.

Um zu verhindern, dass Anwender völlig planlos vor einer Challenge stehen und nicht wissen, was sie tun sollen, können zusätzlich zur Einführung noch Hints angeboten werden. Diese sind dazu gedacht, den Anwender zu einem zielorientierten Vorgehen hinzuführen, letztgenanntes aber nicht einfach nur zu nennen oder gar zu erklären.

Flags
-----
Eine Flag ist im Grunde nichts anderes als eine Zeichenkette/ein String. Eine Flag Beginnt mit "flag\_" worauf 20 Zeichen folgen, die Groß- und Kleinbuchstaben sowie die Ziffern 0-9 sein können.

- Beispiel: flag_CPyI54ejA6Fn7NNW7aM9
- Regex: /^flag_[a-zA-Z0-9]{20}$/

Zum Generieren von Flags kann z.B. folgender PHP-Code verwendet werden:

|	<?php
|	for($flag='flag\_'; strlen($flag)<25; $c=rand(48, 122)) $flag .= ($c>57 && $c<65) || ($c>90 && $c<97) ? '' : chr($c);
|	echo $flag;
|	?>

Die Validierung der Syntax einer Flag erfolgt beim Seclab-internen Webserver nach dem Pasten einer Flag in das entsprechende Formularfeld mittels JavaScript. Die wirkliche Prüfung der Validität einer Falg und Zuordnung zu einer Challenge erfolgt nach Absenden des Formulars mittels PHP. Valide Flags und zugehörige Challenges sind in einer Datenbank hinterlegt. 

Virtuelle Maschinen
-------------------
Es ist vorgesehen, dass eine Challenge in Form einer — oder, wenn nötig, mehrerer interagierender — virtuellen Maschine (VM) vorliegt. Beginnt ein Anwender eine Challenge, wird eine solche VM gestartet und dem Anwender die nötigen Zugangsdaten übergeben. Es besteht also mindestens eine VM pro Challenge pro aktivem Anwender. Denkbare Zugangsmethoden sind SSH, VNC, x2go, etc. Die Wahl der Methode hängt von der Natur der Challenge ab.

Checkliste
----------
- In sich geschlossene Aufgabe
- Zuordnung zu einem Bereich der IT-Sicherheit
- Einführung (evtl. Hints)
- Flag(s) bei Erfolg
- Vorlegend als VM
- Zugangsmethode zur VM

Reverse-Engineering-Challenge
==============================
Die Reverse-Engineering-Challenge soll als Beispiel für künfige Challenges dienen.

Thema & Motivation
-------------------
- Einführung Reverse-Engineering
- Analysieren von Dateien
- Einführung Auslesen aus Binärdateien
- Vertiefung von Java-Kentnissen (aus anderer Sicht wie in den meisten Vorlesungen)

Szenario
--------
Einleitungstext:

	In Deinem Freundeskreis wird in den letzten Wochen ein neues Spiel immer beliebter: Wormhole eXtreme! Es gibt eine Online-Bestenliste und jeder versucht, an die Spitze zu gelangen um als bester Spieler aufgeführt zu werden.
	Dein Kumpel Richard Alpert, derzeitiger Anführer der Besteniste, hat Dir eine Kopie seiner Installation des Spiels zukommen lassen und Dich herausgefordert, seine seit Wochen ungeschlagene Highscore zu überbieten. 
	Challenge accepted!

Ziel der Challenge ist also, selbst an der Spitze von besagter Online-Bestenliste zu stehen.

Aufbau
------
Um oben erwähntes Ziel zu erreichen sind mehrere Dinge nötig, die sich zu drei Hauptaufgaben zusammenfassen lassen:

#. Das Spiel starten
#. Das Spiel manipulieren um die nötige Punktzahl zu erreichen
#. Die Punktzahl im eigenen Namen submitten 

Diese drei Hauptaufgaben stellen jeweils eine Stage dar, an deren Ende eine Flag ausgegeben wird. Stage 1 ist nötig, da das Spiel beim Start einen Lizenzschlüssen verlangt; Stage 2, da Richard Alperts Highscore durch legitimes Spielen nicht erreicht werden kann und Stage 3, da das Spiel die Highscores im "Auslieferungszustand" noch unter dem Namen "Richard Alpert" sumittet.

Technische Realisiertung
------------------------
Die Challege läuft auf einer Lubuntu-VM, die ausgehend von folgendem Image erstellt wurde: http://www.trendsigma.net/vmware/lubuntu1204.html Auf ihr befinden sich zum einen das Spiel, was mit Java ausgeführt werden kann, und zum anderen ein Webserver, auf dem das "Online"-Scoreboard gehostet wird. Das Spiel wurde <some Words from Tim> erstellt, das Scoreboard ist in PHP realisiert; Scores werden in Plaintext-Files gespeichert.

Die Kommunikation zwischen Spiel und Scoreboard funktioniert über GET-Parameter. Das Scoreboard bietet eine addscore.php, der die Parameter score und key übergeben werden muss, und validatekey.php, die einen Key auf Validität prüft. Zu Testzwecken wurde noch generatekey.php mit Parameter name erstellt, welche in der finalen Version natürlich nicht vorhanden ist.

Weiterhin wurde Folgendes an der VM getan:

- Installation von OpenJDK 7
- Änderung der Rechte, sodass Anwender nicht auf die Dateien des Scoreboards zugreifen können
- Entfernen des Standardusers aus der sudoers-File
- Einrichten eines VNC-Servers um eine Verbindung zur VM zu ermöglichen
- Automatischer Start von Webserver und VNC-Server
- Installation von Tools wie Java-Decomiler, Hexeditor, etc.
- Installation von VMware-Tools, um von Joomla aus die IP der VM zu erhalten
- Erstellen einer Readme-File in der erklärt wird, wie das Tastaturlayout geändert und das Spiel gestartet werden kann
- "Aufräumen" (leeren von Browser-History, .bash_history, etc.)

Daten/Misc
----------
- Komplettes Spiel (ohne Lizenschlussel und Flags etc.) als Download nach beenden des Szenarios
- Lizenz-Key des Spiels: 4M8K-15BM-16JN-2342
- Root-Password Lubuntu-VM: <in finaler Version aber nicht public auf github ^^>


Verlauf des Projektes
======================

#. Erarbeiten möglicher Themengebiete
#. Konzeption Challengestruktur
#. Konzeption Flagsystem
#. Umsetzung Flagsystem
#. Konzeption Beispielchallenge
#. Umsetzung Beispielchallenge
	+ Game
	+ Scoreboard
	+ VM
