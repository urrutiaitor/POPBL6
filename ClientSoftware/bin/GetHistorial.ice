module utils {
    sequence<string> StringVector;
  
    interface GetHistorial {
        bool comprobarUsuario(string usuario, string contrasena);
        StringVector getProx(string usuario, string contrasena);
        StringVector getTemp(string usuario, string contrasena);
    };
};
