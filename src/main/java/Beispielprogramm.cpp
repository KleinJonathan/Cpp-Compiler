// -------------------------
// Deklaration einer Klasse
// -------------------------
class Person {
public:
    int age;
    char name;

    // Standardkonstruktor
    Person() : age(0), name('?') {}

    // Konstruktor mit Parametern
    Person(int a, char c) : age(a), name(c) {}
};

// Eine Funktion, die das Alter einer Person setzt.
void setAge(Person &p, int newAge) {
    p.age = newAge;
}

// Eine Funktion, die zwei Alterswerte addiert und zurückgibt.
int sumAges(const Person &p1, const Person &p2) {
    return p1.age + p2.age;
}

// Überladene Funktion add(...)
int add(int a, int b) {
    return a + b;
}

// Überladung mit drei Parametern
int add(int a, int b, int c) {
    return a + b + c;
}

// Eine Funktion, die Referenzen als Parameter nimmt und bool verwendet
int modify(int &x, bool &f) {
    if (f) {
        x += 10;    // x wird erhöht, wenn f=true
        return x;
    }
    return x - 1;   // andernfalls x - 1
}

int main() {
    // -------------------------
    // Einfache Variablen
    // -------------------------
    bool flag = true;
    int x = 5;

    // -------------------------
    // Klassenobjekte
    // -------------------------
    Person p1(20, 'A'); // age=20, name='A'
    Person p2;          // Standardkonstruktor: age=0, name='?'

    // -------------------------
    // Array von Person
    // -------------------------
    Person arr[2];
    arr[0] = p1; // Kopie
    arr[1] = p2; // Kopie

    // -------------------------
    // Verschiedene Funktionsaufrufe
    // -------------------------
    // 1) Einfacher Aufruf einer überladenen Funktion (zwei Parameter)
    int sum1 = add(x, p1.age);           // add(int,int)
    // 2) Überladener Aufruf mit drei Parametern
    int sum2 = add(p2.age, x, 100);      // add(int,int,int)

    // 3) Aufruf einer Funktion mit Referenzen
    //    x wird erhöht, da flag=true
    int result = modify(x, flag);

    // 4) Klasse-Setter
    setAge(arr[1], 99); // arr[1] = p2, p2.age=99

    // 5) Referenzvariable auf ein Klassenmember
    int &refAge = arr[0].age;
    refAge = refAge + 10; // arr[0].age wird erhöht

    // 6) Aufruf sumAges
    int combined = sumAges(arr[0], arr[1]); // p1 + p2

    // 7) bool-Referenz
    bool &refFlag = flag;
    refFlag = false; // ändert auch 'flag'

    // 8) Noch ein Aufruf von modify, nun mit f=false
    int result2 = modify(x, refFlag);

    // Programmende, kein Output
    return 0;
}
