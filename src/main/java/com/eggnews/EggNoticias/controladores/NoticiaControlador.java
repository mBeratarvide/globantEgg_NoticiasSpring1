package com.eggnews.EggNoticias.controladores;

import com.eggnews.EggNoticias.entidades.Noticia;
import com.eggnews.EggNoticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        boolean muchas = false;
        try {
            List<Noticia> n = noticiaServicio.obtenerNoticiaPorTitulo("%"+aBuscar+"%");
            if(n.size() > 1) {
                muchas = true;
                mm.addAttribute("noticias", n);
            } else {
                id = n.get(0).getIdNoticia();
                mm.addAttribute("noticia", n.get(0));
            }
        } catch(Exception e) {
            mm.addAttribute("warning", "Noticia no encontrada");
            mm.addAttribute("noticias", noticiaServicio.obtenerNoticiasActivas());
            return "index";
        }

        if(muchas) {
            return "index";
        } else {
            return "redirect:/noticia/" + id;
        }
    }

    @GetMapping("/{id}")
    public String noticia(@PathVariable int id, ModelMap mm) {
        Noticia n = noticiaServicio.obtenerNoticiaPorId(id);
        if(n != null) {
            mm.addAttribute("noticia", n);
            return "noticia";
        } else {
            mm.addAttribute("warning", "Noticia no encontrada");
            mm.addAttribute("noticias", noticiaServicio.obtenerNoticiasActivas());
            return "index";
        }
    }

}
