package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Nodo;
import negocio.ArbolBinario;

/**
 * Programa principal: organización jerárquica de departamentos universitarios.
 */
public class Main {

    private static ArbolBinario<String> arbol = new ArbolBinario<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
            }
            sc.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1:
                    crearArbolInicial();
                    break;
                case 2:
                    System.out.println("\n--- Estructura del árbol ---");
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("\nNúmero de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("\nNúmero de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("\nAltura del árbol (en aristas): " + arbol.altura());
                    break;
                case 6:
                    consultarGrado(sc);
                    break;
                case 7:
                    verificarHoja(sc);
                    break;
                case 8:
                    System.out.println("\nSaliendo del programa...");
                    break;
                case 9:
                    ejecutarCasosDePrueba();
                    break;
                default:
                    System.out.println("\nOpción no válida. Ingrese un número del 1 al 8.");
            }
        } while (opcion != 8);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=========== ORGANIZACIÓN DE DEPARTAMENTOS ===========");
        System.out.println("1. Crear el árbol inicial");
        System.out.println("2. Visualizar el árbol");
        System.out.println("3. Consultar el número de nodos");
        System.out.println("4. Consultar el número de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
        System.out.println("9. (Adicional) Ejecutar casos de prueba");
        System.out.print("Seleccione una opción: ");
    }

    /** Construye el árbol inicial de 9 nodos del enunciado. */
    private static void construirInicial(ArbolBinario<String> a) {
        Nodo<String> universidad = a.crearRaiz("UNIVERSIDAD");

        Nodo<String> fisei = a.agregarIzquierdo(universidad, "FISEI");
        Nodo<String> dide = a.agregarDerecho(universidad, "DIDE");

        Nodo<String> software = a.agregarIzquierdo(fisei, "SOFTWARE");
        a.agregarDerecho(fisei, "TI");

        a.agregarIzquierdo(dide, "INVESTIGACIÓN");
        a.agregarDerecho(dide, "VINCULACIÓN");

        a.agregarIzquierdo(software, "NIVEL1");
        a.agregarDerecho(software, "NIVEL3");
    }

    private static void crearArbolInicial() {
        arbol = new ArbolBinario<>();
        construirInicial(arbol);
        System.out.println("\nÁrbol inicial creado correctamente (" + arbol.contarNodos() + " nodos).");
    }

    private static void consultarGrado(Scanner sc) {
        if (arbol.esVacio()) {
            System.out.println("\nEl árbol está vacío. Cree el árbol inicial (opción 1).");
            return;
        }
        System.out.print("\nIngrese el nombre del nodo: ");
        String nombre = sc.nextLine();
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo \"" + nombre.trim() + "\" no existe en el árbol.");
        } else {
            System.out.println("Grado de " + nodo.getDato() + ": " + nodo.grado());
        }
    }

    private static void verificarHoja(Scanner sc) {
        if (arbol.esVacio()) {
            System.out.println("\nEl árbol está vacío. Cree el árbol inicial (opción 1).");
            return;
        }
        System.out.print("\nIngrese el nombre del nodo: ");
        String nombre = sc.nextLine();
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo \"" + nombre.trim() + "\" no existe en el árbol.");
        } else if (nodo.esHoja()) {
            System.out.println(nodo.getDato() + " SÍ es una hoja.");
        } else {
            System.out.println(nodo.getDato() + " NO es una hoja (tiene " + nodo.grado() + " hijo(s)).");
        }
    }

    /** Casos de prueba obligatorios del enunciado. */
    private static void ejecutarCasosDePrueba() {
        System.out.println("\n================ CASOS DE PRUEBA ================");

        // Caso 1: árbol vacío
        ArbolBinario<String> vacio = new ArbolBinario<>();
        System.out.println("\n[Caso 1] Árbol vacío");
        System.out.println("  esVacio()      = " + vacio.esVacio());
        System.out.println("  contarNodos()  = " + vacio.contarNodos() + "   (esperado: 0)");
        System.out.println("  altura()       = " + vacio.altura() + "  (esperado: -1)");

        // Caso 2: un solo nodo
        ArbolBinario<String> unico = new ArbolBinario<>();
        unico.crearRaiz("UNIVERSIDAD");
        System.out.println("\n[Caso 2] Árbol con un solo nodo");
        System.out.println("  contarNodos()  = " + unico.contarNodos() + "   (esperado: 1)");
        System.out.println("  contarHojas()  = " + unico.contarHojas() + "   (esperado: 1)");
        System.out.println("  altura()       = " + unico.altura() + "   (esperado: 0)");

        // Caso 3: árbol inicial
        ArbolBinario<String> inicial = new ArbolBinario<>();
        construirInicial(inicial);
        System.out.println("\n[Caso 3] Árbol inicial");
        inicial.mostrarArbol();
        System.out.println("  contarNodos()  = " + inicial.contarNodos() + "   (esperado: 9)");
        System.out.println("  contarHojas()  = " + inicial.contarHojas() + "   (esperado: 5)");
        System.out.println("  altura()       = " + inicial.altura() + "   (esperado: 3)");

        // Caso 4: insertar en posición ocupada
        System.out.println("\n[Caso 4] Intento de insertar un hijo en una posición ocupada");
        Nodo<String> fisei = inicial.buscar("FISEI");
        Nodo<String> intento = inicial.agregarIzquierdo(fisei, "NUEVO");
        if (intento == null) {
            System.out.println("  Inserción rechazada: FISEI ya tiene hijo izquierdo (SOFTWARE).");
        } else {
            System.out.println("  ERROR: se insertó en una posición ocupada.");
        }
        System.out.println("  contarNodos()  = " + inicial.contarNodos() + "   (sigue siendo 9)");

        // Caso 5: grado de FISEI y SOFTWARE
        System.out.println("\n[Caso 5] Grado de FISEI y SOFTWARE");
        System.out.println("  grado(FISEI)    = " + inicial.buscar("FISEI").grado() + "   (esperado: 2)");
        System.out.println("  grado(SOFTWARE) = " + inicial.buscar("SOFTWARE").grado() + "   (esperado: 2)");
        System.out.println("\n=================================================");
    }
}
