package com.uniminuto.taller8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
	
	public void registrarProducto(Producto producto) {
        String sql = "INSERT INTO productos (codigo_producto, nombre, descripcion, precio_base, precio_venta, categoria, cantidad_disponible, activo) VALUES (?, ?, ?, ?, ?, ?, ?, TRUE)";

        Connection conn = ConexionDB.getConnection();
        if (conn == null) {
            return;
        }
        try (Connection c = conn; PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, producto.getCodigoProducto());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecioBase());
            pstmt.setDouble(5, producto.getPrecioVenta());
            pstmt.setString(6, producto.getCategoria());
            pstmt.setInt(7, producto.getCantidadDisponible());

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto guardado exitosamente: " + producto.getNombre());
            }
        } catch (SQLException e) {
            System.err.println("Error al persistir los datos del producto.");
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Mensaje: " + e.getMessage());
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT codigo_producto, nombre, descripcion, precio_base, precio_venta, categoria, cantidad_disponible, activo FROM productos WHERE activo = TRUE ORDER BY codigo_producto";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || pstmt == null) {
                return lista;
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos.");
            System.err.println("Mensaje: " + e.getMessage());
        }
        return lista;
    }

    public Producto buscarPorCodigo(String codigo) {
        String sql = "SELECT codigo_producto, nombre, descripcion, precio_base, precio_venta, categoria, cantidad_disponible, activo FROM productos WHERE codigo_producto = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || pstmt == null) {
                return null;
            }
            pstmt.setString(1, codigo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el producto.");
            System.err.println("Mensaje: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarProducto(String codigoOriginal, Producto producto) {
        String sql = "UPDATE productos SET codigo_producto = ?, nombre = ?, descripcion = ?, precio_base = ?, precio_venta = ?, categoria = ?, cantidad_disponible = ? WHERE codigo_producto = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || pstmt == null) {
                return false;
            }
            pstmt.setString(1, producto.getCodigoProducto());
            pstmt.setString(2, producto.getNombre());
            pstmt.setString(3, producto.getDescripcion());
            pstmt.setDouble(4, producto.getPrecioBase());
            pstmt.setDouble(5, producto.getPrecioVenta());
            pstmt.setString(6, producto.getCategoria());
            pstmt.setInt(7, producto.getCantidadDisponible());
            pstmt.setString(8, codigoOriginal);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Producto actualizado correctamente.");
                return true;
            }
            System.out.println("No se encontró un producto con el código indicado.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto.");
            System.err.println("Mensaje: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarProducto(String codigo) {
        String sql = "UPDATE productos SET activo = FALSE WHERE codigo_producto = ? AND activo = TRUE";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(sql) : null) {
            if (conn == null || pstmt == null) {
                return false;
            }
            pstmt.setString(1, codigo);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Producto dado de baja correctamente (sigue en base de datos, no aparece en el listado).");
                return true;
            }
            if (buscarPorCodigo(codigo) != null) {
                System.out.println("El producto ya estaba dado de baja.");
            } else {
                System.out.println("No se encontró un producto con ese código.");
            }
        } catch (SQLException e) {
            System.err.println("Error al dar de baja el producto.");
            System.err.println("Mensaje: " + e.getMessage());
        }
        return false;
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        return new Producto(
                rs.getString("codigo_producto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio_base"),
                rs.getDouble("precio_venta"),
                rs.getString("categoria"),
                rs.getInt("cantidad_disponible"),
                rs.getBoolean("activo"));
    }

}
