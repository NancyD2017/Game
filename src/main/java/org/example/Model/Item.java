package org.example.Model;

enum Item {
    E('0'),
    P('r'),
    R('R'),
    T('t'),
    C('c');
    private final char value;
    Item(char value) {
        this.value = value;
    }
}
