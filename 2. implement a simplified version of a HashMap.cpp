#include <iostream>
#include <vector>
using namespace std;


class MyHashMap {
private:
    static const int SIZE = 10007; // A prime number to reduce collisions
    vector<pair<int, int>> table[SIZE]; // Array of vectors for chaining


    int hash(int key) {
        return key % SIZE;
    }


public:
    MyHashMap() {
        // Nothing needed for initialization
    }


    void put(int key, int value) {
        int idx = hash(key);
        for (auto& pair : table[idx]) {
            if (pair.first == key) {
                pair.second = value; // Update existing key
                return;
            }
        }
        table[idx].push_back({key, value}); // Insert new key-value pair
    }


    int get(int key) {
        int idx = hash(key);
        for (auto& pair : table[idx]) {
            if (pair.first == key) return pair.second;
        }
        return -1; // Not found
    }


    void remove(int key) {
        int idx = hash(key);
        auto& bucket = table[idx];
        for (auto it = bucket.begin(); it != bucket.end(); ++it) {
            if (it->first == key) {
                bucket.erase(it);
                return;
            }
        }
    }
};
int main() {
    MyHashMap obj;
    obj.put(1, 10);
    obj.put(2, 20);
    cout << obj.get(1) << endl;
    cout << obj.get(3) << endl;
    obj.put(2, 30);
    cout << obj.get(2) << endl;
    obj.remove(2);
    cout << obj.get(2) << endl;
    return 0;
}


