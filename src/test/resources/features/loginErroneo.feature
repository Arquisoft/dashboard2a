# language: es
Característica: Login de usuario

  Escenario: Login como alcalde

    Dado el formulario de registro de la pagina principal
    Cuando me logueo como usuario que no existe
    Entonces se me muestra un error
