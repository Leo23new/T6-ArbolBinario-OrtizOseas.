# T6-ArbolBinario-OrtizOseas

**Autor:** Everly Oseas Ortiz Yaucan
**Carrera:** Software — Universidad Técnica de Ambato (FISEI-UTA)
**Tarea 6:** Árbol binario con programación orientada a objetos en Java

## 1. Problema

La Universidad necesita representar la organización de sus dependencias académicas.
Cada dependencia es un nodo que guarda su nombre y puede tener como máximo dos hijos
(izquierdo y derecho), por lo que la estructura natural es un **árbol binario**.
La aplicación permite construir el árbol, visualizarlo y analizarlo (nodos, hojas,
altura, grado de un nodo y si un nodo es hoja).

## 2. Estructura del árbol

```
UNIVERSIDAD
|-- (I) FISEI
|   |-- (I) SOFTWARE
|   |   |-- (I) NIVEL1
|   |   `-- (D) NIVEL3
|   `-- (D) TI
`-- (D) DIDE
    |-- (I) INVESTIGACIÓN
    `-- (D) VINCULACIÓN
```

| Propiedad | Valor |
|-----------|-------|
| Nodos     | 9     |
| Hojas     | 5 (NIVEL1, NIVEL3, TI, INVESTIGACIÓN, VINCULACIÓN) |
| Altura    | 3 aristas (UNIVERSIDAD → FISEI → SOFTWARE → NIVEL1) |

## 3. Organización del proyecto

```
src/
 ├── modelo/
 │    └── Nodo.java            // Nodo<T>: dato, izquierdo, derecho, esHoja(), grado()
 ├── negocio/
 │    └── ArbolBinario.java    // crearRaiz, agregarIzquierdo/Derecho, contarNodos,
 │                             // contarHojas, altura, esVacio, mostrarArbol, buscar
 └── app/
      └── Main.java            // menú interactivo con Scanner y casos de prueba
```

Restricciones cumplidas: no se usa `TreeSet`, `TreeMap`, `PriorityQueue`,
`NavigableSet` ni `NavigableMap`; el conteo de nodos, de hojas, la altura y la
búsqueda son **recursivos**.

## 4. Instrucciones de ejecución

Requisitos: JDK 11 o superior.

```bash
# desde la carpeta raíz del repositorio
mkdir out
javac -encoding UTF-8 -d out src/modelo/Nodo.java src/negocio/ArbolBinario.java src/app/Main.java
java -Dfile.encoding=UTF-8 -cp out app.Main
```

En Windows, si las tildes se ven mal en la consola, ejecute antes `chcp 65001`.
También se puede abrir la carpeta `src` en VS Code y ejecutar `app/Main.java`.

### Menú

| Opción | Acción |
|--------|--------|
| 1 | Crear el árbol inicial |
| 2 | Visualizar el árbol |
| 3 | Consultar el número de nodos |
| 4 | Consultar el número de hojas |
| 5 | Consultar la altura |
| 6 | Consultar el grado de un nodo (se ingresa su nombre) |
| 7 | Verificar si un nodo es hoja (se ingresa su nombre) |
| 8 | Salir |
| 9 | *(Adicional)* Ejecutar los casos de prueba obligatorios |


| Caso | Resultado esperado | Resultado obtenido |
|------|--------------------|--------------------|
| Árbol vacío | 0 nodos, altura -1 | 0 nodos, altura -1 ✔ |
| Un solo nodo | 1 hoja, altura 0 | 1 hoja, altura 0 ✔ |
| Árbol inicial | 9 nodos, 5 hojas, altura 3 | 9 nodos, 5 hojas, altura 3 ✔ |
| Insertar en posición ocupada | Inserción rechazada | Rechazada: FISEI ya tiene hijo izquierdo ✔ |
| Grado de FISEI y SOFTWARE | 2 y 2 | 2 y 2 ✔ |
