package org.example.model;

class FieldItem {
        Integer col;
        Integer ro;
        Item item;
        FieldItem(Integer ro, Integer col, Item item) {
            this.ro = ro;
            this.col = col;
            this.item = item;
        }
}
