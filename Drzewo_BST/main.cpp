#include<iostream>
#include<cstdlib>

using namespace std;

class BST
{
    struct node
    {
        int data;
        node* left;
        node* right;
    };

    node* root;

    node* findMin(node* t)
    {
        if(t == NULL)
            return NULL;
        else if(t->left == NULL)
            return t;
        else
            return findMin(t->left);
    }

    node* findMax(node* t)
    {
        if(t == NULL)
            return NULL;
        else if(t->right == NULL)
            return t;
        else
            return findMax(t->right);
    }

    node* wstaw(int x, node* t)
    {
        if(t == NULL)
        {
            t = new node;
            t->data = x;
            t->left = t->right = NULL;
        }
        else if(x < t->data)
            t->left = wstaw(x, t->left);
        else if(x > t->data)
            t->right = wstaw(x, t->right);
        return t;
    }


    node* dodaj(int x, node* t)
    {
        if(t == NULL)
        {
            t = new node;
            t->data = x;
            t->left = t->right = NULL;
        }
        else if(x < t->data)
            t->left = wstaw(x, t->left);
        else if(x > t->data)
            t->right = wstaw(x, t->right);
        return t;
    }


    node* usun(int x, node* t)
    {
        node* temp;
        if(t == NULL)
            return NULL;
        else if(x < t->data)
            t->left = usun(x, t->left);
        else if(x > t->data)
            t->right = usun(x, t->right);
        else if(t->left && t->right)
        {
            temp = findMin(t->right);
            t->data = temp->data;
            t->right = usun(t->data, t->right);
        }
        else
        {
            temp = t;
            if(t->left == NULL)
                t = t->right;
            else if(t->right == NULL)
                t = t->left;
            delete temp;
        }

        return t;
    }

    void inorder(node* t)
    {
        if(t == NULL)
            return;
        inorder(t->left);
        cout << t->data << " ";
        inorder(t->right);
    }

    node* wyszukaj(node* t, int x)
    {
        if(t == NULL){
            cout<<"Nie znaleziono"<<endl;
            return NULL;}
        else if(x < t->data)
            return wyszukaj(t->left, x);
        else if(x > t->data)
            return wyszukaj(t->right, x);
        else{
            cout<<"Znaleziono"<<endl;
            return t;}
    }

public:
    BST()
    {
        root = NULL;
    }

    void dodaj(int x)
    {
        root = dodaj(x, root);
    }

    void usun(int x)
    {
        root = usun(x, root);
    }

    void wypisz()
    {
        inorder(root);
        cout << endl;
    }

    void wyszukaj(int x)
    {
        root = wyszukaj(root, x);
    }
};

int main()
{
    BST t;
    int x(1),y,z;
    while(x=1)
    {
    system("cls");
    cout<<"Witaj w super programie :)"<<endl;
    cout<<"Co chcesz uczynic?"<<endl;
    cout<<"1. Dodaj"<<endl;
    cout<<"2. Usun"<<endl;
    cout<<"3. Wyszukaj"<<endl;
    cout<<"4. Wypisz wartosci"<<endl;
    cout<<"5. Zakoncz program"<<endl;
    cin>>y;
        switch(y)
        {
        case 1:
            cout<<"Co chcesz dodac?"<<endl;
            cin>>z;
            t.dodaj(z);
            break;
        case 2:
            cout<<"Co chcesz usunac?"<<endl;
            cin>>z;
            t.usun(z);
            break;
        case 3:
            cout<<"Jaki element chcesz wyszukac?"<<endl;
            cin>>z;
            t.wyszukaj(z);
            while (y!=1){
            cout<<"Wcisnij 1, aby przejsc do menu"<<endl;
            cin>>y;
            }
            break;
        case 4:
            t.wypisz();
            while (y!=1){
            cout<<"Wcisnij 1, aby przejsc do menu"<<endl;
            cin>>y;
            }
            break;
        case 5:
            x++;
            break;
        default:
            cout<<"Nie ma takiej opcji!!!"<<endl;
            break;
        }
    }
}
