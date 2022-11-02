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

			canalIn = rafIn.getChannel();

			Path path = Paths.get("copiaRegistro.dat");

			canalOut = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

			buffer = ByteBuffer.allocate(512);

			while (canalIn.read(buffer) > 0) {
				buffer.flip();
				canalOut.write(buffer);
				buffer.rewind();
			}

			canalOut.close();
			canalIn.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
