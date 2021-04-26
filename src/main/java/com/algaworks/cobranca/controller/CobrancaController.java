package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.Cobranca;
import com.algaworks.cobranca.model.StatusCharge;
import com.algaworks.cobranca.repository.Cobrancas;

@Controller
@RequestMapping("/cobranca")
public class CobrancaController {
	
	// ele está pegado da interface criada e instanciando no repository com o autoWired
	// injeção de dependência pelo Framework (ele sai procurando o objeto e encaixa pra mim)
	@Autowired
	private Cobrancas cobrancas;
	
	// Rotas da aplicação Front-end
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("PesquisaCobranca");

		List<Cobranca> todasCobrancas = cobrancas.findAll();
		
		mv.addObject("listaCobranca", todasCobrancas);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroCobranca");
		mv.addObject(new Cobranca());
		//mv.addObject("todosStatusCharge", StatusCharge.values());
		
		return mv;
	}
	
	// quando o cliente acessar a aba "cobranca" e utilizar o método POST na requisição
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cobranca cobranca, Errors errors, RedirectAttributes redirect) {
		if (errors.hasErrors()) {
			return "CadastroCobranca";
		}
		
		cobrancas.save(cobranca);
		redirect.addFlashAttribute("mensagem", "Cobrança salva com sucesso!");
		
		// faz redirecionamento de página
		return "redirect:/cobranca/novo";
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
