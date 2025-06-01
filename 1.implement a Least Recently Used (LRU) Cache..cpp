#include <iostream>
#include <unordered_map>
using namespace std;


class LRUCache {
private:
    // Doubly linked list node
    struct Node {
        int key, value;
        Node* prev;
        Node* next;
        Node(int k, int v) : key(k), value(v), prev(nullptr), next(nullptr) {}
    };


    int capacity;
    unordered_map<int, Node*> cache; // key -> node
    Node* head; // most recently used
    Node* tail; // least recently used


    // Add node right after head
    void addNode(Node* node) {
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }


    // Remove node from the list
    void removeNode(Node* node) {
        Node* prevNode = node->prev;
        Node* nextNode = node->next;
        prevNode->next = nextNode;
        nextNode->prev = prevNode;
    }


    // Move node to the front (most recently used)
    void moveToHead(Node* node) {
        removeNode(node);
        addNode(node);
    }


    // Remove the least recently used node (from the tail)
    Node* removeTail() {
        Node* lru = tail->prev;
        removeNode(lru);
        return lru;
    }


public:
    LRUCache(int capacity) : capacity(capacity) {
        head = new Node(0, 0); // dummy head
        tail = new Node(0, 0); // dummy tail
        head->next = tail;
        tail->prev = head;
    }


    int get(int key) {
        if (cache.find(key) == cache.end())
            return -1;


        Node* node = cache[key];
        moveToHead(node);
        return node->value;
    }


    void put(int key, int value) {
        if (cache.find(key) != cache.end()) {
            // Update existing node
            Node* node = cache[key];
            node->value = value;
            moveToHead(node);
        } else {
            // Add new node
            if (cache.size() >= capacity) {
                Node* lru = removeTail();
                cache.erase(lru->key);
                delete lru;
            }
            Node* newNode = new Node(key, value);
            cache[key] = newNode;
            addNode(newNode);
        }
    }


    ~LRUCache() {
        Node* current = head;
        while (current) {
            Node* next = current->next;
            delete current;
            current = next;
        }
    }
};
int main() {
    LRUCache lru(2);
    lru.put(1, 1);
    lru.put(2, 2);
    cout << lru.get(1) << endl; 
    lru.put(3, 3);              
    cout << lru.get(2) << endl; 
    lru.put(4, 4);          
    cout << lru.get(1) << endl; 
    cout << lru.get(3) << endl; 
    cout << lru.get(4) << endl; 
    return 0;
}
