package com.eggnews.EggNoticias.controladores;

import com.eggnews.EggNoticias.entidades.Noticia;
import com.eggnews.EggNoticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping({"/", ""})
    public String index(ModelMap mm) {
        mm.addAttribute("noticias", noticiaServicio.obtenerNoticiasActivas());
        return "index";
    }

    @PostMapping("/culo")
    public String buscador(@RequestParam String aBuscar, ModelMap mm) {
        int id = 0;
        try {
            Noticia n = noticiaServicio.obtenerNoticiaPorTitulo("%"+aBuscar+"%");
            id = n.getIdNoticia();
            mm.addAttribute("noticia", n);
        } catch(Exception e) {
            mm.addAttribute("warning", "Noticia no encontrada");
            mm.addAttribute("noticias", noticiaServicio.obtenerNoticiasActivas());
            return "index";
        }
        return "redirect:/noticia/"+id;
    }

    @GetMapping("/{id}")
    public String noticia(@PathVariable int id, ModelMap mm) {
        mm.addAttribute("noticia", noticiaServicio.obtenerNoticiaPorId(id));
        return "noticia";
    }

}
