

/*
// Funktionen zum Testen mit dem echten C-Compiler
#include <iostream>

void print_int(int value) {
    std::cout << value << std::endl;
}

void print_char(char value) {
    std::cout << value << std::endl;
}

void print_bool(bool value) {
    std::cout << value << std::endl;
}
*/


class Person {
public:
    int age;
    int func2() {
        return 10;
    }
};


int func(int &i){
    //print_int(i);
    i = i + 10;
    return i;
}


int main() {
    int k = 20;
    print_int(k);
    print_int(func(k));
    print_int(func(k));
    print_int(func(k));
    print_int(func(k));
    int &b = k;
    print_int(b);

}










    //std::cout << (b) << std::endl;

