---
created: 2024-12-11 21-21-04
referenzen: 
quellen:
---
# Fragen 
- try catch
- public und protate und abstrakte klassen und methoden
- Structs 
- mehrdimensionale Arrays?
- using / namespaces
- vektoren 
- typedef 
- alias

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
- Variablen können direkt beim 

c++ Klassen und funktionsdeclaration muss vor ihrem aufruf stehen 
- In c++ läuft der Compiler ein mal durch den Code und Prüfe, ob die Variablen bekannt sind. 
- Bei Funktionen wird geprüft, ob diese deklariert sind und beim Aufruf wird geprüft, ob Parameter und Rückgabetyp passen
- Bei Klassen wird auf eine Deklaration geachtet. Sie müssen nicht definiert sein, deklariert reicht erstmal 

Zuweisungen z.b. c = 4 sind expressions und liefern einen Wert zurück 
Alle Werte != 0 sind im Boolschen Kontext True. Nur 0 == False 
if (c=4) => Zuweisung => liefert 4 zurück => true 

int a = 10;
{
	int a = 20 
	int b = ::a; => Es wird mit :: auf die globale Variable zugegriffen 
}

Funktionen dürfen mehrfach deklariert aber nur einmal definiert sein
```
// .h
class Fluppie {
public:
    int wuppie(int c=0);
};

// .cpp
int Fluppie::wuppie(int c) { ... }
```

Was passiert bei Klassenvariablen

![[Pasted image 20241214205821.png|600]]


### Fehler oder Prüfen
- Klassem
	- Klasse - Initliste
	- public / abstrakt Klassen
	- Explizit
	- Destruktor
	- Abstrakte Methoden 
	- Konstruktor - Prüfen, ob dieser zu der Klasse gehört 
- Anführungszeichen " und ' nutzbar machen
- CONST REF VOID werden im AST noch nicht wirklich beachtet 
- Rückgabewert von Funktionen ref bsp: int &fkt1(const int &, const char);
- Funktionsaufrufe von Klassen a.foo();
- Beim ändern von Daten prüfen, ob diese const sind oder nicht 
- Arrays aus Objekten funktionieren aktuell nicht 
- int &i = &j
- int &d = b.age;
- Referenzen in funktionsdeklaration int func(int &)
- Es muss im AST gespeichert werden, ob Funktionsparameter referenzen sind oder nicht 

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
- [ ] Polymorphie (dynamisch, statisch)
- [x] Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)

# AST 
- [x] Basisdatentypen: `bool`, `int`, `char`
- [x] Variablen
- [x] Arrays
- [x] C++-Referenzen
- [x] Zuweisungen und Expressions
- [x] Kontrollfluss: `if`-`then`-`else`, `while`-Schleifen
- [x] Funktionen (Definition, Deklaration, Aufrufe)
- [ ] Klassen (mit Attributen und Methoden)
- [ ] Einfach-Vererbung
- [ ] Polymorphie (dynamisch, statisch)
- [x] Eingebaute Funktionen: `print_bool`, `print_int`, `print_char` (Ausgabe eines Werts des jeweiligen Typs auf der Konsole)

# TESTEN

# Scope erstellen 
-  declarray - assignnewarray
- (Assign var - Klassen und Objekte )
- Assign Array Element
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

# Interpreter 
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





# Sonstiges & TODOs
Fehlerbehandungen und Null checks 
Funktionen und wiederholenden Code auslagern 
Code Kommentieren 
Was ist mit Rekursivität 
