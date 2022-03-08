package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    static final int RADKY = 2;//radky = velkost ktreu fakt chci -1
    static final int SLOUPCE = 2;

    public static void main(String[] args) {

        Cell[][] maze = new Cell[RADKY+1][SLOUPCE+1];

        //fills grid with Cells - all have all 4 walls
        for (int x = 0; x <= RADKY; x++) {
            for (int y = 0; y <= SLOUPCE; y++) {
                maze[x][y] = new Cell(x, y);
            }
        }

        Cell current;
        Cell next;
        System.out.println("vypisuji souradnice kazde bunky");
        for (int x = 0; x <= RADKY; x++) {
            for (int y = 0; y <= SLOUPCE; y++) {
                current = maze[x][y];
                System.out.print(current.getX());
                System.out.println(" "+ current.getY());
            }
        }

        System.out.println("vypisuji random valid souradnice");
        //generating random coordinates of a cell to choose one
        Random rand = new Random();
        int upperbound = RADKY-1;//btw starting from 0 - upperbound is 1 smaller

        //generates random values
        int random_radek = rand.nextInt(upperbound);
        int random_sloupec = rand.nextInt(upperbound);

        System.out.println(random_radek);
        System.out.println(random_sloupec);
        System.out.println("souradnice nahodne bunky");

        current = maze[random_radek][random_sloupec];//nahodna bunka
        current.setVisited(true);
        next = maze[random_radek][random_sloupec];

        int remaning = (RADKY+1)*(SLOUPCE+1)-1;//RADKY*SLOUPCE-1;//(radky+1)*(sloupce+1)-1
        int[]policko;
        int x = current.getX();
        int y = current.getY();
        int Nx = 0;
        int Ny = 0;
        int u=0;

        while(remaning > 0){
            x = current.getX();
            y = current.getY();
            policko = vyberRandSouseda(x, y);
            Nx = policko[0];//nove souradnice od bunky na x, y
            Ny = policko[1];
            if( (Nx >= 0) && (Ny >= 0) && (Nx <= RADKY) && (Ny <= SLOUPCE)){//souradnice se fitujou do maze
                next = maze[Nx][Ny];
                if(next.isVisited() == false){
                    current.removeWalls(next);
                    u++;
                    remaning = remaning - 1;
                    next.setVisited(true);
                }
                current = next;
                System.out.println(remaning);
            }
        }

        System.out.println("vypisuji steny ig");
        for (int z = 0; z <= RADKY; z++) {
            for (int p = 0; p <= SLOUPCE; p++) {
                current = maze[z][p];
                System.out.print(current.getX());
                System.out.println(" "+ current.getY());
                System.out.println(Arrays.toString(current.getWalls()));
            }
        }
        System.out.println("pocet mazání "+u);
        
        System.out.println("maze ig");
        boolean[] pole;
        for(int j=0; j<= SLOUPCE*2 - 1; j++) {
            System.out.print("_ ");
        }
        System.out.println();
        for(int i=0; i <= RADKY; i++){
            for(int j=0; j<= SLOUPCE; j++) {
                current = maze[i][j];
                pole = current.getWalls();
                //System.out.print("| ");
                if(pole[2] == true){//ma levou stenu
                    System.out.print("|"); //█
                }
                if(pole[3] == true){//mad dolni stenu
                    System.out.print("_");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.print(" |");
            System.out.println();
        }
    }

    public static int[] vyberRandSouseda(int x, int y) {
        int[] smery = {0, 1, 2, 3}; //nahoru, doprava, doleva, dolu

        Random ran = new Random();
        int smer = ran.nextInt(4);
        int Nx = x;
        int Ny = y;

        if(smer == 0){//nahoru
            Nx = x - 1;
        }
        else if(smer == 1){ //doprava
            Ny = y + 1;
        }
        else if(smer == 2) {//dolu
            Nx = x + 1;
        }
        else if(smer == 3){//doleva
            Ny = y - 1;
        }
        int[]pole = {Nx, Ny};
        return pole;
    }
}
