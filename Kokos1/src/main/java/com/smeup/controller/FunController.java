package com.smeup.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smeup.utils.FindServiceByFun;
import com.smeup.utils.InterpreterCaller;
import com.smeup.yamlReader.*;


@Controller
@RequestMapping("/fun")
public class FunController {
	
	@RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody String recall(@RequestBody String payload)
	{
		String service;
		String response = "";
	    String fun = payload;
	    
	    //DALLA FUN RICAVO IL NOME DEL PROGRAMMA DA CHIAMARE
	    FindServiceByFun findProg = new FindServiceByFun();
	    service = findProg.find(fun);
	    
		
	    // LEGGO IL REGISTRO DEI PROGRAMMI (.YML) E RICAVO L'INTERPRETE DEL PROGRAMMA RICAVATO
		FindInterpreter findInt= new FindInterpreter();	
		String interpreter = findInt.find(service);
		
		
		//RICHIAMO IL PROGRAMMA SULL'INTERPRETE SPECIFICO
		InterpreterCaller intCall = new InterpreterCaller(fun, interpreter);
		
		//RITORNA XML DAL PROGRAMA CHIAMATO
		try {
			response = intCall.call();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return response; //"application/json" mean this is a text not a redirect
	}
}
