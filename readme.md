# Compiler Projektarbeit - Compilerbau 3. Semester
Dieses Projekt ist eine Semesterarbeit für das Modul Compilerbau im 3. Semester des Studiengangs Informatik an der Hochschule Bielefeld.

## Installation

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

### Testen

`./gradlew check`

### Grammatik neu übersetzen

Die ANTLR-Grammatiken werden im Ordner [`src/main/antlr`](src/main/antlr/) erwartet. 

Die generierten Dateien werden im Build-Ordner
[`build/generated-src/antlr/main/`](build/generated-src/antlr/main/) abgelegt und sind über
die Gradle-Konfiguration automatisch im Classpath verfügbar.
