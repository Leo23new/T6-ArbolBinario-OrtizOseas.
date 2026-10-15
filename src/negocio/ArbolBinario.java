package negocio;

import modelo.Nodo;

/**
 * Árbol binario genérico. Las operaciones de conteo, altura y búsqueda
 * se implementan de forma recursiva.
 *
 * @param <T> tipo de dato almacenado en los nodos
 */
public class ArbolBinario<T> {

    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    /** Verifica si el árbol está vacío. */
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * Crea el nodo raíz.
     *
     * @return el nodo raíz creado, o null si el árbol ya tenía raíz
     */
    public Nodo<T> crearRaiz(T dato) {
        if (raiz != null) {
            return null;
        }
        raiz = new Nodo<>(dato);
        return raiz;
    }

    /**
     * Agrega un hijo izquierdo al nodo padre.
     *
     * @return el nuevo nodo, o null si el padre es nulo o la posición está ocupada
     */
    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        if (padre == null || padre.getIzquierdo() != null) {
            return null;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setIzquierdo(nuevo);
        return nuevo;
    }

    /**
     * Agrega un hijo derecho al nodo padre.
     *
     * @return el nuevo nodo, o null si el padre es nulo o la posición está ocupada
     */
    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        if (padre == null || padre.getDerecho() != null) {
            return null;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setDerecho(nuevo);
        return nuevo;
    }

    // ------------------------------------------------------------------
    // Conteo y altura (recursivos)
    // ------------------------------------------------------------------

    /** Número total de nodos del árbol. */
    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    /** Número de hojas (nodos sin hijos). */
    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) {
            return 1;
        }
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    /**
     * Altura del árbol medida en aristas.
     * Árbol vacío = -1, árbol con un solo nodo = 0.
     */
    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) {
            return -1;
        }
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    // ------------------------------------------------------------------
    // Búsqueda (recursiva, recorrido preorden)
    // ------------------------------------------------------------------

    /**
     * Busca un nodo cuyo dato, convertido a texto, coincida con el indicado
     * (sin distinguir mayúsculas de minúsculas).
     *
     * @return el nodo encontrado o null si no existe
     */
    public Nodo<T> buscar(String texto) {
        return buscar(raiz, texto);
    }

    private Nodo<T> buscar(Nodo<T> nodo, String texto) {
        if (nodo == null) {
            return null;
        }
        if (String.valueOf(nodo.getDato()).equalsIgnoreCase(texto.trim())) {
            return nodo;
        }
        Nodo<T> enIzquierdo = buscar(nodo.getIzquierdo(), texto);
        if (enIzquierdo != null) {
            return enIzquierdo;
        }
        return buscar(nodo.getDerecho(), texto);
    }

    // ------------------------------------------------------------------
    // Visualización
    // ------------------------------------------------------------------

    /**
     * Presenta la estructura del árbol en consola.
     * (I) indica hijo izquierdo y (D) hijo derecho.
     */
    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("(árbol vacío)");
            return;
        }
        System.out.println(raiz.getDato());
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo<T> nodo, String prefijo) {
        Nodo<T> izq = nodo.getIzquierdo();
        Nodo<T> der = nodo.getDerecho();

        if (izq != null) {
            boolean esUltimo = (der == null);
            System.out.println(prefijo + (esUltimo ? "`-- " : "|-- ") + "(I) " + izq.getDato());
            mostrarHijos(izq, prefijo + (esUltimo ? "    " : "|   "));
        }
        if (der != null) {
            System.out.println(prefijo + "`-- (D) " + der.getDato());
            mostrarHijos(der, prefijo + "    ");
        }
    }
}
