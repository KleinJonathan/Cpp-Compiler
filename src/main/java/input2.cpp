#include <cstdio>
#include <iostream>

//#include <iostream>
//using namespace std;

class Student;


class Student {
public:
    int name;
    Student(){};
    Student(int v){
        name = v;
    };
    Student(const int &n, int c)
    : name(n+n), credits(c)
    {}
    void abc(int a){
      return;
    }
    int credits;
    int wuppie(int c=0);
};

void func(int a, int b);
void func(int c, int d);

void func(int a, int b = 10){
    return;
}

class Dummy : public Student {
public:
    int myMethod(){};
    //Dummy( ) = default;
    // => Konflikt mit Dummy(), da hier c einen defaultwert hat und auch als default constructor verwendet werden kann
    Dummy(int c = 10) : Student(10, 10){
        Student::abc(10);
        asd = c;
    }
    Dummy(const char &n, double c)
    : nick(n), asd(c)
    {}
private:
    int asd;
    char nick;
};

void fkt(int&, char); // Funktionsdeklaration
int &fkt1(int &b, const char);

int add_5(int x) {
    x += 5;
    return x;
    return x;
}

struct input2
{

};


int fun1(int a, int);
int fun1(int a, int b = 10);
int func1 (int x){
    return 1;
};

int func12(int i) {
    return i;
};
int func12(int i);


int func(int i) {
    return i;
};
int func(int &i) {
    return i;
};


int main(){
    int kkk = 10;
    func123(kkk);

    int aaa = 10;
    aaa = func1(add_5(10+10));
    printf("%d\n", aaa);

    char bb = 'b';
    char bbb = 'bb';
    printf("%d\n", 10-true);



    int i=2, ii, iii;
    int j=9;
    int &r=i;    // Referenz: neuer Name fuer i
    r=10;        // aendert i: i==10
    r=j;         // aendert i: i==9
    int &l=r;

    int &kk = j;
    int jj = 9;

    const Dummy a;
    Dummy b(37);
    Dummy c=99;

    Dummy d[4];
    d[1] = b;


    Student s10 = Student(10, 10);
    s10.wuppie(10);

    int k[3];
    char n = 'a';
    Student s = Student(), s1 = Student();
    Student t[10];
    t[1].abc(10);
    s.abc(10);
    i = 10; j = 20; k[0] = 30; n = 'a'; s = Student(); s.name = 2;
    int myArray2[] = {1, 2, 3, 4}, myArray3[12+1] = {1, 2, 3, 4};

    if (i == 10){
    } else {
    }

    while (i < 10){
        i++;
    }


    int o=0, ip=o, erg;
    add_5(ip);
    erg = add_5(o);


    int aaaaa;
    std::cout << aaaaa << std::endl;


}

int Student::wuppie(int c){
    return c;
};

