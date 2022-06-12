#include <iostream>
#include <algorithm>

using namespace std;
 
int main()
{
    int N;
    int Q;
    cin >> N;
    cin >> Q;
    int price[N+1];
    price[0]=0;
    for (int i=1;i<=N;i++) cin >> price[i];

    sort(price,price+N+1);
    bool debug = false;
    if (debug){
        for (int i=0;i<=N;i++){
            cout << price[i] << " ";
        }
        cout << endl;
    }

    long long presum[N+1];
    for (int i=1;i<=N;i++){
        presum[i]=presum[i-1]+price[i];
    }

    for (int i=0;i<Q;i++){
        int x, y;
        cin >> x;
        cin >> y;
        cout << (presum[N-x+y]-presum[N-x]) << endl;
    }
 
    return 0;
}