package clases;

public class Nombres {

    private String nombre;
    private String numero;
    private String urlImg;


    public Nombres(String nombre,String Cantidad,String urlImg)
    {
        this.nombre=nombre;
        this.numero=numero;
        this.urlImg=urlImg;
    }
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        nombre = nombre;
    }

    public String getCantidad()
    {
        return numero;
    }

    public void setCantidad(String cantidad)
    {
        numero = numero;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
