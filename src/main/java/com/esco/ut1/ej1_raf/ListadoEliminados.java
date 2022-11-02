package com.esco.ut1.ej1_raf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ListadoEliminados extends JFrame {

	public Doujinshi[] selection;
	private final JList<Doujinshi> list;
	private final RandomAccessFile raf;

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

	private void buttonActionPerformed(ActionEvent e) {

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
			this.dispose();
		}

	}

	private void borrar() {

		File duplicados = new File("duplicados.dat");
		File temporal = new File("temporal.dat");

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
				manejoDatos(0, temporal, duplicados);


			if (selection.length > 1)
				for (int i = 1; i < selection.length; i++)
					manejoDatos(i, temporal, duplicados);

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

	}

	@SuppressWarnings("InfiniteLoopStatement")
	private void manejoDatos(int pos, File temporal, File duplicados) throws IOException {

		try (
				DataInputStream dis = new DataInputStream(new FileInputStream(duplicados));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(temporal))
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

		} catch (EOFException e) {
			duplicados.delete();
			temporal.renameTo(duplicados);
		}
	}
}
