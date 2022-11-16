/*2. Realiza una aplicación, haciendo uso de las clases de NIO, incluyendo buffers y canales,
para hacer una copia del fichero de acceso directo generado en la anterior aplicación.*/

package com.esco.ut1.ej2_nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ejNIO {

	public static void main(String[] args) {

		FileChannel canalIn;
		FileChannel canalOut;
		ByteBuffer buffer;

		try (RandomAccessFile rafIn = new RandomAccessFile("registroD.dat", "r")) {

			// Creamos un canal de entrada obteniéndolo a partir del RAF.
			canalIn = rafIn.getChannel();

			// Creamos un Path con la ruta al fichero de salida.
			Path path = Paths.get("copiaRegistro.dat");

			// Creamos el canal de salida usando el Path y un par de StandardOpenOption para no machacar información.
			canalOut = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

			// Alojamos memoria en el Buffer.
			buffer = ByteBuffer.allocate(512);

			// Mientras los datos recogidos del canal mediante .read() sean mayores que 0.
			while (canalIn.read(buffer) > 0) {
				// Pasamos el Buffer a lectura para extraer información de él.
				buffer.flip();
				// Escribimos la información en el canal de salida.
				canalOut.write(buffer);
				// Reseteamos el estado del Buffer.
				buffer.rewind();
			}

			// Cerramos los canales.
			canalOut.close();
			canalIn.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
