import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener
{
    private int[] snakex = new int[750];   //arrays pentru a stoca coordonatele segmentelor sarpelui
    private int[] snakey = new int[750];

    //variabile de tip boolean care indica directia de deplasare a sarpelui: (ex: left = right, iar right, up, down = false =>sarpele merge spre stanga)
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    //obiecte ImageIcon: imagini pentru diverse parti ale jocului
    private ImageIcon dreapta;
    private ImageIcon stanga;
    private ImageIcon sus;
    private ImageIcon jos;
    private ImageIcon snakebody;
    private ImageIcon titleImage;

    private Timer timp;  //obiect timer pentru a gestiona actualizarile periodice ale jocului
    private int delay = 100;  // o intarziere in minisecunde pentru timer

    private int lungimeSarpe = 3;  //lungimea initiala a sarpelui
    private int moves =0;  //nr de misc
    private int score = 0;  //scorul

    private int[] mancarex = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] mancarey = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon mancare;
    private Random random = new Random();

    private int xpos = random.nextInt(34);   //coordonatele curente ale sarpelui
    private int ypos = random.nextInt(23);

    private boolean gameOver = false;


    public GamePlay()
    {
        // Constructorul clasei GamePlay
        dreapta = new ImageIcon("src/rightmouth.png");
        stanga = new ImageIcon("src/leftmouth.png");
        sus = new ImageIcon("src/upmouth.png");
        jos = new ImageIcon("src/downmouth.png");
        snakebody = new ImageIcon("src/snakeimage.png");
        titleImage = new ImageIcon("src/snaketitle.jpg");
        mancare = new ImageIcon("src/enemy.png");
        addKeyListener(this); //Această linie adaugă un obiect KeyListener la instanța curentă a clasei GamePlay.
        // Un KeyListener este folosit pentru a asculta evenimentele de tastatură, cum ar fi apăsarea sau eliberarea unei taste.
        // Prin adăugarea clasei GamePlay ca ascultător pentru evenimente de tastatură, aceasta va primi notificări atunci când sunt efectuate acțiuni pe tastatură.
        setFocusable(true); //Această metodă face componenta (în acest caz, obiectul GamePlay) capabilă să primească focus.
        // Focusul este important în contextul tastaturii, deoarece doar componenta care are focusul va primi evenimente de la tastatură.
        // Prin setarea acestei proprietăți la true, se permite componentei să primească focus și să asculte evenimentele de la tastatură.
        setFocusTraversalKeysEnabled(true); //ceastă metodă activează sau dezactivează utilizarea tastelor de traversare a focusului.
        // Atunci când este setată la true, aceasta permite utilizarea tastelor de traversare a focusului pentru a naviga între diferite componente ce pot primi focus.
        timp = new Timer(delay, this); //Se creează un obiect Timer care va genera evenimente la intervale regulate.
        // Constructorul Timer primește două argumente: delay (întârzierea în milisecunde între evenimente) și this (referința către obiectul care va trata aceste evenimente, în acest caz, instanța curentă a clasei GamePlay).
        timp.start(); //se porneste timer ul, incepand sa genereze evenim la interv specificate
    }

    protected void paintComponent(Graphics g)  //se deseneaza toate elem grafice ale jocului
    {
        //metoda paintComponent este responsabilă pentru desenarea întregului aspect vizual al jocului, inclusiv șarpele și mâncarea.
        // Atunci când șarpele mănâncă mărul, aspectul vizual trebuie să reflecte acest lucru,
        // adăugând un nou segment la corpul șarpelui și actualizând scorul.
        if(moves == 0)
        { //coordonatele initiale ale sarpelui
            snakex[0]= 100;
            snakex[1] = 75;
            snakex[2] = 50;

            snakey[0] = 100;
            snakey[1] = 100;
            snakey[2] = 100;
        }
        if(!gameOver) {
            //desenam componentele grafice
            super.paintComponent(g);   //asigura ca orice desenare anterioara este stearsa, astfel încât să poată fi desenat conținutul actual.

            //border pt titlu:
            g.setColor(Color.WHITE);
            g.drawRect(24, 10, 851, 55);

            // Încarcă imaginea pentru titlu
            titleImage.paintIcon(this, g, 25, 11);

            //border pt joc:
            g.setColor(Color.WHITE);
            g.drawRect(24, 74, 851, 577);
            g.setColor(Color.BLACK);
            g.fillRect(25, 75, 850, 575);

            //draw the score
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.PLAIN, 14));
            g.drawString("Scor: " + score, 740, 30);
            g.drawString ("Lungime Sarpe: " +lungimeSarpe, 740, 50);

            //desenul sarpelui:
            //Se desenează capul șarpelui și, în funcție de direcția mișcării, se folosește o anumită imagine (dreapta, stanga, sus, jos) pentru a reprezenta capul în acea direcție.

            dreapta.paintIcon(this, g, snakex[0], snakey[0]);

            for (int i = 0; i < lungimeSarpe; i++) {
                if (i == 0 && right)  //verificam daca este capul, si miscarea pe care o face sarpele, sa stim cum desenam capul
                {
                    dreapta.paintIcon(this, g, snakex[i], snakey[i]);
                }
                if (i == 0 && left) {
                    stanga.paintIcon(this, g, snakex[i], snakey[i]);
                }
                if (i == 0 && up) {
                    sus.paintIcon(this, g, snakex[i], snakey[i]);
                }
                if (i == 0 && down) {
                    jos.paintIcon(this, g, snakex[i], snakey[i]);
                }

                if (i != 0)  //desenam segmentele corpului, folosind imaginea "snakebody"
                {
                    snakebody.paintIcon(this, g, snakex[i], snakey[i]);
                }
            }
            mancare.paintIcon(this, g, mancarex[xpos], mancarey[ypos]);  //se deseneaza mancarea

            if (mancarex[xpos] == snakex[0] && mancarey[ypos] == snakey[0]) //daca coordonatele capului sunt la fel cu cele ale marului
            {
                // am mancat marul
                lungimeSarpe++;  // lungimea sarpelui creste
                score++;  // avem cu un punct mai mult

                // generam noi coordonate pentru mancare
                xpos = random.nextInt(34);
                ypos = random.nextInt(23);
            }

            for (int i = 1; i < lungimeSarpe; i++)  //daca sarpele "si-a mancat coada"
            {
                if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
                    right = false;
                    left = false;
                    up = false;
                    down = false;

                    g.setColor(Color.WHITE);
                    g.setFont(new Font("arial", Font.BOLD, 50));
                    g.drawString("Game Over!!!", 300, 300);
                    g.setFont(new Font("arial", Font.BOLD, 20));
                    g.drawString("Space to Restart", 400, 400);
                    gameOver = true; // am terminat jocul

                }
            }

                if (snakex[0] >= 870 || snakex[0] <= 15 || snakey[0] < 80 || snakey[0] >= 640)
                {
                    right = false;
                    left = false;
                    up = false;
                    down = false;

                    g.setColor(Color.WHITE);
                    g.setFont(new Font("arial", Font.BOLD, 50));
                    g.drawString("Game Over!!!", 300, 300);
                    g.setFont(new Font("arial", Font.BOLD, 20));
                    g.drawString("Space to Restart", 400, 400);
                    gameOver = true; // am terminat jocul
                }

        }
    }

    // Adaugă această metodă pentru a verifica dacă mâncarea se află în zona specificată (nu unde ar putea da GameOver)
     int isFoodInRestrictedArea(int foodX, int foodY)
    {
        if (foodX < 200 && foodX > 800 && foodY > 350 && foodY < 550)
        {
            return 0;
        }
        return 1;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Verifica coliziunea cu marul inainte de a actualiza pozitia sarpelui
        if (mancarex[xpos] == snakex[0] && mancarey[ypos] == snakey[0])
        {
            // Am mancat marul
            lungimeSarpe++;  // Lungimea sarpelui creste
            score++;         // Avem cu un punct mai mult
            // Adaug noi coordonate pentru mancare
            do {
                xpos = random.nextInt(34);
                ypos = random.nextInt(23);
            } while (isFoodInRestrictedArea(xpos, ypos)!=0);

        }
        //ce se intampla daca ne mutam la draepta? trebuie mutat capu; apoi tot corpul o "casuta" mai in colo, ultima "casuta", acolo unde e "coada" (adica ultimul segment din corp) ramanand goala, sau de culoarea background ului, in cazul nostru, neagra
        if(right)
        {
            for(int i= lungimeSarpe-1; i>=0; i--)
            {
                snakey[i+1] = snakey[i];
            }
            for(int i=lungimeSarpe; i>=0; i--)
            {
                if(i==0)
                {
                    snakex[i]= snakex[i]+25;
                }
                else
                {
                    snakex[i]=snakex[i-1];
                }
//                if(snakex[i]>850)
//                {
//                    snakex[i] = 25;
//                }
            }
            repaint();
        }
        //analog procedam si pentru stanga:
        if(left)
        {
            for(int i= lungimeSarpe-1; i>=0; i--)
            {
                snakey[i+1] = snakey[i];
            }
            for(int i=lungimeSarpe; i>=0; i--)
            {
                if(i==0)
                {
                    snakex[i]= snakex[i]-25;
                }
                else
                {
                    snakex[i]=snakex[i-1];
                }
//                if(snakex[i]<25)
//                {
//                    snakex[i] = 850;
//                }
            }
            repaint();
        }
        //sus:
        if(up)
        {
            for(int i= lungimeSarpe-1; i>=0; i--)
            {
                snakex[i+1] = snakex[i];
            }
            for(int i=lungimeSarpe; i>=0; i--)
            {
                if(i==0)
                {
                    snakey[i]= snakey[i]-25;
                }
                else
                {
                    snakey[i]=snakey[i-1];
                }
//                if(snakey[i]<75)
//                {
//                    snakey[i] = 625;
//                }
            }
            repaint();
        }
        if(down)
        {
            for(int i= lungimeSarpe-1; i>=0; i--)
            {
                snakex[i+1] = snakex[i];
            }
            for(int i=lungimeSarpe; i>=0; i--)
            {
                if(i==0)
                {
                    snakey[i]= snakey[i]+25;
                }
                else
                {
                    snakey[i]=snakey[i-1];
                }
//                if(snakey[i]>625)
//                {
//                    snakey[i] = 75;
//                }
            }
            repaint();
        }


    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                gameOver = false;
                lungimeSarpe = 3;
                moves = 0;
                score = 0;
                right = true;  // Inițializăm direcția spre dreapta pentru a începe jocul.
                left = false;
                up = false;
                down = false;
                repaint();
            }
        } else {
            moves++;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!left) {
                    right = true;
                    up = false;
                    down = false;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!right) {
                    left = true;
                    up = false;
                    down = false;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (!down) {
                    up = true;
                    left = false;
                    right = false;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (!up) {
                    down = true;
                    left = false;
                    right = false;
                }
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
