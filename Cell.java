import java.awt.*;

public class Cell {
    public static final int W = 20;//sirka jednoho ctverecku v px
    private int x, y;//souradnice dane bunky
    private boolean visited = false;
    public boolean[] walls = {true, true, true, true};//top, right, left, down

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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

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

    public void draw(Graphics g){
        int x2 = this.y * W;
        int y2 = this.x * W;

        g.setColor(Color.BLACK);
        if (walls[0]) {//horní check
            g.drawLine(x2, y2, x2+W, y2);
        }
        if (walls[1]) {//pravá check
            g.drawLine(x2+W, y2, x2+W, y2+W);
        }
        if (walls[2]) { //LEvá
            //g.drawLine(x2+W, y2+W, x2, y2+W);
            g.drawLine(x2, y2+W, x2, y2);
        }
        if (walls[3]) { //dolní
            g.drawLine(x2+W, y2+W, x2, y2+W);
        }
    }
}