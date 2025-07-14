package Entidades.Facebook;

public class Ent_LoginFacebook {

    private String usuario;
    private String clave;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}

// Los getters y setters son para el encapsulamiento de los atributos de la clase.
// En este caso nos permitiran que cuando utilicemos las propiedades, podamos actualizarlas y retornarlas cuando lo necesitemos