#include <iostream>
using namespace std;
#include "MyArray.h"

MyArray::MyArray(int N) : length(N)
{
	arr = new double[length];
	for (int i = 0; i < length; i++)
	{
		arr[i] = 0;
	}
}

MyArray::MyArray(MyArray &in)
{
	length = in.length;
	arr = new double[length];
	for (int i = 0; i < length; i++)
	{
		arr[i] = in.arr[i];
	}
}

void MyArray::copy(MyArray &in)
{
	if (length != in.length)
		return; // lengths are unequal
	for (int i = 0; i < length; i++)
	{
		arr[i] = in.arr[i];
	}
}

MyArray::~MyArray()
{
	delete[] arr;
	arr = NULL;
}

int MyArray::getLength() const {
	return length;
}

double MyArray::getAt(int K)
{
	return arr[K];
}

void MyArray::setAt(int K, double value)
{
	if (K < 0 || K >= length)
		return;
	arr[K] = value;
}

void MyArray::print()
{
	cout << "Array contains: ";
	for (int i = 0; i < length; i++)
		cout << arr[i] << " ";
	cout << endl;
}

void MyArray::convolve(MyArray &in)
{
	if (length != in.length)
		return;
	for (int i = 0; i < length; i++)
		arr[i] += in.arr[i];
}

double * MyArray::getPointer()
{
	return arr;
}

void main() {
	MyArray A(5); // constructor
	MyArray B(A); // copy constructor
	MyArray C(5);

	A.print(); // print function
	C.setAt(0, 1); // setter
	A.copy(C); // copy function
	A.print();

	cout << "Element at position 0 of C is ";
	cout << C.getAt(0) << endl; // getter

	A.setAt(1, 2);
	A.convolve(C); // convolve function
	A.print();

	cout << "Length of A is ";
	cout << A.getLength() << endl; // get length

	double* arr = A.getPointer(); // get pointer
	cout << "Element at position 1 of A is ";
	cout << arr[1] << endl;
}