package com.example.accesscontrol.servicios;

import com.example.accesscontrol.estructural.Usuario;

import java.util.ArrayList;

public class Servicio
{
    private ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
    ArrayList<String> mostrarUsuarios = new ArrayList<String>();


    public boolean validacion(Usuario user) {
        boolean centinela = false;
        int vali = user.getDocumento();


        for (int i = 0; i < listUsuarios.size(); i++) {
            Usuario obj = listUsuarios.get(i);
            int id = obj.getDocumento();

            if (vali == id) {
                centinela = true;
            }
        }

        return centinela;
    }

    public void registrarUsuario(Usuario user) {
        listUsuarios.add(user);
    }

    public boolean login(String usuario, String contraseña) {
        boolean validacion = false;
        boolean centinela = false;

        for (int i = 0; i < listUsuarios.size() && !centinela; i++) {

            //variable temporal que almacena el objeto de tipo usuario
            Usuario x = listUsuarios.get(i);

            String user = x.getUsuario();
            String password = x.getPassword();

            if (usuario.equals(user) && contraseña.equals(password)) {
                validacion = true;
                centinela = true;
            }
        }

        return validacion;
    }

    public int numEmpleados() {
        int num = 0;

        for (int i = 0; i < listUsuarios.size(); i++) {
            if (listUsuarios.get(i) != null) {
                num++;
            } else {
                num = -1;
            }
        }

        return num;
    }

    public boolean eliminarUsuario(int documento) {
        boolean centinela = false;

        for (int i = 0; i < listUsuarios.size() && !centinela; i++) {
            Usuario x = listUsuarios.get(i);
            int id = x.getDocumento();

            if (documento == id) {
                listUsuarios.remove(listUsuarios.get(i));
                centinela = true;
            }
        }
        return centinela;
    }

    public void modificarUsuario(String usuario, String password, String nombre, String apellido, String posCompany, String genero, int edad, int documento)
    {

        boolean centinela = false;

        for (int i = 0; i < listUsuarios.size() && !centinela; i++)
        {
            Usuario x = listUsuarios.get(i);
            int id = x.getDocumento();

            if (documento == id)
            {
                x.setUsuario(usuario);
                x.setPassword(password);
                x.setNombre(nombre);
                x.setApellido(apellido);
                x.setPosCompany(posCompany);
                x.setGenero(genero);
                x.setEdad(edad);
                x.setDocumento(documento);
                centinela = true;
            }
        }
    }

    public boolean buscarUsuarioPorDocumentNumber(int documento) {
        boolean centinela = false;

        for (int i = 0; i < listUsuarios.size() && !centinela; i++) {
            Usuario x = listUsuarios.get(i);
            int id = x.getDocumento();

            if (documento == id) {
                centinela = true;
            }
        }
        return centinela;
    }

    public Usuario enviarObj(String usuario, String contraseña) {
        boolean validacion = false;
        boolean centinela = false;
        Usuario usr = null;

        for (int i = 0; i < listUsuarios.size() && !centinela; i++) {



            //variable temporal que almacena el objeto de tipo usuario
            Usuario x = listUsuarios.get(i);

            String user = x.getUsuario();
            String password = x.getPassword();

            if (usuario.equals(user) && contraseña.equals(password)) {
                validacion = true;
                centinela = true;

                usr = x;
            }
        }

        return usr;
    }

    public Usuario enviarObjDocumento(int pDocumento) {
        boolean validacion = false;
        boolean centinela = false;
        Usuario usr = null;

        for (int i = 0; i < listUsuarios.size() && !centinela; i++) {



            //variable temporal que almacena el objeto de tipo usuario
            Usuario x = listUsuarios.get(i);

            int documento =  x.getDocumento();

            if (pDocumento == documento) {
                validacion = true;
                centinela = true;

                usr = x;
            }
        }

        return usr;
    }


    public ArrayList<Usuario> getListUsuarios()
    {
        return listUsuarios;
    }

    public ArrayList<String> getListaDeNombres()
    {
        for(int i = 0; i < listUsuarios.size(); i++)
        {
            if(listUsuarios.size() > 0)
            {
                Usuario obUsuario = listUsuarios.get(i);
                String nombre = obUsuario.getNombre();
                int documento = obUsuario.getDocumento();

                String concatenar = "Nombre: ".concat(nombre).concat(" - ").concat(" Documento: ").concat(String.valueOf(documento));

                mostrarUsuarios.add(concatenar);

            }
            else
            {
                mostrarUsuarios.add("Vacio");
            }
        }
        return mostrarUsuarios;
    }


    public void setListUsuarios(ArrayList<Usuario> listUsuarios)
    {
        this.listUsuarios = listUsuarios;
    }


}

