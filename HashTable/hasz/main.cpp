#include <iostream>
#include <string.h>
#include <windows.h>
#include <conio.h>

using namespace std;

struct baza {
	char make[20];
	char model[20];
	char vin[20];
	char year[5];
	int o;
};


int main()
{
	char number[20]; // numer vin do usuniecia
	int x, n(0), z(0);
	int counter(0), y, haszpos; // haszpoz indekst tablicy haszujacej
	int temp;
	int pos1, pos2, k; // zmienne do sortowania
	cout << "Cars" << endl;
	baza cars[100]; //tablica pomocnicza #1
	baza carsort[100]; //tablica pomocnicza sort
	baza carhasz[100]; //tablica haszujaca
	baza temporary[1]; //tablica pomocnicza temp
	for (int i = 0; i < 100; i++)
		carhasz[i].o = 0;
	while (z == 0) {

		cout << "----------MENU-----------" << endl;
		cout << "1.Add a new car" << endl;
		cout << "2.Delete a car" << endl;
		cout << "3.Print all cars" << endl;
		cout << "4.Sort all cars by VIN number" << endl;
		cout << "5.Search a car by VIN number" << endl;
		cout << "6.Quit" << endl;
		cin >> x;

		switch (x) {
		case 1:
			counter = 0;
			cout << "Make:" << endl;
			cin >> cars[n].make;
			cout << "Model:" << endl;
			cin >> cars[n].model;
			cout << "VIN number:" << endl;
			cin >> cars[n].vin;
			cout << "Year of manufacture:" << endl;
			cin >> cars[n].year;
			y = 0;
			while (cars[n].vin[y] != 0) {
				counter += (int)cars[n].vin[y];
				y++;
			}
			haszpos = counter % 100;
			y = 0;
			for (int i = 0; i < 100; i++) {
				if (strcmp(cars[n].vin, carhasz[i].vin) == 0)
					y = 1;
			}
			while (y == 0) {
				if (carhasz[haszpos].o == 0) {
					carhasz[haszpos] = cars[n];
					carhasz[haszpos].o = 1;
					y++;
				}
				else haszpos++;
			}
			n++;
			system("cls");
			break;

		case 2:
			/*
			cout << "VIN number:" << endl;
			cin >> number;
			y = 0;
			while (number[y] != 0) {
				counter += (int)number[y];
				y++;
			}
			haszpos = counter % 100;
			y = 0;
			while (y == 0) {
				if (strcmp(carhasz[haszpos].vin, number) == 0) {
					carhasz[haszpos].o = 0;
					y++;
					cout << "Deleted!!!" << endl;
				}
				else haszpos++;
			}
			system("cls");
			break;
			*/

			cout << "VIN number:" << endl;
			cin >> number;
			y = 0;
			counter = 0;
			for (int i = 0; i < strlen(number); i++) {
				counter += (int)number[i];
			}
			haszpos = counter % 100;
			if (carhasz[haszpos].o == 0) {
				y = 1;
				cout << "Not found!!!" << endl;
			}
			else y = 0;
			while (y == 0) {
				if (strcmp(carhasz[haszpos].vin, number) == 0) {
					
					carhasz[haszpos].o = 0;
					cout << "Deleted!!!" << endl;
					y++;

				}
				else haszpos++;
			}
			cout << "Press any key" << endl;
			_getch();
			system("cls");
			break;

		case 3:
			for (int i = 0; i < 100; i++) {
				if (carhasz[i].o == 1) {
					cout << "Index number:" << i << endl;
					cout << "Make: " << carhasz[i].make << endl;
					cout << "Model: " << carhasz[i].model << endl;
					cout << "VIN number: " << carhasz[i].vin << endl;
					cout << "Year of manufacture: " << carhasz[i].year << endl;
					cout << "----------------------" << endl;
				}
			}
			_getch();
			system("cls");
			break;

		case 4:
			y = 0;
			for (int i = 0; i < 100; i++) {
				if (carhasz[i].o == 1) {
					carsort[y] = carhasz[i];
					y++;
				}
			}

			for (int i = 0; i < y - 1; i++) {
				for (int j = 0; j < y - i - 1; j++) {
					k = 0;
					counter = 0;
					while (carsort[j].vin[k] != NULL) {
						counter += (int)carsort[j].vin[k];
						k++;
					}
					pos1 = counter;

					k = 0;
					counter = 0;
					while (carsort[j + 1].vin[k] != NULL) {
						counter += (int)carsort[j + 1].vin[k];
						k++;
					}
					pos2 = counter;
					if (pos1 > pos2) {
						temporary[0] = carsort[j];
						carsort[j] = carsort[j + 1];
						carsort[j + 1] = temporary[0];
					}
				}
			}
			for (int i = 0; i < y; i++) {
				cout << "Make: " << carsort[i].make << endl;
				cout << "Model: " << carsort[i].model << endl;
				cout << "VIN number: " << carsort[i].vin << endl;
				cout << "Year of manufacture: " << carsort[i].year << endl;
				cout << "----------------------" << endl;
			}
			_getch();
			system("cls");

			break;

		case 5:

			cout << "VIN number:" << endl;
			cin >> number;
			y = 0;
			counter = 0;
			for (int i = 0; i < strlen(number); i++) {
				counter += (int)number[i];
			}
			haszpos = counter % 100;
			if (carhasz[haszpos].o == 0) {
				y = 1;
				cout << "Not found!!!" << endl;
			}
			else y = 0;
			while (y == 0) {
				if (strcmp(carhasz[haszpos].vin, number) == 0) {

					cout << "Index number: " << haszpos << endl;
					cout << "Make: " << carhasz[haszpos].make << endl;
					cout << "Model: " << carhasz[haszpos].model << endl;
					cout << "VIN number: " << carhasz[haszpos].vin << endl;
					cout << "Year of manufacture:" << carhasz[haszpos].year << endl;
					cout << "----------------------" << endl;

					y++;

				}
				else haszpos++;
			}
			cout << "Press any key" << endl;
			_getch();
			system("cls");
			break;

		case 6:
			z++;
			break;

		default:
			cout << "No such option!!!" << endl;
			cout << "Press any key" << endl;
			_getch();
			system("cls");
			break;
		}
	}

	return 0;
}
