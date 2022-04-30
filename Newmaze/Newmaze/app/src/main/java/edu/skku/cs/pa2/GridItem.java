package edu.skku.cs.pa2;

public class GridItem {

    int num;
    int num_maze;
    String cango;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum_maze() {
        return num_maze;
    }

    public void setNum_maze(int num_maze) {
        this.num_maze = num_maze;
    }

    public String getCango() {
        return cango;
    }

    public void setCango(String cango) {
        this.cango = cango;
    }

    public GridItem(int num, int num_maze, String cango){
        this.num = num;
        this.num_maze = num_maze;
        this.cango = cango;
    }



}
