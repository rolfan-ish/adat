package Encriptacion;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

/**
 * Ejercicio2
 * @author Aimar Ibarra
 */
public class Ejercicio2 {

    // Tamaño del bloque AES (128 bits)
    private static final int AES_KEY_TAMANIO = 128;
    private static final int IV_SIZE = 16;

    public static void main(String[] args) throws Exception {
        var filePath = "data.txt";
        var cryptFilePath = "dataCrypt.bin";
        var decryptFilePath = "data.bin";

        // Generar una clave AES
        var secretKey = generarAESKey();

        // Generar un IV (vector de inicialización)
        var iv = generarIV();

        // Cifrar el archivo
        encriptarArchivo(secretKey, iv, new File(filePath), new File(cryptFilePath));

        // Descifrar el archivo
        decriptarArchivo(secretKey, iv, new File(cryptFilePath), new File(decryptFilePath));
    }

    // Generar clave AES de 128 bits
    public static SecretKey generarAESKey() throws Exception {
        var keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_TAMANIO); // 128 bits para AES
        return keyGen.generateKey();
    }

    // Generar IV aleatorio de 16 bytes (128 bits)
    public static byte[] generarIV() {
        var iv = new byte[IV_SIZE];
        var random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    // Método para cifrar un archivo
    public static void encriptarArchivo(SecretKey secretKey, byte[] iv, File inputFile, File outputFile) throws Exception {
        var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        var ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Leer el archivo original
        var inputStream = new FileInputStream(inputFile);
        var outputStream = new FileOutputStream(outputFile);

        // Cifrar y escribir el archivo cifrado
		var buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            var output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }

        var outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }

        inputStream.close();
        outputStream.close();
    }

    // Método para descifrar un archivo
    public static void decriptarArchivo(SecretKey secretKey, byte[] iv, File inputFile, File outputFile) throws Exception {
        var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        var ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Leer el archivo cifrado
        var inputStream = new FileInputStream(inputFile);
        var outputStream = new FileOutputStream(outputFile);

        // Descifrar y escribir el archivo descifrado
        var buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }

        var outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }

        inputStream.close();
        outputStream.close();
    }
}
