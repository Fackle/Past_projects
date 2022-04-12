#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void wypelnij(int tab[], int n){
for(int i=0;i<n;i++)
    tab[i]=rand()%1000;
}

void wypisz(int tab[], int n){
for(int i=0;i<n;i++)
    cout<<tab[i]<<endl;
}

void babelkowe_sort(int tab[], int n)
{
   int i, j, temp;
   for (i=0; i<n-1; i++){
        for (j = 0; j < n-i-1; j++){
           if (tab[j] > tab[j+1]){
            temp=tab[j];
            tab[j]=tab[j+1];
            tab[j+1]=temp;
           }
        }
}
}

void wstawianie_sort(int tab[], int n)
{
    int i, j, temp;
    for(i=1; i<n; i++){
        temp=tab[i];
        for(j=i-1; j>=0 && tab[j]>temp; j--)
             tab[j+1]=tab[j];
        tab[j+1]=temp;
    }
}

void wybieranie_sort(int tab[], int n )
{
    int k,temp;
    for(int i=0; i < n; i++)
    {
        k=i;
        for( int j=i+1; j<n; j++)
        if(tab[j]<tab[k])
             k=j;
        temp=tab[k];
        tab[k]=tab[i];
        tab[i]=temp;
            }
}

void scalanie(int pocz, int sr, int kon, int tab[], int t[])
{
int i,j,q;
for (i=pocz; i<=kon; i++) t[i]=tab[i];
i=pocz; j=sr+1; q=pocz;
while (i<=sr && j<=kon) {
if (t[i]<t[j])
tab[q++]=t[i++];
else
tab[q++]=t[j++];
}
while (i<=sr) tab[q++]=t[i++];
}

void scalanie_sort(int pocz, int kon, int tab[], int t[])
{
int sr;
if (pocz<kon) {
sr=(pocz+kon)/2;
scalanie_sort(pocz, sr, tab, t);
scalanie_sort(sr+1, kon, tab , t);
scalanie(pocz, sr, kon, tab, t);
}
}

int podzial(int tab[], int a, int b){
int x = tab[a];
int i=a, j=b, d;
while (true){
while (tab[j] > x)
j--;
while (tab[i] < x)
i++;
if (i < j){
d = tab[i];
tab[i] = tab[j];
tab[j] = d;
i++;
j--;
}
else
return j;
}
}

void szybki_sort(int tablica[], int a, int b){
int c;
if (a < b){
c = podzial(tablica,a,b);
szybki_sort(tablica, a, c);
szybki_sort(tablica, c+1, b);
}
}

int main()
{
    int n,x,y(1);
    cout<<"Podaj rozmiar tablicy:";
    cin>>n;
    int tab[n],t[n];
    wypelnij(tab,n);
    cout<<"Nieposortowana tablica:"<<endl;
    wypisz(tab,n);

    while(y==1){

    cout<<"Wybierz w jaki sposob chcesz posortowac tablice:" << endl;
    cout<<"1. Sortowanie babelkowe"<<endl;
    cout<<"2. Sortowanie przez wstawianie"<<endl;
    cout<<"3. Sortowanie przez wybieranie"<<endl;
    cout<<"4. Sortowanie przez scalanie"<<endl;
    cout<<"5. Sortowanie szybkie"<<endl;
    cout<<"6. Wypisz tablice"<<endl;
    cout<<"7. Wygeneruj nowa tablice"<<endl;
    cout<<"8. Zakoncz dzialanie programu"<<endl;
    cin>>x;

    switch(x){
case 1:
babelkowe_sort(tab,n);
wypisz(tab,n);
    break;

case 2:
wstawianie_sort(tab,n);
wypisz(tab,n);
    break;

case 3:
wybieranie_sort(tab,n);
wypisz(tab,n);
    break;

case 4:
scalanie_sort(0,n-1,tab,t);
wypisz(tab,n);
    break;

case 5:
szybki_sort(tab, 0 ,n-1);
wypisz(tab,n);
    break;

case 6:
wypisz(tab,n);
    break;

case 7:
wypelnij(tab,n);
    break;

case 8:
    y=0;
    break;

default:
    cout<<"Nie podano prawidlowej wartosci"<<endl;
    }
    cout<<"Nacisnij dowolny przycisk"<<endl;
    system ("PAUSE > NULL");
    system("cls");
    }
    return 0;
}
