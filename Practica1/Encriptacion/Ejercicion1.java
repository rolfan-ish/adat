package Encriptacion;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Ejercicion1
 * @author Aimar Ibarra
 */
public class Ejercicion1 {
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Convierte un char en su version numerica segun el algoritmo Vigenere
	 */
	private static int charInt(char c) {
		return (int) c - (int) 'A';
	}

	/**
	 * Operacion inversa a charInt
	 */
	private static char intChar(int c) {
		return (char)(c + (int) 'A');
	}

	/**
	 * Encripta un char, en vez de utilizar un condicional basta con el modulo
	 */
	private static char encriptarChar(char c, char k) {
		return intChar((charInt(c) + charInt(k)) % 26);
	}

	/**
	 * Decripta un char, denuevo, basta con el modulo (pero es necesario sumar de antemano)
	 */
	private static char decriptarChar(char c, char k) {
		return intChar((charInt(c) - charInt(k) + 26) % 26);
	}

	/**
	 * Aplica encriptarChar a todo un string
	 */
	private static String encriptarString(String s, String k) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			res += encriptarChar(s.charAt(i), k.charAt(i % k.length()));
		}
		return res;
	}

	/**
	 * Aplica decriptarChar a todo un string
	 */
	private static String decriptarString(String s, String k) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			res += decriptarChar(s.charAt(i), k.charAt(i % k.length()));
		}
		return res;
	}

	/**
	 * Filtra un string
	 */
	private static String filtrarString(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i)))
				res += Character.toUpperCase(s.charAt(i));
		}
		return res;
	}
	
	public void run() {
		System.out.println("Decriptar? (s/n)");
		var d = sc.nextLine().equalsIgnoreCase("s");
		
		System.out.println("Archivo a leer:");
		var ar = sc.nextLine();
		System.out.println("Archivo a crear:");
		var out = sc.nextLine();
		System.out.println("Clave:");
		var key = sc.nextLine();

		try {
			var data = filtrarString(Files.readString(new File(ar).toPath()));
			var outFile = new FileWriter(new File(out));
			String res = d ? decriptarString(data, key) : encriptarString(data, key);
			outFile.write(res);
			outFile.close();
		}
		catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	    new Ejercicion1().run();
	}
}
