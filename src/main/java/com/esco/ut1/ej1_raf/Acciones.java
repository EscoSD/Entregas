/*1. Realiza una aplicación que genere un fichero de acceso directo con registros de una determinada entidad.
La aplicación debe permitir, al menos, generar altas y consultas.*/

package com.esco.ut1.ej1_raf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Acciones {

	public static void insert(RandomAccessFile raf, Doujinshi dj) {

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("duplicados.dat", true))) {

			raf.seek((long) (dj.getCode() - 1) * Doujinshi.DOUJIN_SIZE);

			if (raf.readInt()!=0) {
				dos.writeInt(dj.getCode());
				dos.writeUTF(dj.getName());
				dos.writeInt(dj.getPages());
				dos.writeDouble(dj.getPrize());
			} else {
				raf.seek((long) (dj.getCode() - 1) * Doujinshi.DOUJIN_SIZE);

				raf.writeInt(dj.getCode());
				raf.writeUTF(dj.getName());
				raf.writeInt(dj.getPages());
				raf.writeDouble(dj.getPrize());
			}

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public static Doujinshi[] search(RandomAccessFile raf, int searchCode) {

		Doujinshi[] array = new Doujinshi[0];
		ArrayList<Doujinshi> list = new ArrayList<>();

		try (DataInputStream dis = new DataInputStream(new FileInputStream("duplicados.dat"))) {

			raf.seek((long) (searchCode - 1) * Doujinshi.DOUJIN_SIZE);
			int code = raf.readInt();

			if (code == searchCode) {

				String name = raf.readUTF();
				int pages = raf.readInt();
				double prize = raf.readDouble();

				list.add(new Doujinshi(code, name, pages, prize));
			}

			dupesCheck(dis, list, searchCode);

			array = new Doujinshi[list.size()];

			int i = 0;
			for (Doujinshi aux : list) {
				array[i] = aux;
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

		return array;
	}

	private static void dupesCheck(DataInputStream dis, ArrayList<Doujinshi> list, int id) {

		int code;
		try {
			while ((code = dis.readInt()) != -1)
				if (code == id)
					list.add(new Doujinshi(
							id,
							dis.readUTF(),
							dis.readInt(),
							dis.readDouble()
					));
		} catch (EOFException ignored) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
