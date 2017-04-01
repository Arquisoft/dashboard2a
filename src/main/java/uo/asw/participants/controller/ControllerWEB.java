package uo.asw.participants.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.dbmanagement.GetParticipant;
import uo.asw.dbmanagement.GetUser;
import uo.asw.dbmanagement.UpdateInfo;
import uo.asw.dbmanagement.model.Citizen;
import uo.asw.dbmanagement.model.User;
import uo.asw.participants.util.Check;

@Controller
public class ControllerWEB {

	@Autowired
	private GetParticipant getParticipant;

	@Autowired
	private UpdateInfo updateInfo;

	@Autowired
	private GetUser getUser;

	/**
	 * Devuelve la pagina de incio login
	 * 
	 * @param model
	 * @return pagina log HTML
	 */
	@RequestMapping(value = { "/", "/portal" }, method = RequestMethod.GET)
	public String showView(Model model) {
		return "log";
	}

	
	@RequestMapping(value = { "/closeSession" }, method = RequestMethod.GET)
	public String closeSession(HttpSession session, Model model) {
		session.invalidate();
		return "log";
	}
	
	/**
	 * Recibe los datos de login del usuario, busca si exite ese usuario y en
	 * caso de exitir pasa a la siguiente página que muestra la informacion en
	 * caso contrario muestra la página de error, en caso de no ser un
	 * participante se comprueba si es otro usuario (politico o admin) y se
	 * reeenvia a la vista adecuada
	 * 
	 * @param session
	 *            mantiene la sesion
	 * @param user
	 *            nombre del login
	 * @param password
	 *            contresena del usuario
	 * @param model
	 * @return view si exito, error si fracaso
	 */
	@RequestMapping(value = "/acceso", method = RequestMethod.POST)
	public String showInfo(HttpSession session, @RequestParam String user, @RequestParam String password, Model model) {

		Citizen c = null;
		User u = null;

		if (user != null && password != null) {
			c = getParticipant.getParticipant(user, password);
			if (c != null) {
				session.setAttribute("citizen", c);
				model.addAttribute("resultado", "Bienvenid@ " + c.getName());
				return "viewParticipant";
			} else {
				// Buscamos si es politico/admin
				u = getUser.getUser(user, password);
				if (u != null) {
					session.setAttribute("user", u);
					return "viewUser";
				}
			}
		}
		return "error";

	}

	/**
	 * Acceso a la página que modifica los datos del usuario
	 * 
	 * @return changeInfo html para modificar datos del usuario
	 */
	@RequestMapping(value = "/changeInfo", method = RequestMethod.GET)
	public String changeInfo() {
		return "changeInfo";
	}

	/**
	 * Cambia la contrasena de un usuario, siempre que e usuario este en sesion,
	 * la contrasena antigua se igual que la contrasena de parametro y la nueva
	 * contrasena no sea vacia
	 * 
	 * @param session
	 *            scope
	 * @param password
	 *            contrasena antigua
	 * @param newPassword
	 *            contrasena nueva
	 * @param model
	 * @return pagina siguiente
	 */
	@RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
	public String changePassword(HttpSession session, @RequestParam String password, @RequestParam String newPassword,
			Model model) {
		Citizen c = (Citizen) session.getAttribute("citizen");
		if (c != null) {
			if (c.getPassword().equals(password) && !newPassword.isEmpty()) {
				c.setPassword(newPassword);
				updateInfo.updateInfo(c);
				model.addAttribute("resultado", "Contrasena actualizada correctamente");
				return "viewParticipant";
			}
			return "errorContrasena";
		}
		return "error";

	}

	/**
	 * Modifica el email del usuario en sesión, comprueba que el email es
	 * correcto segun un patron y muestra el resultado sobre el HTML view, o
	 * redirige a la pagina de error en caso de que no se encuentre el usuario
	 * en sesion
	 * 
	 * @param session
	 *            objeto session del usuario registrado
	 * @param email
	 *            nuevo email de usuario
	 * @param model
	 * @return view si el usuario esta registrado, error si el usuario no esta
	 *         registrado
	 */
	@RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
	public String changeEmail(HttpSession session, @RequestParam String email, Model model) {
		Citizen c = (Citizen) session.getAttribute("citizen");
		if (c != null) {
			if (!email.isEmpty() && Check.validateEmail(email)) {
				c.setEmail(email);
				updateInfo.updateInfo(c);
				model.addAttribute("resultado", "Email actualizado correctemente a: " + email);
			} else {
				model.addAttribute("resultado", "El email no es valido, no actualizado a: " + email);
			}
			return "viewParticipant";
		}
		return "error";
	}
}