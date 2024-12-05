import clases.SorteoBoxeo;

import java.util.Scanner;

public static void main(String[] args) {
    SorteoBoxeo sorteo = new SorteoBoxeo();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("1. Agrega un participante");
        System.out.println("2. Agrega una categoría");
        System.out.println("3. Comenzar sorteo");
        System.out.println("4. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre del participante: ");
                String nombreParticipante = scanner.nextLine();
                sorteo.agregarParticipante(nombreParticipante);
                break;
            case 2:
                System.out.print("Ingrese la categoría: ");
                String nombreCategoria = scanner.nextLine();
                sorteo.agregarCategoria(nombreCategoria);
                break;
            case 3:
                sorteo.realizarSorteo();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida. Por favor, inténtelo de nuevo.");
        }
    }
}