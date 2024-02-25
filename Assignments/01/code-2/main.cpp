#include <bits/stdc++.h>
#define endl "\n"

using namespace std;

const int max_number=1e5+10;

string a[max_number];

void invert(int index, int x);
int main()
{
    int t;
    cin>>t;
    for (int c = 0; c < t; c++)
    {
        long long total = 0;
        int n,m;
        cin>>n>>m;
        for(int i=0;i<n;i++)
        {
            cin>>a[i];
        }
        if(n==1)
        {
            cout<<0<<endl;
            continue;
        }
        for(int i=m-1;i>=0;i--)
        {
            long long one_count=0;
            for(int j=0;j<n;j++)
            {
                if(a[j][i] == '1')
                {
                    one_count++;
                }
            }
            if(one_count > n/2)
            {
                for(int j=0;j<n;j++)
                {
                    if(a[j][i]=='0')
                    {
                        invert(j, i);
                        total++;
                    }
                }
            }
            else
            {
                for(int j=0;j<n;j++)
                {
                    if(a[j][i]=='1')
                    {
                        invert(j, i);
                        total++;
                    }
                }
            }
        }
        cout<<total<<endl;
    }
}


void invert(int index, int range)
{
    for(int i=0;i<=range;i++)
    {
        if(a[index][i]=='0')
            a[index][i]='1';

        else
         a[index][i]='0';
    }
}




