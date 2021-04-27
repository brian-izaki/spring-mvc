package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.Cobranca;
import com.algaworks.cobranca.model.StatusCharge;
import com.algaworks.cobranca.repository.Cobrancas;
import com.algaworks.cobranca.service.CadastroCobrancaService;

@Controller
@RequestMapping("/cobranca")
public class CobrancaController {
	
	// torna a variavel imutável com o final e com static ele pertece à classe e não objeto (instanciação)
	private static final String CADASTRO_VIEW = "CadastroCobranca";
	
	@Autowired
	private Cobrancas cobrancas;
	
	@Autowired
	private CadastroCobrancaService cadastroCobrancaService;
	
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
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cobranca());
		//mv.addObject("todosStatusCharge", StatusCharge.values());
		
		return mv;
	}
	
	// quando o cliente acessar a aba "cobranca" e utilizar o método POST na requisição
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cobranca cobranca, Errors errors, RedirectAttributes redirect) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			cadastroCobrancaService.salvar(cobranca);
			redirect.addFlashAttribute("mensagem", "Cobrança salva com sucesso!");
			
			// faz redirecionamento de página
			return "redirect:/cobranca/novo";
		} catch(IllegalArgumentException e) {
			// o primeiro argumento é o name que foi passasdo no input
			// foi adicionado uma mensagem de erro para o caso de o dado de data for inválido
			errors.rejectValue("expirationDate", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	// com as chaves eu torno o caminho variavel (bom para passar id de items)
	@RequestMapping("/{code}")
	public ModelAndView edicao(@PathVariable Long code) {
		
		Cobranca cobranca = cobrancas.getOne(code);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(cobranca);
		return mv;
	}
	
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long code, RedirectAttributes attributes) {
		
		cadastroCobrancaService.excluir(code);
		
		attributes.addFlashAttribute("mensagem", "Cobrança excluída com sucesso!");
		return "redirect:/cobranca";
		
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
