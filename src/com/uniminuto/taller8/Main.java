package com.uniminuto.taller8;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConexionDB.ejecutarMigracionesSchema();
        ProductoDAO productoDAO = new ProductoDAO();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println();
            System.out.println("--- Gestión de productos (CRUD) ---");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar todos los productos");
            System.out.println("3. Buscar producto por código");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar (borrado lógico)");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    registrarDesdeConsola(sc, productoDAO);
                    break;
                case "2":
                    listarProductos(productoDAO);
                    break;
                case "3":
                    buscarPorCodigo(sc, productoDAO);
                    break;
                case "4":
                    actualizarDesdeConsola(sc, productoDAO);
                    break;
                case "5":
                    eliminarDesdeConsola(sc, productoDAO);
                    break;
                case "6":
                    salir = true;
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        sc.close();
    }

    private static void registrarDesdeConsola(Scanner sc, ProductoDAO dao) {
        System.out.println("\n--- Nuevo producto ---");
        System.out.print("Código: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine().trim();
        double precioBase = leerDouble(sc, "Precio base: ");
        double precioVenta = leerDouble(sc, "Precio venta: ");
        System.out.print("Categoría: ");
        String categoria = sc.nextLine().trim();
        int cantidad = leerEntero(sc, "Cantidad disponible: ");

        Producto p = new Producto(codigo, nombre, descripcion, precioBase, precioVenta, categoria, cantidad);
        dao.registrarProducto(p);
    }

    private static void listarProductos(ProductoDAO dao) {
        System.out.println("\n--- Lista de productos ---");
        List<Producto> lista = dao.listarProductos();
        if (lista.isEmpty()) {
            System.out.println("No hay productos activos (los dados de baja no se muestran aquí).");
            return;
        }
        for (Producto p : lista) {
            mostrarProducto(p);
            System.out.println("--------------------");
        }
    }

    private static void buscarPorCodigo(Scanner sc, ProductoDAO dao) {
        System.out.print("\nCódigo del producto a buscar: ");
        String codigo = sc.nextLine().trim();
        Producto p = dao.buscarPorCodigo(codigo);
        if (p == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }
        System.out.println("--- Producto encontrado ---");
        mostrarProducto(p);
    }

    private static void actualizarDesdeConsola(Scanner sc, ProductoDAO dao) {
        System.out.print("\nCódigo del producto a modificar: ");
        String codigoOriginal = sc.nextLine().trim();
        if (dao.buscarPorCodigo(codigoOriginal) == null) {
            System.out.println("No existe un producto con ese código.");
            return;
        }
        System.out.println("Ingrese los nuevos datos (puede cambiar también el código):");
        System.out.print("Nuevo código: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine().trim();
        double precioBase = leerDouble(sc, "Precio base: ");
        double precioVenta = leerDouble(sc, "Precio venta: ");
        System.out.print("Categoría: ");
        String categoria = sc.nextLine().trim();
        int cantidad = leerEntero(sc, "Cantidad disponible: ");

        Producto actualizado = new Producto(codigo, nombre, descripcion, precioBase, precioVenta, categoria, cantidad);
        dao.actualizarProducto(codigoOriginal, actualizado);
    }

    private static void eliminarDesdeConsola(Scanner sc, ProductoDAO dao) {
        System.out.print("\nCódigo del producto a dar de baja: ");
        String codigo = sc.nextLine().trim();
        System.out.print("¿Confirma la baja lógica? El registro permanecerá en la base de datos. (s/n): ");
        String conf = sc.nextLine().trim();
        if (!conf.equalsIgnoreCase("s") && !conf.equalsIgnoreCase("si")) {
            System.out.println("Operación cancelada.");
            return;
        }
        dao.eliminarProducto(codigo);
    }

    private static void mostrarProducto(Producto p) {
        System.out.println("Código:          " + p.getCodigoProducto());
        System.out.println("Nombre:          " + p.getNombre());
        System.out.println("Descripción:     " + p.getDescripcion());
        System.out.println("Precio base:     " + p.getPrecioBase());
        System.out.println("Precio venta:    " + p.getPrecioVenta());
        System.out.println("Categoría:       " + p.getCategoria());
        System.out.println("Cantidad:        " + p.getCantidadDisponible());
        System.out.println("Activo:          " + (p.isActivo() ? "sí (visible en listado)" : "no (dado de baja; solo visible al buscar por código)"));
    }

    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico no válido. Intente de nuevo.");
            }
        }
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entero no válido. Intente de nuevo.");
            }
        }
    }
}
