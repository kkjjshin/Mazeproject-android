package edu.skku.cs.pa2;

public class Rcp {
    int row, col;
    String path;
    public Rcp(int row, int col, String ph) {
        this.row = row;
        this.col = col;
        if("".equals(ph)) {
            this.path =  row + "," + col ;
        }
        else {
            this.path = ph + ">"  + row + "," + col ;
        }
    }
}
