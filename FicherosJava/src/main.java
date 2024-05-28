import java.io.*;

public class main {

	public static void main(String[] args) throws IOException {
		
		File f2 = new File("Ficheros/File1.txt");
		
		System.out.println("Nombre del fichero: " + f2.getName());
		System.out.println("Ruta relativa: " + f2.getPath());
		System.out.println("Ruta absoluta: " + f2.getAbsolutePath());
		System.out.println("¿Se puede leer? " + f2.canRead());
		System.out.println("Se puede escribir? " + f2.canWrite());
		System.out.println("Tamaño: " + f2.length());
		System.out.println("¿Es un directorio? " + f2.isDirectory());
		System.out.println("¿Es un archivo? " + f2.isFile());
	
		//-----------------Lectura-----------------
		FileReader fr = new FileReader(f2);

		// Leemos el primer carácter que se almacena en una variable de tipo int
		int caract = fr.read();
		
		// Recorremos el fichero hasta encontrar el carácter -1 que marca el final del fichero
		while (caract != -1) {
		// Mostramos por pantalla el carácter leído convertido a char
			System.out.print((char) caract);
			caract = fr.read();
		}
		fr.close(); // Cerramos el fichero
		
		//-----------------Escritura-----------------
		String provincias[] = {"Madrid", "Sevilla", "Caceres"};
		
		FileWriter fw = new FileWriter(f2);

		for (int i = 0; i < provincias.length; i++) {

			fw.write(provincias[i]+ " ");

		}

		fw.close();
		
	}

}
