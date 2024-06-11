#ifndef MYARRAY_H
#define MYARRAY_H

#include <iostream>
using namespace std;
class MyArray { // Class declaration
public:
	MyArray(int N = 10);//initialize the array with capacity cap, default values 10 and Fill elements up to size with zeros
	MyArray(MyArray &in);//Copy constructor
	void copy(MyArray& in); //copy elements of array in
    ~MyArray();//Destructor
    int getLength() const;// return the size of the array.
    double getAt(int K);
    void setAt(int K, double value);
	virtual void print();
    void convolve(MyArray &in);
	double* getPointer();
private:
    int length;
    double  * arr;
};

#endif