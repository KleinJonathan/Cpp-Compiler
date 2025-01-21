# Rudimentärer Cpp Compiler mit Antlr und Java - Projektarbeit - Compilerbau 3. Semester
Dieses Projekt ist eine Semesterarbeit für das Modul Compilerbau im 3. Semester des Studiengangs Informatik an der Hochschule Bielefeld.

---

# Projektbeschreibung 
## Anforderungen
- Basisdatentypen: `bool`, `int`, `char`
- Variablen
- Arrays
- C++-Referenzen
- Zuweisungen und Expressions
- Kontrollfluss: `if`-`then`-`else`, `while`-Schleifen
- Funktionen (Definition, Deklaration, Aufrufe)
- Klassen (mit Attributen und Methoden) (Destuktoren nicht zwingend)
- Einfach-Vererbung
- Polymorphie (dynamisch, statisch)
- Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)

## Fehlerhafte Teile
- Arrays aus Objekten funktionieren nicht
- Custom Construktoren können zu Konflikten führen
- This wurde nicht implementiert
- Zuweisungsoperator für Klassen gehen nicht
- Destruktoren wurden im Interpreter nicht implementiert
- Default Funktionsparameter wurden nicht implementiert

## Verbesserungen
- Kann in der Klasse AST der rtype auch über den Konstruktor gesetzt werden
- AST - 2 Konstruktoren (Nur einer?)


## Sonstiges 
- In Klassendefinitionen müssen die Variablendefinitionen vor den Funktionen stehen, in welchen sie genutzt werden




---

# Installation

## Vorraussetzungen
Es wird die [Java SE Development Kit 21 LTS](https://jdk.java.net/21/) benötigt.

Weitere Software ist nicht notwendig. ANTLR und JUnit werden über das Build-Skript automatisch
als Dependency heruntergeladen und eingebunden. Das Antlr-Plugin für IntelliJ ist optional, kann aber 
sehr hilfreich sein zu installieren.
[ANTLR-Plugin für IntelliJ](https://plugins.jetbrains.com/plugin/7358-antlr-v4)


## Gradle-Tasks

### Aufräumen

`./gradlew clean`

### Starten des Programms

Konfigurieren des Programms mit [`build.gradle`](build.gradle).

Danach starten und kompilieren des Programms über `./gradlew run`.

### Grammatik neu übersetzen

Die ANTLR-Grammatiken werden im Ordner [`src/main/antlr`](src/main/antlr/) erwartet. 

Die generierten Dateien werden im Build-Ordner
[`build/generated-src/antlr/main/`](build/generated-src/antlr/main/) abgelegt und sind über
die Gradle-Konfiguration automatisch im Classpath verfügbar.
