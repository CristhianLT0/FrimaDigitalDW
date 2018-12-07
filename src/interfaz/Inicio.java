package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Controlador;

/**
 * Ventana inicial del programa
 */
@SuppressWarnings("serial")
public class Inicio extends JFrame {
	
	
	private Controlador controlador;
	
	public Inicio() 
	{
		controlador = new Controlador();
		
		setTitle("DESARROLLO WEB - FIRMA DIGITAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 675, 400);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLayout(null);
		
		
		VentanaGeneradorClaves generadorClaves = new VentanaGeneradorClaves(this);
		VentanaFirmador firmador = new VentanaFirmador(this);
		VentanaVerificador comprobador = new VentanaVerificador(this);
                /*VentanaSalir salir =new VentanaSalir (this);*/
		
		
		JButton butGeneradorClaves = new JButton("CLAVES - HASH");
		butGeneradorClaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generadorClaves.setVisible(true);
				dispose();
			}
		});
		butGeneradorClaves.setBounds(50, 70, 255, 100);
		contentPane.add(butGeneradorClaves);
		
		JButton butFirmador = new JButton("FIRMAR DOCUMENTO");
		butFirmador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firmador.setVisible(true);
				dispose();
			}
		});
		butFirmador.setBounds(350, 70, 255, 100);
		contentPane.add(butFirmador);
		
		JButton butComprobador = new JButton("COMPROBAR FIRMA");
		butComprobador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprobador.setVisible(true);
				dispose();
			}
		});
		butComprobador.setBounds(200, 210, 255, 100);
		contentPane.add(butComprobador);
	}
	
	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public static void main(String[] args) 
	{
		Inicio window = new Inicio();
		window.setVisible(true);

	}

}