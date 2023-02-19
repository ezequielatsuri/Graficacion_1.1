package graficacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Figuras extends JFrame {

	private JPanel contentPane;
	private String[] opciones = {"Pentágono", "Hexágono"};
	private String[] colores = {"RBG", "RGB a CMY","RGB a HSV ", "RGB a HSL"};
	private JButton crearFigura = new JButton("Crear");
	private JTextField R;
	private final JLabel R1 = new JLabel("R");
	private final JLabel G1 = new JLabel("G");
	private final JTextField G = new JTextField();
	private final JLabel B1 = new JLabel("B");
	private final JTextField B = new JTextField();
	private final JButton pintar = new JButton("Pintar");
	private Color color;
	private final JLabel lblNewLabel = new JLabel("RGB");
	private final JLabel lblNewLabel_1 = new JLabel("CMY");
	private final JLabel lblNewLabel_2 = new JLabel("HSV ");
	private final JLabel lblNewLabel_3 = new JLabel("HSL");

	//variables de colores 
	int r=0,g1=0,b=0;//variables del modelo RGB
	float c=1,m=1,y1=1;//variables del modelo CMY
        float h1=0,s=0,v=0;//variables del modelo CMYHS
        float h2= 0,s1=0,l1 = 0;//variables del modelo HSL
	
        
        
        
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Figuras frame = new Figuras();
		frame.setVisible(true);
		
	}

	/**
	 * Create the frame.
	 */
	public Figuras() {
		//en esta parte inicializamos lo componentes que se declararon en la parte de arriba
		B.setBounds(460, 12, 39, 20);
		B.setColumns(10);
		G.setBounds(405, 12, 36, 20);
		G.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 509, 450);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//evento para mandar a llamar al metodo para crear la figura 2d 
		crearFigura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearRombo();
	        
			}
		});
		
		//definimos tamaños de los componentes
		
		crearFigura.setBounds(141, 11, 70, 23);
		panel.add(crearFigura);
		
		R = new JTextField();
		R.setBounds(338, 12, 47, 20);
		panel.add(R);
		R.setColumns(10);
		R1.setBounds(328, 15, 19, 14);
		
		panel.add(R1);
		G1.setBounds(395, 15, 19, 14);
		
		panel.add(G1);
		
		panel.add(G);
		B1.setBounds(450, 15, 25, 14);
		
		panel.add(B1);
		
		panel.add(B);
		
		// es el accion clic del boton que manda a llamar a la funcion para pintar las figuras segun la opcion escogida 
		pintar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r=Integer.parseInt(R.getText());
				g1=Integer.parseInt(G.getText());
				b=Integer.parseInt(B.getText());
				
				float [] CMY = RgbACMY(r,g1,b);
                                float [] HSV = RgbAHSV(r,g1,b);
                                float [] HSL = RgbAHSV(r,g1,b);
				
				c=CMY[0];m=CMY[1];y1=CMY[2];
                                h1 =HSV[0];s=HSV[1];v=HSV[2];
                                h2=HSL[0];s1= HSL[1];l1 = HSL[2];
                                
				CrearRombo();
			}
		});
		pintar.setBounds(368, 65, 89, 23);
		
		panel.add(pintar);
		lblNewLabel.setBounds(130, 45, 46, 14);
		
		panel.add(lblNewLabel);
		lblNewLabel_1.setBounds(245, 45, 46, 14);
		
		panel.add(lblNewLabel_1);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(112, 392, 46, 14);
		
		panel.add(lblNewLabel_2);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(245, 392, 46, 14);
		
		panel.add(lblNewLabel_3);
		
	}
	
	public void CrearRombo( ) {
		  Graphics g = getGraphics();
                  int x = 100, y = 100, w = 100, h = 150;
        /**
         *Siendo x la posición en el eje x y siendo y la posición en el eje y , donde aparecera nuestro rombo
         *h que representa la altura del rombo
         * w representa el ancho del rombo. 
         */
        
        // dibujar el primer rombo ->Modelo RGB
        color = new Color(r, g1, b);
        g.setColor(color);
        /**
         * Un rombo tiene 4 vértices y tenemos que asignar esos valores.
         * por esos se crean dos arreglos uno para las posiciones en x y otros para las posiciones en y
         * Como si fuera una matriz que el punto 0 en la posición x está relacionada a la posición 0 en y
         * pero se ocupan dos arreglos para usar el componente fillPolygon
         * El primer vértice en la posición parte superior del rombo esta dado por x,y+h/2.
         * El segundo vértice en la posición parte izquierda del rombo esta dado por x+w/2,y
         * El tercer vértice en la posición inferior esta dado por x + w,y + h/2
         * el cuarto vértice en la posición derecha esta dada por x + w/2,y + h
         * 
         * 
         */
        int[] xRomobo1 = {x, x + w/2, x + w, x + w/2};        
        int[] yRomobo1 = {y + h/2, y, y + h/2, y + h};
        /**
         * fillPolygon es un método de la clase Graphics en Java que se utiliza para dibujar un polígono relleno en un componente gráfico
         * Basicamente le pasamos los puntos en x  , y para que los una tambien pide  el numero de puntos de cada eje 
         * por ejemplo de un rombo son 4 puntos de x  y 4 puntos para y 
         * pero si quisieramos crear exagono tendriamos que pasar 6 puntos de cada eje
         */
        g.fillPolygon(xRomobo1, yRomobo1, 4);
        
        // dibujar el segundo rombo ->MOdelo CMY
        x += w + 20; // aumentar la coordenada x para separar los rombos -
       
        Color color1 = new Color(1.0f - c, 1.0f - m, 1.0f - y1);
        g.setColor(color1);
        
        int[] xRomobo2 = {x, x + w/2, x + w, x + w/2};
        int[] yRomobo2 = {y + h/2, y, y + h/2, y + h};
        g.fillPolygon(xRomobo2, yRomobo2, 4);
        
        // dibujar el tercer rombo->MOdelo HSv
        x = 100; // resetear la coordenada x , para que este a la parte del primero
        y += h + 20; // aumentar la coordenada y para separar los rombos
        g.setColor(Color.getHSBColor(h1,s,v));
        int[] xRomobo3 = {x, x + w/2, x + w, x + w/2};
        int[] yRomobo3 = {y + h/2, y, y + h/2, y + h};
        g.fillPolygon(xRomobo3, yRomobo3, 4);
        
        // dibujar el cuarto rombo ->MOdelo HSL
        x += w + 20; // aumentar la coordenada x para separar los rombos
        g.setColor(Color.getHSBColor(h2, s1, l1));
        int[] xRomobo4 = {x, x + w/2, x + w, x + w/2};
        int[] yRomobo4 = {y + h/2, y, y + h/2, y + h};
        g.fillPolygon(xRomobo4, yRomobo4, 4);
    }   
	//Es una función para convertir los valores de RGB a CMY, para esto pedimos de parametro los valores de R,G,B
	public float[] RgbACMY(int r, int g, int b) {
		// las siguientes 3 lineas de codigo nos permite saber si los valores guardados estan entre el rango de o a 255
		//si el valor es menor a 0 se establece como 0
		//si el valor esta arriba de 255 se establce como 255
		 r = Math.min(Math.max(r, 0), 255);
                 g = Math.min(Math.max(g, 0), 255);
                 b = Math.min(Math.max(b, 0), 255);

                Color color = new Color(r, g, b); // ejemplo de creación de color RGB
                float c = 1.0f - (float) color.getRed() / 255.0f; // valor de cian entre 0 y 1
                float m = 1.0f - (float) color.getGreen() / 255.0f; // valor de magenta entre 0 y 1
                float y = 1.0f - (float) color.getBlue() / 255.0f; // valor de amarillo entre 0 y 1

	    return new float[] {c, m, y};
		
	}
        
        public float[] RgbAHSV(int r, int g, int b) {
		// las siguientes 3 lineas de codigo nos permite saber si los valores guardados estan entre el rango de o a 255
		//si el valor es menor a 0 se establece como 0
		//si el valor esta arriba de 255 se establce como 255
		Color color = new Color(r, g, b); // ejemplo de creación de color RGB
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                float h = hsb[0]; // valor de matiz entre 0 y 1
                float s = hsb[1]; // valor de saturación entre 0 y 1
                float bri = hsb[2]; // valor de brillo entre 0 y 1

	    return new float[] {h, s, bri};
		
	}
        //aaaaaaaaaaaaaaaaa
        
        public float[] RgbAHSL(int r, int g, int b) {
		// las siguientes 3 lineas de codigo nos permite saber si los valores guardados estan entre el rango de o a 255
		//si el valor es menor a 0 se establece como 0
		//si el valor esta arriba de 255 se establce como 255
		Color color = new Color(r, g, b); // ejemplo de creación de color RGB
               float[] hsl = rgbToHsl(color.getRed(), color.getGreen(), color.getBlue());
                float hu = hsl[0]; // valor de matiz entre 0 y 1
                float sa = hsl[1]; // valor de saturación entre 0 y 1
                float lu = hsl[2]; // valor de luminosidad entre 0 y 1

	    return new float[] {hu, sa, lu};
		
	}
        
        public static float[] rgbToHsl(int r, int g, int b) {//METODO PARA CONVERTIR DEL MODELO RGB A HSL
    float red = r / 255.0f;
    float green = g / 255.0f;
    float blue = b / 255.0f;
    
    float max = Math.max(Math.max(red, green), blue);
    float min = Math.min(Math.min(red, green), blue);
    float delta = max - min;
    
    float hue = 0;
    if (delta != 0) {
        if (max == red) {
            hue = ((green - blue) / delta) % 6;
        } else if (max == green) {
            hue = ((blue - red) / delta) + 2;
        } else if (max == blue) {
            hue = ((red - green) / delta) + 4;
        }
        hue /= 6;
        if (hue < 0) {
            hue += 1;
        }
    }
    
    float luminance = (max + min) / 2;
    
    float saturation = 0;
    if (delta != 0) {
        saturation = delta / (1 - Math.abs(2 * luminance - 1));
    }
    
    return new float[] {hue, saturation, luminance};
}
    	
}
