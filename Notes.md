---
created: 2024-12-11 21-21-04
referenzen: 
quellen:
---

![[Pasted image 20241214205821.png|600]]



### Fehler/Fehlend oder Prüfen
- Klassen
	- Destruktor - Weglassen? 
	- ```Student &s = Student()``` => Geht noch nicht 
- Funktionen
	- Rückgabewert von Funktionen ref bsp: int &fkt1(const int &, const char);
- ```int &fkt1(const int &, const char a);```
- THIS noch nicht implementiert 
- ARRAYS funktionieren noch nicht zufriedenstellend - Vor allem Mehrdimensionale nicht - Objektarrays
  ```Person x[] = {Person(10, 20), Person(20, 30), Person(30, 40)};``` => Wird nicht erkannt 
- Zugreifen auf Objekteigenschaften in Methoden und Klassenmethoden - ```B(B& rhs) { value = rhs.value; }```
- This muss mit eingebaut werden um dinge auflösen zu können oder um Objekte zu vergleichen 
- = Operator in Klassen 
- Custom Kopy Konstruktoren 
- Funktion Parameter Default werte 

# TODOs
- Fehlerbehandungen und Null checks 
- Funktionen und wiederholenden Code auslagern 
- System.out.println - Prüfen und unnötige löschen
- Bei allen Expressions prüfen, ob dort auch die Werte zurückgegeben werden. BSP Assignold liefert neuen Wert zurück
- Alle Kommentare und ausgaben auf Englisch umschrieben 

# Verbesserungen 
- Kann in der Klasse AST der rtype auch über den Konstruktor gesetzt werden 
- AST - 2 Konstruktoren (Nur einer?) 

# Anforderungen 
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










# Vorstellung
- Grammatik 
- AST
	- Funktionen Parametertypenprüfungen 
- Scope Erstellung und Verwaltung 
- Interpreter
	- Referenzen Umsetzung und Erklärung, vor allem auch im Interpreter 
	- Mathhelper und Konvertierung im Interpreter 

Semantische Analyse und Laufzeitanalyse 
Mathematische Funktion implementieren, anhand welcher der Typenvergleich, Parameter und auch Klassen, wenn es eine Klassenfunktion ist, gezeigt werden können - Auch überladungen können hier gezeigt werden 
Wie werden Funktionen gespeichert und wiedergefunden? 
Was passiert bei Variablen Deklaration / Welche werte werden gespeichert 

AST - prefix - ASTType - Value - rtype
==Morgen am AST und Symboltabelle vorstellen und zeigen== 


==Programm am AST und der Symboltabelle erklären 
Dort kann man recht viel sehen==
# Ablauf
- Begrüßung 
- Vorstellung Struktur 
- Vorstellung Programm - Grob erklären was passiert 
- Klassendefinition und Aufbau - Alan
	- Listener
		- 587 - enterClassdef !!!
			- Wie Funktioniert die Klassendefinition 
			- => Logische Schlussfolgerung - Scope => Classdefscope => Alles was in der Classdef passiert ist automatisch in diesem Scope und in dem AST
			- Am Ende jeder Funktionsdefinition wird ein Default Copy Construktor zur Klasse hinzugefügt. Ist bereits einer Definiert ist der neu Deklariert sowieso ohne Bedeutung und wird ignoriert 
		- 110 - enterDeclvar - Überschaubar 
	- Interpreter
		- 337 - Classdef
			- Environment Erstellung und Evaluieren des AST 
			- => Logische Schlussfolgerung - Environment => Classdefenvironment => Alles was in der Classdef passiert ist automatisch in diesem Environment 
		- 126 - Declaration
			- Wie werden Declarationen behandelt? Default Werte
- Funktionen - Manuel 
	- Listener
		- 829 - enterFuncfef !!!
			- Wie werden Funktionen definiert? 
			- Wie werden die Funktionen gespeichert um diese auch überladen zu können? 
			  `func[int, int] func[char, int]`
		- 910 - enterCallfunc
			- Wie funktionieren Funktionsaufrufe und wie werden die Aufgelöst? Auch Klassenfunktionen 
		- 1203 - Math - Add
			- Wie werden die Typen geprüft, verglichen und gespeichert? 
	- Interpreter 
		- 403 - Function_def 
		- 413 - Function_call 
			- Wie werden die Environments erstellt und verknüpft? Funktionsparameter? 
		- 601 - Add
			- 743 Mathhelper 
- Objekte und Aufrufe - Jonathan 
	- Listener
		- 175 Assign Var !!!
			- Wie werden Variablen Assigned - Vor allem Klassen 
			- Hier kann auch die Referenz erklärt werden 
		- Objekterstellung und Auflösen von Klassenattributen 
	- Interpreter
		- 326 Assignclass !!!!
			- Wie werden neue Instanzen
		- 796 - createClassObject 
			- Wie werden Objekte erstellt und gespeichert? Konstruktoraufrufe 
		- 172 - Referenzen !!!!
			- Wie werden Referenzen gehandhabt und gespeichert? Was passiert, wenn ich diese änder? 
			  Dynamische Polymorphie hier erklären 

Rückgabe von Funktionen mit Expressions 



# Einschränkungen in die Abgabe mit reinschreiben. 
- Parameter müssen in Klassen, genauso wie im restlichen Codeablauf, vor ihrer ersten Verwendung Deklariert werden. Das heißt, bevor ich auf eine Variabe im Konstruktor zum Beispiel zugreife, muss diese in der Klasse schon bekannt sein 
- Variablen werden zufällig initialisiert, da dies in c++ genauso ist (Man weis nicht was in der Variable steht)
















