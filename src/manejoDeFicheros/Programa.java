package manejoDeFicheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Programa {
	private static final String FILE_NAME = "coches.dat";
	private static ArrayList<Coche> coches = new ArrayList<>();

	public static void main(String[] args) {
		leerArchivo();
		desplegarMenu();
	}

	private static void leerArchivo() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			coches = (ArrayList<Coche>) ois.readObject();
			System.out.println("Archivo " + FILE_NAME + " leido");
		} catch (FileNotFoundException e) {
			System.out.println("Archivo " + FILE_NAME + " no encontrado");
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Error al convertir el objeto leido: " + e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				System.err.println("Error al cerrar los flujos: " + e.getMessage());
			}
		}
	}

	private static void desplegarMenu() {
		Scanner input = new Scanner(System.in);
		int opcion = 0;
		while (opcion != 5) {
			System.out.println("\nMenu:");
			System.out.println("1. Añadir nuevo coche");
			System.out.println("2. Borrar coche por id");
			System.out.println("3. Consultar coche por id");
			System.out.println("4. Listado de coches");
			System.out.println("5. Terminar el programa");
			System.out.print("Seleccione una opción: ");
			opcion = input.nextInt();
			switch (opcion) {
			case 1:
				agregarCoche();
				break;
			case 2:
				borrarCoche();
				break;
			case 3:
				consultarID();
				break;
			case 4:
				listarCoches();
				break;
			case 5:
				sobrescribirArchivo();
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		}
	}

	private static void agregarCoche() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nIngrese el ID: ");
		int id = input.nextInt();
		for (Coche coche : coches) {
			if (coche.getId() == id) {
				System.out.println("Ya existe un coche registrado con el ID " + id);
				return;
			} else {
				input.nextLine();
				System.out.print("Ingrese la matrícula: ");
				String matricula = input.nextLine();
				System.out.print("Ingrese la marca: ");
				String marca = input.nextLine();
				System.out.print("Ingrese el modelo: ");
				String modelo = input.nextLine();
				System.out.print("Ingrese el color: ");
				String color = input.nextLine();

				Coche nuevoCoche = new Coche(id, matricula, marca, modelo, color);
				coches.add(nuevoCoche);
				System.out.println("-- Coche agregado --");
			}
		}
	}

	private static void borrarCoche() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nIngrese el ID del coche a borrar: ");
		int id = input.nextInt();

		for (int i = 0; i < coches.size(); i++) {
			if (coches.get(i).getId() == id) {
				coches.remove(i);
				System.out.println("-- Coche borrado --");
				return;
			}
		}
		System.out.println("No se encontró ningún coche con ese ID");
	}

	private static void consultarID() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nIngrese el ID del coche a consultar: ");
		int id = input.nextInt();

		for (Coche coche : coches) {
			if (coche.getId() == id) {
				System.out.println("ID: " + coche.getId());
				System.out.println("Matrícula: " + coche.getLicensePlate());
				System.out.println("Marca: " + coche.getBrand());
				System.out.println("Modelo: " + coche.getModel());
				System.out.println("Color: " + coche.getColor());
				return;
			}
		}
		System.out.println("No se encontró ningún coche con ese ID");
	}

	private static void listarCoches() {
		System.out.println();
		if (coches.isEmpty()) {
			System.out.println("No hay coches almacenados");
		} else {
			Collections.sort(coches, new Comparator<Coche>() {
				@Override
				public int compare(Coche coche1, Coche coche2) {
					return coche1.getId() - coche2.getId();
				}
			});
			for (Coche coche : coches) {
				System.out.println("ID: " + coche.getId());
				System.out.println("Matrícula: " + coche.getLicensePlate());
				System.out.println("Marca: " + coche.getBrand());
				System.out.println("Modelo: " + coche.getModel());
				System.out.println("Color: " + coche.getColor());
				System.out.println("------------------");
			}
		}
	}

	private static void sobrescribirArchivo() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(coches);
			System.out.println("\nArchivo generado");
		} catch (FileNotFoundException e) {
			System.err.println("Error al crear el archivo: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				System.err.println("Error al cerrar los flujos: " + e.getMessage());
			}
		}
	}
}