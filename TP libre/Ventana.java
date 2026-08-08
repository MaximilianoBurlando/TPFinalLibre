import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private Juego juego;

    private JPanel panel;

    private Timer timer;


    public Ventana(Juego juego){

        this.juego = juego;


        setTitle("Juego de la Vida");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g){

                super.paintComponent(g);

                dibujarTablero(g);

            }

        };


        add(panel);


        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton step = new JButton("Step");


        start.addActionListener(e -> juego.iniciar());

        stop.addActionListener(e -> juego.detener());


        step.addActionListener(e -> {

            juego.paso();
            panel.repaint();

        });


        JPanel controles = new JPanel();

        controles.add(start);
        controles.add(stop);
        controles.add(step);


        add(controles, BorderLayout.SOUTH);


        timer = new Timer(
            juego.getVelocidad(),
            e -> {

                if(juego.estaCorriendo()){

                    juego.paso();

                    panel.repaint();

                }

            }
        );


        timer.start();

    }


    private void dibujarTablero(Graphics g){

        Tablero t = juego.getTablero();


        int ancho = panel.getWidth();
        int alto = panel.getHeight();


        int filas = t.getFilas();
        int columnas = t.getColumnas();


        int tamX = ancho / columnas;
        int tamY = alto / filas;


        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){


                Celda c = t.getCelda(i,j);


                //obtenemos el simbolo de la celda
                char simbolo = c.getSimbolo();


                //dibujamos el simbolo
                g.drawString(
                    String.valueOf(simbolo),
                    j * tamX + tamX / 2,
                    i * tamY + tamY / 2
                );


                //dibujamos los bordes de cada celda
                g.drawRect(
                    j * tamX,
                    i * tamY,
                    tamX,
                    tamY
                );

            }

        }

    }

}