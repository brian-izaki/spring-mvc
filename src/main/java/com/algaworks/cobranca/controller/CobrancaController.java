package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.Cobranca;
import com.algaworks.cobranca.model.StatusCharge;
import com.algaworks.cobranca.repository.Cobrancas;

@Controller
@RequestMapping("/cobranca")
public class CobrancaController {
	
	// ele está pegado da interface criada e instanciando aqui com o autoWired
	@Autowired
	private Cobrancas cobrancas;
	
	// Rotas da aplicação Front-end
	
	@RequestMapping(method = RequestMethod.GET)
	public String pesquisar() {
		return "PesquisaCobranca";
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroCobranca");
		
		//mv.addObject("todosStatusCharge", StatusCharge.values());
		
		return mv;
	}
	
	// quando o cliente acessar a aba "cobranca" e utilizar o método POST na requisição
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Cobranca cobranca) {
		// h2 é um banco de dados em memória.
		cobrancas.save(cobranca);
		
		ModelAndView mv = new ModelAndView("CadastroCobranca");
		mv.addObject("mensagem", "Cobrança salva com sucesso!");
		
		
		
		return mv;
	}
	
	// no @modelAttribute, eu defino o nome do atributo que será reconhecido pelo thymeleaf
	// Caso não passe nada, por padrão será o "Nome_do_Objeto + List"
	@ModelAttribute("todosStatusCharge")
	public List<StatusCharge> todosStatusCharge() {
		// com isso, faço que o atributo de listagem sempre vá. não precisa repetir nos métodos anteriores o
		// [mv.addObject("todosStatusCharge", StatusCharge.values());]
		return Arrays.asList(StatusCharge.values());
	}
}
