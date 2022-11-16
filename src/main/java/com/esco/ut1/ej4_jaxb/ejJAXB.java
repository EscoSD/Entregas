package com.esco.ut1.ej4_jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ejJAXB {
	public static void main(String[] args) {

		escrituraJAXB();
		lecturaJAXB();
	}

	public static void escrituraJAXB() {

		int code;
		ArrayList<Doujinshi> list = new ArrayList<>();

		try (RandomAccessFile raf = new RandomAccessFile("RegistroD.dat", "rw")) {
			// Rellenamos un ArrayList con todos los objetos Doujin almacenados en el RAF.
			for (int i = 1; i <= 100; i++) {
				raf.seek((i - 1) * 50);

				code = raf.readInt();
				Doujinshi doujin;

				if (code != 0) {
					doujin = new Doujinshi(code, raf.readUTF(), raf.readInt(), raf.readDouble());
					list.add(doujin);
				}

			}

			escritura(list);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void escritura(ArrayList<Doujinshi> array) {
		try {
			Doujinshis list = new Doujinshis(array);

			JAXBContext contexto = JAXBContext.newInstance(
					list.getClass());
			Marshaller marshaller = contexto.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					true);

			marshaller.marshal(list, new File("Doujinshis_JAXB.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void lecturaJAXB() {

		try {
			JAXBContext context = JAXBContext.newInstance(Doujinshis.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			Doujinshis list = (Doujinshis) unmarshaller.unmarshal(new File("Doujinshis_JAXB.xml"));

			for (Doujinshi doujin : list.list)
				System.out.println(doujin);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
