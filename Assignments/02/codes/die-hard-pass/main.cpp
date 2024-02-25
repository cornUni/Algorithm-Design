#include <iostream>
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;

vector<int> memo;
vector<int> result;


bool isPrime(int n) {
    if(n <= 1) return false;
    if(n <= 3) return true;

    if(n % 2 == 0 || n % 3 == 0) return false;

    for(int i = 5; i * i <= n; i = i + 6)
        if(n % i == 0 || n % (i + 2) == 0) return false;

    return true;
}

bool all_prime(int number){
        if (number<10){
        return isPrime(number);
    }
    else{
        return isPrime(number)&& all_prime(number/10);
    }
    return true;
}

bool check_number(int number){


    while (number>0){
        if (count(memo.begin(), memo.end(), number)){
                return true;
        }
        else{
            if (isPrime(number)){
                memo.push_back(number);
                number /= 10;
            }
            else
                return false;
        }
    }
}

int main() {
    int n;
    cin>>n;

    for (int i = pow(10, n-1); i < pow(10, n) ; ++i) {
        if (all_prime(i))
            cout<<i<<endl;
    }
    return 0;
}
