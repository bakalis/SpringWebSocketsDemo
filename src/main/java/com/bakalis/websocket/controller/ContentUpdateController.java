package com.bakalis.websocket.controller;

import com.bakalis.websocket.model.HeaderOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;

@Controller
public class ContentUpdateController {

	@Autowired
	private SimpMessagingTemplate template;

	//Called when a message is sent to the /hello destination / Writes the data down in a file and sents them to the Messages destination.
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public HeaderOrder headerOrder(HeaderOrder order) throws Exception {

		if(order.getHeader()!=null && order.getName()!= null){
			BufferedWriter bw = new BufferedWriter(new FileWriter("headers.txt",false));
			// ; (Semicolon) is used as a Delimiter to distinguish name and header values.
			bw.write(order.getName()+";"+order.getHeader());
			bw.close();
		}
        return order;
    }


    //Retrieves the data from the file and sends them to the Website
	@GetMapping("/home")
	public String homeMapping(Model model) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("headers.txt"));
		String s = br.readLine();
		// ; (Semicolon) is used as a Delimiter to distinguish name and header values.
		String[] info = s.split(";");
		model.addAttribute("name",info[0]);
		model.addAttribute("header",info[1]);
		return "index";
	}
	
}
