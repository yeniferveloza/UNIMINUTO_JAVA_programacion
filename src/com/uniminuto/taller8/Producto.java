package com.uniminuto.taller8;

public class Producto {
	
	private String codigoProducto;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private double precioVenta;
    private String categoria;
    private int cantidadDisponible;
    private boolean activo;

    public Producto(String codigoProducto, String nombre, String descripcion, double precioBase, double precioVenta, String categoria, int cantidadDisponible) {
        this(codigoProducto, nombre, descripcion, precioBase, precioVenta, categoria, cantidadDisponible, true);
    }

    public Producto(String codigoProducto, String nombre, String descripcion, double precioBase, double precioVenta, String categoria, int cantidadDisponible, boolean activo) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.cantidadDisponible = cantidadDisponible;
        this.activo = activo;
    }
    
    public String getCodigoProducto() { return codigoProducto; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioBase() { return precioBase; }
    public double getPrecioVenta() { return precioVenta; }
    public String getCategoria() { return categoria; }
    public int getCantidadDisponible() { return cantidadDisponible; }

    public boolean isActivo() { return activo; }

}
