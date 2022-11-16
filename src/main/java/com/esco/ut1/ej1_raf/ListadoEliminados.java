package com.esco.ut1.ej1_raf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ListadoEliminados extends JFrame {

	public Doujinshi[] selection;	// Array que contendrá los elementos seleccionados por el usuario.
	private final JList<Doujinshi> list;
	private final RandomAccessFile raf;

	/**
	 * Constructor de la lista que mostrará los Doujinshi a eliminar.
	 * @param array Array contenedor de los datos obtenidos.
	 * @param raf RandomAccessFile ya creado para evitar problemas.
	 */
	public ListadoEliminados(Doujinshi[] array, RandomAccessFile raf) {

		this.raf = raf;
		JPanel jp = new JPanel(new GridLayout(3, 1));
		add(jp);

		JLabel label = new JLabel(" i.- Puedes seleccionar varias opciones con Ctrl + Click ");
		list = new JList<>(array);
		JButton button = new JButton("Eliminar");

		jp.add(label);
		jp.add(list);
		jp.add(button);

		button.addActionListener(this::buttonActionPerformed);

		setLocationRelativeTo(null);
		pack();
		setVisible(true);

	}

	/**
	 * Método que gestiona las acciones del botón de eliminar.
	 * @param e	ActionEvent generado por la pulsación del botón.
	 */
	private void buttonActionPerformed(ActionEvent e) {

		// Si se pulsa el botón de eliminar sin seleccionar ningún valor, saltará una ventana de advertencia.
		if (list.getSelectedIndices().length == 0) {
			JOptionPane jop = new JOptionPane("-Selecciona algún Doujin por favor.", JOptionPane.WARNING_MESSAGE);
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);

		} else {
			selection = list.getSelectedValuesList().toArray(new Doujinshi[0]);
			borrar();

			JOptionPane jop = new JOptionPane("-Eliminado con éxito.");
			JDialog dialog = jop.createDialog("Doujinshi");
			dialog.setVisible(true);

			// Método que cierra la ventana una vez completado el borrado.
			this.dispose();
		}

	}

	/**
	 * Método que se encarga de borrar datos del área normal
	 * o comprobar si los datos a borrar pertenecen al área de sinónimos.
	 */
	private void borrar() {

		try {

			int searchCode = selection[0].getCode();
			raf.seek((long) (searchCode - 1) * Doujinshi.DOUJIN_SIZE);
			raf.readInt();
			String nombre = raf.readUTF();

			if (nombre.equals(selection[0].getName())) {
				raf.seek((long) (searchCode - 1) * Doujinshi.DOUJIN_SIZE);

				raf.writeInt(0);
				raf.writeUTF("None");
				raf.writeInt(0);
				raf.writeDouble(0);
			} else
				manejoDatos(0);


			if (selection.length > 1)
				for (int i = 1; i < selection.length; i++)
					manejoDatos(i);

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

	}

	/**
	 * Método que gestiona el borrado del área de sinónimos.
	 * Se copian todos los elementos del fichero a un fichero adicional salvo aquel a borrar,
	 * después se borra el fichero original y se renombra el nuevo fichero.
	 * @param pos Posición del elemento a borrar.
	 * @throws IOException Si ocurre cualquier IOException
	 */
	@SuppressWarnings("InfiniteLoopStatement")
	private void manejoDatos(int pos) throws IOException {

		File archivoViejo = new File("duplicados.dat");
		File archivoNuevo = new File("temporal.dat");

		try (
				DataInputStream dis = new DataInputStream(new FileInputStream(archivoViejo));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoNuevo))
		) {
			int code, pages;
			String name;
			double prize;

			while (true) {
				code = dis.readInt();
				name = dis.readUTF();
				pages = dis.readInt();
				prize = dis.readDouble();

				if (!name.equals(selection[pos].getName())) {
					dos.writeInt(code);
					dos.writeUTF(name);
					dos.writeInt(pages);
					dos.writeDouble(prize);
				}
			}

		} catch (EOFException e) {	// Cuando se termina la copia. Borramos el fichero y renombramos.
			archivoViejo.delete();
			archivoNuevo.renameTo(archivoViejo);
		}
	}
}
