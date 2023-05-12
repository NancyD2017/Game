package org.example.model;

enum Item {
    O('0'),
    r('r'),
    R('R'),
    T('t'),
    C('c');
    private final char value;
    Item(char value) {
        this.value = value;
    }
}
