---
created: 2024-12-11 21-21-04
referenzen: 
quellen:
---
 C++ Version: 11

# Fragen 
- mehrdimensionale Arrays?

# Notizen 
- Referenzen auf Arrays sind verboten 
- Wenn ich eine Funktion in der Elternklasse haben, mit gleichem Namen aber einer Anderen Signatur, kann ich diese nicht in der Kindmethode aufrufen. Die Methode wird verdeckt 
```
class Person { ... }
class Student : public Person { ... }

Student s("Heinz", "heizer");
Person &p = s;

cout << s.toString() << endl;
cout << p.toString() << endl;

Bei p.toString() wird die Methde von Person und nicht von Student aufgerufen => Anders als in Java 


class Person {
    virtual string toString() const { ... }
};
=> Bewirkt, dass die Methode dynamisch vererbt wird und die Methode toString beim aufruf p.toString() von Student aufgerufen wird
```
Scope 
- Zu beginn in den Basisscope alle bekannten Typen und Buildtins integrieren. Genauso auch mit Schlüsselwörtern wie for ...
- Wenn Variablen Deklariert oder Definiert werden, füge ich diese zum Scope hinzu. 
- Vor dem Hinzufügen muss allerdings geprüft werden, ob diese bereits in dem aktuellen Scope existiert 

c++ Klassen und funktionsdeclaration muss vor ihrem aufruf stehen 
- In c++ läuft der Compiler ein mal durch den Code und Prüfe, ob die Variablen bekannt sind. 
- Bei Funktionen wird geprüft, ob diese deklariert sind und beim Aufruf wird geprüft, ob Parameter und Rückgabetyp passen
- Bei Klassen wird auf eine Deklaration geachtet. Sie müssen nicht definiert sein, deklariert reicht erstmal 
- Konstruktoren liefern ein Objekt zurück

Interpreter
- Zuweisungen z.b. c = 4 sind expressions und liefern einen Wert zurück 
- Alle Werte != 0 sind im Boolschen Kontext True. Nur 0 == False 
- if (c=4) => Zuweisung => liefert 4 zurück => true 

int a = 10;
{
	int a = 20 
	int b = ::a; => Es wird mit :: auf die globale Variable zugegriffen 
}

Von Classen mit Abstrakten Methoden darf ich kein Objekt erstellen 

Funktionen dürfen nicht in Funktionen definiert werden 

Was passiert bei Klassenvariablen

![[Pasted image 20241214205821.png|600]]


This muss mit eingebaut werden um dinge auflösen zu können oder um Objekte zu vergleichen 
Destruktoren "müssen" nicht auf biegen und brechen vorhanden sein

Initialisierungslisten müssen nicht implementiert werden - Wenn geerbt wird, wir von der Elternmethode automatisch der Default Konstruktor verwendet (Konstruktor ohne Parameter) - Dieser muss aber auch existieren 

### Fehler oder Prüfen
- Zum Starten der Datei muss es eine Mainfunktion geben
- Klassen
	- Abstrakte Methoden können einen Funktionkörper haben 
	- Explizite Klassen? 
	- Destruktor
	- Virtual 
	- Grammatikregel "classfunc" ID::ID
	- Grammatikregel "callfunc" ID::ID wird aktuell als normale Klassenfuntkion behandelt 
	- Prüfen beim erstellen von Klassenobjekten, ob die Klasse abstrakte Methoden hat => Es kann kein Objekt erstellt werden
- Funktionen
	- Es muss im AST gespeichert werden, ob Funktionsparameter referenzen sind oder nicht 
	- Prüfen der ob ein Return vorhanden ist ?
	- Rückgabewert von Funktionen ref bsp: int &fkt1(const int &, const char);
- CONST REF VOID im AST richtig behandelt? 
- ```int &d = b.age; => Funktioniert nicht => Einfach weglassen? => Referenz auf AST Variable / Dort kann man leichter erkennen, ob es sich um eine Klassenvariabe handelt oder nicht```
- ```int &fkt1(const int &, const char a);```
- ```int xx  [2][2] = {{1, 2}, {3, 4}};```
- Der Ausführbare Programmcode kann aktuell noch außerhalb von Funktionen stehen und ausgeführt werden 
- "decl" in if und whileconn 
- Referenz auf Klassenvariable 
- Operationen können auch außerhalb von Funktionen stehen - (Main Funktion)
- Mathematische Operationen - Mit klassen oder anderen Objekten kann gerechnet werden. 
  Ist das so richtig oder kann nur mit primitiven Datentypen gerechnet werden 
- Addeq, subeq ..., inc, dec Typen werden nicht geprüft 
- If else - Vergleiche zwischen int char ...
- Main Funktion darf keine Parameter haben
- Funktion Parameter Default werte 
- Abstrakte Funktionen - Prüfen, ob diese implementiert wurden 

### Verbesserungen 
- Kann in der Klasse AST der rtype auch über den Konstruktor gesetzt werden 
- AST - 2 Konstruktoren (Nur einer?) 


# Grammar 
- [x] Basisdatentypen: `bool`, `int`, `char`
- [x] Variablen
- [x] Arrays
- [x] C++-Referenzen
- [x] Zuweisungen und Expressions
- [x] Kontrollfluss: `if`-`then`-`else`, `while`-Schleifen
- [x] Funktionen (Definition, Deklaration, Aufrufe)
- [x] Klassen (mit Attributen und Methoden)
- [x] Einfach-Vererbung
- [x] Polymorphie (dynamisch, statisch)
- [x] Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)

# AST 
- [x] Basisdatentypen: `bool`, `int`, `char`
- [x] Variablen
- [x] Arrays
- [x] C++-Referenzen
- [x] Zuweisungen und Expressions
- [x] Kontrollfluss: `if`-`then`-`else`, `while`-Schleifen
- [x] Funktionen (Definition, Deklaration, Aufrufe)
- [x] Klassen (mit Attributen und Methoden)
- [x] Einfach-Vererbung
- [ ] Polymorphie (dynamisch, statisch)
- [x] Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)

# TESTEN

# Scope erstellen 
-  declarray - assignnewarray
- (Assign var - Klassen und Objekte )
- Wo muss der Konstruktoraufruf geprüft werden 

# AST Prüfen  
- [ ] Basisdatentypen: `bool`, `int`, `char`
- [ ] Variablen
- [ ] Arrays
- [ ] C++-Referenzen
- [ ] Zuweisungen und Expressions
- [ ] Kontrollfluss: `if`-`then`-`else`, `while`-Schleifen
- [ ] Funktionen (Definition, Deklaration, Aufrufe)
- [ ] Klassen (mit Attributen und Methoden)
- [ ] Einfach-Vererbung
- [ ] Polymorphie (dynamisch, statisch)
- [ ] Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)


Interpreter - Beim zuweisen von Variablen, genauso wie in der Symboltabelle, von unten nach oben suchen
# Interpreter 
- [x] Basisdatentypen: `bool`, `int`, `char`
- [x] Variablen
- [x] Arrays
- [ ] C++-Referenzen
- [x] Zuweisungen und Expressions
- [x] Kontrollfluss: `if`-`then`-`else`, `while`-Schleifen
- [ ] Funktionen (Definition, Deklaration, Aufrufe)
- [ ] Klassen (mit Attributen und Methoden)
- [ ] Einfach-Vererbung
- [ ] Polymorphie (dynamisch, statisch)
- [x] Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)





# Sonstiges & TODOs
Fehlerbehandungen und Null checks 
Funktionen und wiederholenden Code auslagern 
Code Kommentieren 
Was ist mit Rekursivität 
Was passiert, wenn ich in  &x = y; und dann &x = z














Keras Methoden kennen 
Basics und Beispiele aus dem Vorlesungen und Übungen 





![[Pasted image 20241218090543.png]]
Das sollte in der Semantischen Analyse ein Fehler werfen, da 