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

int main() {
    int n;
    cin>>n;

    vector<int> init {2,3,5,7}; ;
    int available_digits[] =   {1,3,7,9};
//
//    for (int i = pow(10, n-1); i < pow(10, n) ; ++i) {
//        if (all_prime(i))
//            cout<<i<<endl;
//    }

    for (int i = 0; i <n-1 ; ++i) {
        vector<int> temp;
        for (int j = 0; j < init.size(); ++j) {
            for (int k = 0; k < 4; ++k) {
                int temp_prime = init[j]*10 + available_digits[k];
                if (isPrime(temp_prime))
                    temp.push_back(temp_prime);
            }
        }
        init = temp;
    }
    for (int i = 0; i < init.size(); ++i) {
        cout<<init[i]<<endl;
    }
    return 0;
}
