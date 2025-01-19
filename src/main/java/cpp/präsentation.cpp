// Klassen erklären -Definition und  Interpretation - Alan
// Abstrakte Methoden und die Default Konstruktoren
class A {
    public:
        int resultA;
        // => Baue ich einen Anderen Konstruktor, dann muss ich auch den Default Konstruktor bauen
        // Da sonst der Default Konstruktor nicht mehr existiert und kein Objekt erstellt werden kann

        //A(int x) { aval = x; }
        //A() { aval = 1212; }

        virtual int math(int k) = 0;

        virtual int math(int i, int j, int k){
            resultA = i+j;
            return i+j;
        };
};


// - Definition und  Interpretation von Funktionen und Rekursieven aufrufen - Manuel
// Wie werden Variablen Declariert und welche Werte werden denen zugewiesen?
class B : public A {
    public:
        int bval;
        int resultB;

        B(int x) {
            resultB = 7;
        }

        int math(int i, int t, int k){
            if (k < 5){
                t ++;
                k ++;
                i ++;
                return math(i,t,k);
            } else {
                resultB = i*t;
                return i*t;
            }
        };
};



// Erstellen von Objekten und zugreifen auf Objektattribute und Methoden
int main() {
    //A o; // => Fehler - von Objekten mit Pure Virtual Methoden kann kein Objekt erstellt werden

    B y(7);
    A &x = y;

    // Erstellen einer Referenz auf eine Klassenvariable
    int &a = y.resultB;

    print_int(y.resultB);
    print_int(a);
    y.math(1,1,1);
    print_int(a);

    // Hier sieht man, dass die Funktion von B verwendet wird - Also dynamische polymorphie
    print_int(x.math(1,1,1));

    return 0;
}