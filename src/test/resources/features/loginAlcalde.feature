# language: es
Característica: Login de usuario

Escenario: Login como alcalde

	Dado un formulario de login
	Cuando me logueo como usuario "alcalde" con password "1234"
	Entonces voy al dashboard del alcalde
