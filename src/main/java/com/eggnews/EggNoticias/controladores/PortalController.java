package com.eggnews.EggNoticias.controladores;

import com.eggnews.EggNoticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalController {
    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String index(ModelMap mm) {
        mm.addAttribute("noticias", noticiaServicio.obtenerNoticiasActivas());
        return "index";
    }

}
