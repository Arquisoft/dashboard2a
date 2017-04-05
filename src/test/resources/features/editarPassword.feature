# language: es
Característica: Editar password

Escenario: Editar password como participanete

	Dado que me logueo en la pagina como participante "user2" con password "1234"
	Y pulso en el boton de modificar datos "/html/body/table/tbody/tr[5]/td[1]/a"
	Y escribo mi password actual "1234" y escribo mi password nueva "5678"
	Y pulso en volver a incio "/html/body/table/tbody/tr[5]/td[2]/a"
	Y me vuelvo a loguear como participante "user2" con la nueva password "5678"
	Entonces estoy en la pagina principal de usuario

