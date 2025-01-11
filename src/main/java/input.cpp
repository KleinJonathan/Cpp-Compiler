class PersonA{
    int test(int a){
        return a;
    }
    int test(char a){
        return 10;
    }
    virtual int test2(int a) = 0;
};

class PersonB : PersonA{
    int test(int a){
        return a;
    }
};


int main() {

    if (int a = 10){
        int b = 10 + a;
    } else if (int b = 20){
        int c = 10 + a;
    }

    const int a = 10;

}