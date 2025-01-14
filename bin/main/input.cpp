

/*
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
    int age;
    int func2() {
        return 10;
    }
};

int func();


int main();



int a;


int func2(int i){
    int b = 10;
    a += b;
    print_int(10);
    return a;
}


int main() {

    int a = 10;
    {
        a = 20;
    }
    print_int(a);

    int b;
    b = 50;

    print_int(b);

    char c;
    c = 'a';
    print_char(c);

    int x = 100;




    int aaa[10+10];
    print_int(aaa[1]);
    aaa[2] = 10;
    print_int(aaa[2]);
    aaa[3+1] = x*100;
    print_int(aaa[x-96]);
    print_int(aaa[4]);

    char bbb[10];
    print_char(bbb[1]);

    int ccc[10] = {9,10+10};
    print_int(ccc[0]);
    print_int(ccc[1]);
    print_int(ccc[5]);
    print_int(c+b);
    //std::cout << (b) << std::endl;


    if (true){
        print_int(10);
    }
    else{
        print_int(20);
    }


    if (2){ // error
        print_int(10);
    }
    else{
        print_int(20);
    }


}





int func(){
    int b = 10;
    a += b;
    print_int(10);
    return a;
}