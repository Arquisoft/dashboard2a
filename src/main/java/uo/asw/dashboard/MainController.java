package uo.asw.dashboard;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import uo.asw.kafkastream.listeners.MessageListener;
import uo.asw.kafkastream.producers.KafkaProducer;


@Controller
public class MainController {

	

	@Autowired
	private KafkaProducer kafkaProducer;

	@RequestMapping("/ejemplo")
	public String landing(Model model) {
		model.addAttribute("data", MessageListener.con);
		return "index";
	}

	@RequestMapping("/loadEjemplo")
	public String loadData(Model model) {
		kafkaProducer.send("exampleTopic", "test");
		return "redirect:/";
	}

}