package org.example;

import org.example.Model.Colors;

public class Dib {
        private Integer row;
        private Integer column;
        private Colors color;
        private String type;

        public Dib(Integer row, Integer column, Colors color, String type) {
            this.row = row;
            this.column = column;
            this.color = color;
            this.type = type;
        }

        public Integer getRow() {
            return row;
        }

        public Integer getColumn() {
            return column;
        }

        public Colors getColor() {
            return color;
        }

        public String getType() {
            return type;
        }
}
