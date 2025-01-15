

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

/*
class Person {
public:
    int age;
    int func2() {
        return 10;
    }
};
*/



int func(int &i){
    //print_int(i);
    i = i + 10;
    return i;
}




// McCarthy 91 function (see https://en.wikipedia.org/wiki/McCarthy_91_function)
int mc91(int n) {
    if (n > 100) {
        return n - 10;
    } else {
        return mc91(mc91(n + 11));
    }
}
int test(int i){
    int i = 1000;
    i = i + 10;
    return i;
}

int main() {
    int k = 20;
    print_int(mc91(0));     // 91
    print_int(mc91(1));     // 91
    print_int(mc91(11));    // 91
    print_int(mc91(42));    // 91
    print_int(mc91(100));   // 91
    print_int(mc91(101));   // 91
    print_int(mc91(200));   // 190
    print_int(mc91(300));   // 290
}









    //std::cout << (b) << std::endl;

