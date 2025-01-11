class Person {
public:
    virtual char toString() const = 0;
    virtual ~Person() {} // Virtueller Destruktor
};

class Student : public Person {
public:
    char toString() const override {

        return 'S';

    }
};

int main() {
    Student s;
    //s.toString();
    return 0;
}

