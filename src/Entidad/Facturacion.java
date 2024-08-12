package Entidad;

import java.util.Date;

public class Facturacion {

    private int id_factura;
    private int id_p;
    private Date Fecha;
    private String Cliente;
    private String Producto;
    private int Cantida;
    private float SubTotal;
    private float Impuesto;
    private float MontoTotal;

    public Facturacion(int id_p, Date Fecha, String Cliente, String Producto, int Cantida, float SubTotal, float Impuesto, float MontoTotal) {
        this.id_p = id_p;
        this.Fecha = Fecha;
        this.Cliente = Cliente;
        this.Producto = Producto;
        this.Cantida = Cantida;
        this.SubTotal = SubTotal;
        this.Impuesto = Impuesto;
        this.MontoTotal = MontoTotal;
    }

    public Facturacion() {
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public int getCantida() {
        return Cantida;
    }

    public void setCantida(int Cantida) {
        this.Cantida = Cantida;
    }

    public float getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(float SubTotal) {
        this.SubTotal = SubTotal;
    }

    public float getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(float Impuesto) {
        this.Impuesto = Impuesto;
    }

    public float getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(float MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    @Override
    public String toString() {
        return "Facturacion{" + "id_factura=" + id_factura + ", Fecha=" + Fecha + ", Cliente=" + Cliente + ", Producto=" + Producto + ", Cantida=" + Cantida + ", SubTotal=" + SubTotal + ", Impuesto=" + Impuesto + ", MontoTotal=" + MontoTotal + '}';
    }
}
