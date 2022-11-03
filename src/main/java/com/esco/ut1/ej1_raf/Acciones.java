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

	/**
	 * Método que se encarga de almacenar la información, tanto en el área normal como el área de sinónimos.
	 * @param raf Un RandomAccessFile ya creado, para evitar problemas al manejar un fichero.
	 * @param dj El objeto Doujinshi que contiene la información a almacenar.
	 */
	public static void insert(RandomAccessFile raf, Doujinshi dj) {

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("duplicados.dat", true))) {

			raf.seek((long) (dj.getCode() - 1) * Doujinshi.DOUJIN_SIZE);

			if (raf.readInt() != 0) {
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

	/**
	 * Método que acepta un código y devuelve un array con todos los Doujinshis almacenados con ese código.
	 * @param raf RandomAccessFile previamente creado.
	 * @param searchCode Código del Doujinshi a buscar.
	 * @return El array contenedor de todos los Doujinshi con mismo código encontrados.
	 */
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

			obtencionDuplicados(dis, list, searchCode);

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

	/**
	 * Método que rellena un ArrayList con los Doujinshi de mismo código almacenados en el área de duplicados.
	 * @param dis DataInputStream que recorre el área de duplicados.
	 * @param list ArrayList que almacena los Doujinshis con el código deseado.
	 * @param id Código de los Doujinshis que se quieren obtener.
	 */
	private static void obtencionDuplicados(DataInputStream dis, ArrayList<Doujinshi> list, int id) {

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
