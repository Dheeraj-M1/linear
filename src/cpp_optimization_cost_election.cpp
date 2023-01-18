#include<iostream>
#include<cmath>
#include<cstdio>
using namespace std;

int main(){
double x1, x2, x3, x4;
double a[3][5] = { {-2,8,0,10,50}, {5,2,0,0,100}, {3,-5,10,-2,25} };
int i, j, k;
double obj_func = -(x1 + x2 + x3 + x4);


for(i=0; i<3; i++){
    for(j=0; j<5; j++){
        if(j==i){
            double temp = a[i][j];
            for(k=0; k<5; k++){
                a[i][k] /= temp;
            }
        }
    }
    for(j=0; j<3; j++){
        if(i!=j){
            double temp = a[j][i];
            for(k=0; k<5; k++){
                a[j][k] -= temp * a[i][k];
            }
        }
    }
}

cout<<"x1: "<<a[0][4]<<endl;
cout<<"x2: "<<a[1][4]<<endl;
cout<<"x3: "<<a[2][4]<<endl;
cout<<"x4: "<<a[3][4]<<endl;
cout<<"minimum cost: "<<a[0][4]+a[1][4]+a[2][4]<<endl;
return 0;
}
