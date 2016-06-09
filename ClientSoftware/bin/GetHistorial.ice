module utils {
    sequence<string> StringVector;
  
    interface GetHistorial {
        bool comprobarUsuario(string usuario, string contrasena);
        void getProx(string usuario, string contrasena, out StringVector vector);
        void getTemp(string usuario, string contrasena, out StringVector vector);
    };
};
