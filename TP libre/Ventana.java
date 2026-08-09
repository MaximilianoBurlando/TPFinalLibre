import javax.swing.*;//importamos clases de Swing (lo que Java utiliza para crear interfaces gráficas)
import java.awt.*;//clases relacionadas con gráficos y componentes visuales

public class Ventana extends JFrame {//ventana hereda de JFrame
    //la instancia del juego, pemite que lo pare, inicie, de 1 paso o cambie la velocidad desde interfaz
    private Juego juego;
    //representa el panel donde se va a "dibujar" el tablero
    private JPanel panel;
    //el temporalizador que hace que avance automáticamente cada determinada cantidad de milisegundos
    private Timer timer;

    public Ventana(Juego juego){//contructor, recibe la instancia de juego 

        this.juego = juego;//instancia de jeugo


        setTitle("Juego de la Vida");//establece el título de la ventana
        setSize(600,600);//magnitud/resolucion de ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//accion cuando el usuario cierra la ventana


        panel = new JPanel(){//clase anónima que hereda de JPanel

            @Override//sobrescribiendo metodo heredado
            //método que Swing utiliza para dibujar el contenido del panel
            protected void paintComponent(Graphics g/*objeto que permite realizar los dibujos.*/){

                super.paintComponent(g);//esto limpia/prepara correctamente el panel antes de dibujar

                dibujarTablero(g);//llamás a tu propio método para dibujar el tablero

            }

        };


        add(panel);//agrega el panel a la ventana

        //botones
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton step = new JButton("Step");

        //agregás un listener a los botones (un listener es un mecanismo que permite detectar eventos)
        //'e ->'' (representa la acción que se ejecutará cuando ocurra el evento)
        start.addActionListener(e -> juego.iniciar());//inicia el juego

        stop.addActionListener(e -> juego.detener());//detiene el juego
        
        step.addActionListener(e -> {//calcula siguiente generacion

            juego.paso();
            panel.repaint();//indica a Swing que el panel debe volver a dibujarse
            //provoca que posteriormente Swing vuelva a ejecutar
        });
        //crear otro panel
        JPanel controles = new JPanel();
        //agregamos los botones al panel
        controles.add(start);
        controles.add(stop);
        controles.add(step);

        // control de velocidad
        JSlider velocidad = new JSlider(100, 2000, juego.getVelocidad());
        //establece cada cuánto aparecen las marcas principales
        velocidad.setMajorTickSpacing(500);
        velocidad.setMinorTickSpacing(100);
        //hace visibles las marcas del slider
        velocidad.setPaintTicks(true);
        //hace visibles los números
        velocidad.setPaintLabels(true);
        //agrega una etiqueta
        controles.add(new JLabel("Velocidad:"));
        //añade slider al panel de controles
        controles.add(velocidad);

        add(controles, BorderLayout.SOUTH);//agrega el panel de controles en la parte inferior de la ventana

        timer = new Timer(juego.getVelocidad(), e -> {//Timer de Swing

                if(juego.estaCorriendo()){//pregunta si el juego está actualmente ejecutándose

                    juego.paso();//calcula la siguiente generación

                    panel.repaint();//volver a dibujar

                }

            }
        );


        timer.start();//temporizador comienza a generar eventos (espera que 'juego.estaCorriendo()' devuelva true)

        velocidad.addChangeListener(e -> {//detecta cuando cambia el valor del deslizador

            int nuevaVelocidad = velocidad.getValue();//obtiene el valor actual del slider

            juego.setVelocidad(nuevaVelocidad);//ingresa nueva velocidad

            timer.setDelay(nuevaVelocidad);//nuevo delay

        });
    }


    private void dibujarTablero(Graphics g){//encargado exclusivamente de dibujar el tablero

        Tablero t = juego.getTablero();//obtiene el tablero que está dentro de Juego
        //ancho y alto del panel
        int ancho = panel.getWidth();
        int alto = panel.getHeight();

        //filas y columnas
        int filas = t.getFilas();
        int columnas = t.getColumnas();

        //tamaños de celda
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