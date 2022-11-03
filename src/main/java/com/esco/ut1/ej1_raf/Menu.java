/*1. Realiza una aplicación que genere un fichero de acceso directo con registros de una determinada entidad.
La aplicación debe permitir, al menos, generar altas y consultas.*/

package com.esco.ut1.ej1_raf;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author ciclosm
 */
public class Menu extends JFrame {

	private JTextField nameTextField;
	private JTextField codeTextField;
	private JTextField prizeTextField;
	private JTextField pagesTextField;
	private JTextField buscarTextField;
	private RandomAccessFile raf;
	private static final int SIZE_FICHERO = 5000;   // TAMAÑO QUE TENDRÁ EL FICHERO, CAPACIDAD PARA 100 DOUJINS.

	/**
	 * Creates new form Menu
	 */
	public Menu() {
		super("Doujinshi");
		initComponents();
	}

	/**
	 * Conformado mayormente por código autogenerado de Netbeans.
	 * Inicializa los componentes de la aplicación.
	 */
	private void initComponents() {

		JPanel altaPanel = new JPanel();
		JLabel codeLabel = new JLabel();
		JLabel nameLabel = new JLabel();
		nameTextField = new JTextField();
		codeTextField = new JTextField();
		JLabel pagesLabel = new JLabel();
		JLabel prizeLabel = new JLabel();
		prizeTextField = new JTextField();
		pagesTextField = new JTextField();
		JButton inputButton = new JButton();
		// Variables declaration - do not modify
		JLabel altaLabel = new JLabel();
		JPanel buscar_borrarPanel = new JPanel();
		JLabel buscarCodeLabel = new JLabel();
		buscarTextField = new JTextField();
		JLabel buscar_borrarLabel = new JLabel();
		JButton buscarButton = new JButton();
		JButton borrarButton = new JButton();

		try {
			raf = new RandomAccessFile("registroD.dat", "rw");  // creación del RAF y asignación de tamaño al fichero registroD.dat
			raf.setLength(SIZE_FICHERO);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		altaPanel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		codeLabel.setText("CÓDIGO");

		nameLabel.setText("NOMBRE");

		pagesLabel.setText("PÁGINAS");

		prizeLabel.setText("PRECIO");

		prizeTextField.setMinimumSize(new Dimension(70, 23));
		prizeTextField.setPreferredSize(new Dimension(70, 23));

		pagesTextField.setMinimumSize(new Dimension(70, 23));
		pagesTextField.setPreferredSize(new Dimension(70, 23));

		inputButton.setText("AÑADIR");
		inputButton.addActionListener(this::inputButtonActionPerformed);

		altaLabel.setFont(new Font("Liberation Sans", Font.BOLD, 14));
		altaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		altaLabel.setText("ALTA DE DOUJINSHI");

		GroupLayout altaPanelLayout = new GroupLayout(altaPanel);
		altaPanel.setLayout(altaPanelLayout);
		altaPanelLayout.setHorizontalGroup(
				altaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, altaPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(altaPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addGroup(altaPanelLayout.createSequentialGroup()
												.addComponent(pagesLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(prizeLabel))
										.addGroup(altaPanelLayout.createSequentialGroup()
												.addGap(0, 0, Short.MAX_VALUE)
												.addGroup(altaPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
														.addGroup(altaPanelLayout.createSequentialGroup()
																.addComponent(pagesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(prizeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(GroupLayout.Alignment.LEADING, altaPanelLayout.createSequentialGroup()
																.addComponent(codeLabel)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(nameLabel))
														.addGroup(GroupLayout.Alignment.LEADING, altaPanelLayout.createSequentialGroup()
																.addComponent(codeTextField, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))))
								.addGap(33, 33, 33))
						.addGroup(altaPanelLayout.createSequentialGroup()
								.addGap(65, 65, 65)
								.addComponent(inputButton)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(altaPanelLayout.createSequentialGroup()
								.addComponent(altaLabel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))
		);
		altaPanelLayout.setVerticalGroup(
				altaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, altaPanelLayout.createSequentialGroup()
								.addContainerGap(7, Short.MAX_VALUE)
								.addComponent(altaLabel)
								.addGap(18, 18, 18)
								.addGroup(altaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(codeLabel)
										.addComponent(nameLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(altaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(codeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(altaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(pagesLabel)
										.addComponent(prizeLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(altaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(prizeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(pagesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(inputButton)
								.addGap(17, 17, 17))
		);

		buscar_borrarPanel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		buscarCodeLabel.setText("CÓDIGO");

		buscarTextField.setMinimumSize(new Dimension(70, 23));
		buscarTextField.setPreferredSize(new Dimension(70, 23));

		buscar_borrarLabel.setFont(new Font("Liberation Sans", Font.BOLD, 13)); // NOI18N
		buscar_borrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buscar_borrarLabel.setText("BUSCAR / BORRAR");

		buscarButton.setText("BUSCAR");
		buscarButton.addActionListener(this::buscarButtonActionPerformed);

		borrarButton.setText("BORRAR");
		borrarButton.addActionListener(this::borrarButtonActionPerformed);

		GroupLayout buscar_borrarPanelLayout = new GroupLayout(buscar_borrarPanel);
		buscar_borrarPanel.setLayout(buscar_borrarPanelLayout);
		buscar_borrarPanelLayout.setHorizontalGroup(
				buscar_borrarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(buscar_borrarPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(buscar_borrarLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(GroupLayout.Alignment.TRAILING, buscar_borrarPanelLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(buscar_borrarPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(buscarButton)
										.addComponent(buscarCodeLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(buscar_borrarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(buscarTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(borrarButton))
								.addGap(27, 27, 27))
		);
		buscar_borrarPanelLayout.setVerticalGroup(
				buscar_borrarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, buscar_borrarPanelLayout.createSequentialGroup()
								.addComponent(buscar_borrarLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(buscar_borrarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(buscarTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(buscarCodeLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(buscar_borrarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(buscarButton)
										.addComponent(borrarButton))
								.addContainerGap(18, Short.MAX_VALUE))
		);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(altaPanel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addComponent(buscar_borrarPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(altaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(buscar_borrarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		pack();

		setLocationRelativeTo(null);
	}// </editor-fold>

	/**
	 * Método que se activa al presionar el botón de Búsqueda,
	 * generará una ventana que muestre los datos encontrados.
	 * En caso de no encontrar datos, avisará con una ventana.
	 *
	 * @param evt El ActionEvent que genera la pulsación del botón.
	 */
	private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			int id = Integer.parseInt(buscarTextField.getText());

			Doujinshi[] array = Acciones.search(raf, id);
			StringBuilder superString = new StringBuilder("No se ha encontrado");

			if (array.length > 0) {
				superString = new StringBuilder();
				for (Doujinshi dj : array)
					superString.append(dj.toString()).append("\n");
			}

			JOptionPane jop = new JOptionPane(superString.toString());
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);

		} catch (NumberFormatException e) {
			JOptionPane jop = new JOptionPane("-El campo CÓDIGO debe ser un número entero.", JOptionPane.WARNING_MESSAGE);
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);
		}
	}

	/**
	 * Método que se activa al presionar el botón de Borrar,
	 * generará una ventana con un listado que contenga la información encontrada y disponible para eliminar.
	 * En caso de no encontrar datos, avisará con una ventana.
	 *
	 * @param evt El ActionEvent que genera la pulsación del botón.
	 */
	private void borrarButtonActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			int id = Integer.parseInt(buscarTextField.getText());

			Doujinshi[] array = Acciones.search(raf, id);
			String str = "No se ha encontrado.";

			if (array != null) {

				new ListadoEliminados(array, raf);

			} else {
				JOptionPane jop = new JOptionPane(str);
				JDialog dialog = jop.createDialog("Doujinshi");
				dialog.setVisible(true);
			}

		} catch (NumberFormatException e) {
			JOptionPane jop = new JOptionPane("-El campo CÓDIGO debe ser un número entero.", JOptionPane.WARNING_MESSAGE);
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);
		}
	}

	/**
	 * Método que se activa al presionar el botón de Añadir,
	 * se introducirán los datos al fichero, si la información es correcta.
	 * En caso de introducir datos con formato incorrecto, se lanzará una ventana de advertencia.
	 *
	 * @param evt El ActionEvent que genera la pulsación del botón.
	 */
	private void inputButtonActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			int code = Integer.parseInt(codeTextField.getText());

			String name = nameTextField.getText();
			name = name.substring(0, (Math.min(32, name.length())));

			if (code == 0) {
				JOptionPane jop = new JOptionPane("El código debe ser mayor que 0.");
				JDialog dialog = jop.createDialog("Doujinshi");
				dialog.setVisible(true);
				return;
			}

			Doujinshi dj = new Doujinshi(
					code,
					name,
					Integer.parseInt(pagesTextField.getText()),
					Double.parseDouble(prizeTextField.getText())
			);

			Acciones.insert(raf, dj);

			JOptionPane jop = new JOptionPane("Añadido con éxito.");
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);
		} catch (NumberFormatException e) {
			JOptionPane jop = new JOptionPane("-Los campos CÓDIGO y PÁGINAS deben ser números enteros.\n-El campo PRECIO debe ser un número real.", JOptionPane.WARNING_MESSAGE);
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);
		}
	}

	/**
	 * main autogenerado por NetBeans.
	 *
	 * @param args Los parámetros de inicialización del programa.
	 */
	public static void main(String[] args) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
				 UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		EventQueue.invokeLater(() -> new Menu().setVisible(true));

	}
}
