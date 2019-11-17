package es.lm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import es.lm.service.ILmTestService;
import es.lm.vo.ItemVO;

@Controller
public class LmTestController {

	@Autowired
	private ILmTestService lmService;

	@PostMapping(value = "/receipt")
	public ModelAndView showReceipt(ModelAndView model, @RequestBody List<ItemVO> jsonList) {
		model.addObject("receipt", lmService.getReceipt(jsonList));
		model.setViewName("/receipt/home");
		return model;
	}
}
