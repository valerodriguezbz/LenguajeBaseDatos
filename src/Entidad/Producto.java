package Entidad;

/**
 *
 * @author vrb00
 */
public class Producto {

    private int id_producto;
    private String codigo_producto;
    private int id_categoria;
    private String nombre_producto;
    private float precio;
    private float iva;
    private int cantidad;
    private String imagen;

    public Producto(int id_producto, String codigo_producto, int id_categoria, String nombre_producto, float precio, float iva, int cantidad, String imagen) {
        this.id_producto = id_producto;
        this.codigo_producto = codigo_producto;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.iva = iva;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public Producto(String codigo_producto, int id_categoria, String nombre_producto, float precio, float iva, int cantidad, String imagen) {
        this.codigo_producto = codigo_producto;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.iva = iva;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public Producto(int id_producto, String codigo_producto, int id_categoria, String nombre_producto, float precio, int cantidad, String imagen) {
        this.id_producto = id_producto;
        this.codigo_producto = codigo_producto;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public Producto(int id_producto, String nombre_producto, float precio, float iva) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.iva = iva;
    }

    public Producto() {
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", codigo_producto=" + codigo_producto + ", id_categoria=" + id_categoria + ", nombre_producto=" + nombre_producto + ", precio=" + precio + ", iva=" + iva + ", cantidad=" + cantidad + ", imagen=" + imagen + '}';
    }
}
