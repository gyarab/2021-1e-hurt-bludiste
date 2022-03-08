package com.company;

public class Cell {

    private int x, y, distance, id;

    private Cell parent;

    private boolean visited = false;

    public boolean[] walls = {true, true, true, true};//top, right, left, down

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    //constructor of class - mby move upwards
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setX(int x){ this.x = x; }

    public void setY(int y){ this.y = y; }

    public void removeWalls(Cell next) {
        int i = this.x - next.x;
        if(i == 1){//jdu nahoru
            walls[0] = false; //horni stena pryc
            next.walls[3] = false; //dolni strana pryc
        }
        else if(i == -1){//jdu dolu
            walls[3] = false; //spodni strana pryc
            next.walls[0] = false; //horni strana pryc
        }

        int j = this.y - next.y;
        if(j == -1){// jdu doprava
            walls[1] = false; //prava stena pryc
            next.walls[2] = false; //leva stena pryc
        }
        else if(j == 1){//jdu doleva
            walls[2] = false; //leva strana pryc
            next.walls[1] = false; //prava strana pryc
        }
    }
}
