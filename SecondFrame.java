import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SecondFrame extends JFrame{
    private JFrame f = new JFrame("Second");
    static int RADKY = HlavniMenu.getSizeI()+1;//potrebujeme velikosti bludiste z ComboBoxu
    static int SLOUPCE = HlavniMenu.getSizeJ()+1;
    Cell[][] Maze = new Cell[RADKY+1][SLOUPCE+1];

    public SecondFrame() {
        setTitle("Aldous-broderův algoritmus");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SecondFrame view = new SecondFrame();
            }
        });
    }

    @Override
    public void paint(Graphics g){//vygeneruje bludiste a nakreslí ho na obrazovku
        super.paint(g);
        g.translate(50, 50);//dava vystup trochu od kraje
        Maze = mazeGen();
        Cell current;
        //vykreslovani
        for (int z = 0; z <= RADKY; z++) {
            for (int p = 0; p <= SLOUPCE; p++) {
                current = Maze[z][p];
                current.draw(g);
            }
        }
    }

    /**
     * vybere nahodneho souseda dane bunky v poli
     * @param x souradnice x nasi bunky
     * @param y souradnice y nasi bunky
     * @return dvouprvkové pole se souradnicí x a y souseda
     */
    public static int[] vyberRandSouseda(int x, int y) {
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

    public static Cell[][] mazeGen() {
        Cell[][] maze = new Cell[RADKY+1][SLOUPCE+1];

        //vygeneruj maze
        //naplni grid bunkami Cell - vsechny maji vsechny 4 steny
        for (int x = 0; x <= RADKY; x++) {
            for (int y = 0; y <= SLOUPCE; y++) {
                maze[x][y] = new Cell(x, y);
            }
        }

        Cell current;
        Cell next;

        //gerovani nahode sourdnice pro prvni bunku
        Random rand = new Random();
        int upperbound = RADKY-1;//-1 protoze Random() zacina od 0
        int random_radek = rand.nextInt(upperbound);
        int random_sloupec = rand.nextInt(upperbound);

        current = maze[random_radek][random_sloupec];//nahodna bunka
        current.setVisited(true);
        next = maze[random_radek][random_sloupec];

        int remaning = (RADKY+1)*(SLOUPCE+1)-1;
        int[]policko;
        int x = current.getX();
        int y = current.getY();
        int Nx = 0;
        int Ny = 0;

        while(remaning > 0){//dokud neporjdeme celej maze
            x = current.getX();
            y = current.getY();
            policko = vyberRandSouseda(x, y);//vraci souradnice noveho souseda
            Nx = policko[0];//nove souradnice od bunky na x, y
            Ny = policko[1];
            if( (Nx >= 0) && (Ny >= 0) && (Nx <= RADKY) && (Ny <= SLOUPCE)){//souradnice se vejdou do maze
                next = maze[Nx][Ny];
                if(next.isVisited() == false){
                    current.removeWalls(next);
                    remaning = remaning - 1;
                    next.setVisited(true);
                }
                current = next;
                System.out.println(remaning);
            }
        }
        maze[0][0].walls[0] = false;//start a konec
        maze[RADKY][SLOUPCE].walls[3] = false;

        return maze;
    }
}