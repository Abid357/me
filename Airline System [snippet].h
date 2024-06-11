#include <iostream>
using namespace std;

void main(){
	int seat[10] = { 0 }, x, c1 = 0, c2 = 5;
	char answer;
	while (c1 != 5 || c2 != 10){
		cout << " Please type 1 for \"First Class\" and 2 for \"Economy Class\": ";
		cin >> x;
		if (x == 1){
			if (c1 != 5){
				seat[c1] = 1;
				cout << " Your flight details:  First Class and Seat# " << c1 + 1 << endl << endl;
				c1++;
			}
			else {
				cout << " First Class is FULL.  Do you want an Economy Class instead? (Y / N): ";
				cin >> answer;
				if (answer == 'N' || answer == 'n')
					cout << " Next flight leaves in 3 hours. " << endl << endl;

				else {
					seat[c2] = 1;
					cout << "Your flight details:  Economy Class and Seat# " << c2 + 1 << endl << endl;
					c2++;
				}
			}
		}
		else {
			if (c2 != 10){
				seat[c2] = 1;
				cout << "Your flight details:  Economy Class and Seat# " << c2 + 1 << endl << endl;
				c2++;
			}
			else {
				cout << " Economy Class is FULL.  Do you want a First Class instead? (Y / N): ";
				cin >> answer;
				if (answer == 'N' || answer == 'n')
					cout << " Next flight leaves in 3 hours. " << endl << endl;

				else {
					seat[c1] = 1;
					cout << " Your flight details:  First Class and Seat# " << c1 + 1 << endl << endl;
					c1++;
				}
			}
		}
	}
}
