package Tema2.EjerciciosTeoriaJava;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Departamento implements Serializable {
	private int numeroDepartamento;
	private String nombre;
	private String localidad;

	public int getNumeroDepartamento() {
		return numeroDepartamento;
	}

	public void setNumeroDepartamento(int numeroDepartamento) {
		this.numeroDepartamento = numeroDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}

/**
 * Ejercicio1
 */
public class Ejercicio1 {

	public static void main(String[] args) {
		try (var out = new ObjectOutputStream(new FileOutputStream("Departamentos.dat"))) {
			String[] nombres = { "finanzas", "deporte", "salud", "negocios", "recursos", "gerencia", "informatica",
					"educacion", "clientes", "seguridad" };
			String[] localidad = { "almeria", "madrid", "amurrio", "vitoria", "miranda", "casablanca", "bilbo",
					"florida", "peru", "tokio" };
			var arr = new ArrayList<Departamento>();
			for (int i = 0; i < 10; i++) {
				var dep = new Departamento();
				dep.setLocalidad(localidad[i]);
				dep.setNombre(nombres[i]);
				dep.setNumeroDepartamento(i);
				arr.add(dep);
			}
			out.writeObject(arr);
		} catch (Throwable e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}
}
