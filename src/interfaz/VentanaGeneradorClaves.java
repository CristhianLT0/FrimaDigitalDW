package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import modelo.Controlador;

/**
 * Interfaz con las funciones de generar un par de claves RSA y exportar una clave publica
 *
 */
@SuppressWarnings("serial")
public class VentanaGeneradorClaves extends JFrame implements ActionListener{

	public static final String GENERAR_CLAVES = "GENERAR HASH";
	public static final String EXPORTAR_CLAVE_PUBLICA = "EXPORTAR CLAVE PUBLICA";
	
	private JPasswordField passFieldContrasena;
	private JPasswordField passFieldIngreseNuevamenteContrasena;
	
	private Inicio inicio;

	public VentanaGeneradorClaves(Inicio inicio) 
	{
		this.inicio = inicio;
		
		setTitle("CLAVES - HASH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 450);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labContrasena = new JLabel("Ingrese Clave Privada");
		labContrasena.setBounds(30, 35, 261, 14);
		contentPane.add(labContrasena);
		
		JLabel labConfirmarContrasena = new JLabel("Comprobar Clave Privada");
		labConfirmarContrasena.setBounds(30, 72, 261, 14);
		contentPane.add(labConfirmarContrasena);
		
		//----------------------------------------------------------------
		JButton butGenerarClaves = new JButton("GENERAR RSA");
		butGenerarClaves.setBounds(125, 150, 250, 50);
		contentPane.add(butGenerarClaves);
		butGenerarClaves.setActionCommand(GENERAR_CLAVES);
		butGenerarClaves.addActionListener((ActionListener) this);
		//----------------------------------------------------------------
		
		passFieldContrasena = new JPasswordField();
		passFieldContrasena.setBounds(301, 32, 153, 20);
		contentPane.add(passFieldContrasena);
		
		passFieldIngreseNuevamenteContrasena = new JPasswordField();
		passFieldIngreseNuevamenteContrasena.setBounds(301, 69, 153, 20);
		contentPane.add(passFieldIngreseNuevamenteContrasena);
		
		//----------------------------------------------------------------
		JButton butExportarClavePublica = new JButton("EXPORTAR LLAVE PUBLICA");
		butExportarClavePublica.setBounds(125, 220, 250, 75);
		contentPane.add(butExportarClavePublica);
		butExportarClavePublica.setActionCommand(EXPORTAR_CLAVE_PUBLICA);
		butExportarClavePublica.addActionListener((ActionListener) this);
		//----------------------------------------------------------------
		
		
		JButton butIrAFirma = new JButton("MENU PRINCIPAL");
		butIrAFirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inicio.setVisible(true);
				dispose();
				
			}
		});
		butIrAFirma.setBounds(125, 320, 250, 75);
		contentPane.add(butIrAFirma);
	}


	public void generarClaves() 
	{
		Controlador controlador = inicio.getControlador();
		
		String pass1 = String.valueOf(passFieldContrasena.getPassword());
		String pass2 = String.valueOf(passFieldIngreseNuevamenteContrasena.getPassword());
		
		if(pass1.equals("") || pass2.equals(""))
		{
			JOptionPane.showMessageDialog(this, "El password no puede ser vacio","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if(pass1.equals(pass2)==false)
			{
				JOptionPane.showMessageDialog(this, "No coindice el password","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				controlador.generarClave(pass1);
				JOptionPane.showMessageDialog(this, "Se generaron correctamente las claves","Respuesta",JOptionPane.INFORMATION_MESSAGE);
				
				passFieldContrasena.setText("");
				passFieldIngreseNuevamenteContrasena.setText("");
			}
		}
	}
	
	
	public void exportarClavePublica()
	{
		Controlador controlador = inicio.getControlador();
		
		//paso 1: obtener identificador (password) de la clave
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Digite el password:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancelar"};
		int option = JOptionPane.showOptionDialog(null, panel, "Clave",JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[1]);
		if(option == 0) 
		{
		    String password = new String(pass.getPassword());
		    
		    
		    JOptionPane.showMessageDialog(this, "Seleccione donde quiere guardar el archivo");
				
			//paso 2: seleccionar nombre de archivo para guardar
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Guardar archivo");   
			 
			int seleccion = fc.showSaveDialog(this);
			 
			if (seleccion == JFileChooser.APPROVE_OPTION) 
			{
			    File fichero = fc.getSelectedFile();
			    String rutaClavePublica = fichero.getAbsolutePath();
			   
			    try 
			    {
					controlador.exportarClavePublica(rutaClavePublica, password);
					JOptionPane.showMessageDialog(this, "Clave exportada exitosamente","Respuesta",JOptionPane.INFORMATION_MESSAGE);
				} 
			    catch (Exception e) 
			    {
					
			    		JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			    
			}    
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		
		if(comando.equals(GENERAR_CLAVES))
		{	
			generarClaves();	
		}
		else if(comando.equals(EXPORTAR_CLAVE_PUBLICA))
		{
			exportarClavePublica();
		}
		
	}
}