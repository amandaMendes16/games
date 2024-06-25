package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Genero;
import application.model.Livro;
import application.repository.GeneroRepository;
import application.repository.LivroRepository;

@Controller
@RequestMapping("/game")
public class GamesController {

    @Autowired
    private GamesRepository gamesRepo;
    @Autowired
    private GeneroRepository generosRepo;

    @RequestMapping("/list")
    public String list(Model ui){
        ui.addAttribute("games", gamesRepo.findAll());
        return "/games/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("generos", generosRepo.findAll());
        return "/games/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("genero") long generoId) {
            
        Optional<Genero> resultado = generosRepo.findById(generoId);
        if(resultado.isPresent()) {
            Games games = new Games();
            games.setTitulo(titulo);
            games.setGenero(resultado.get());

            gamesRepo.save(games);
        }
        
        return "redirect:/games/list";
    }
    @RequestMapping("/update")
public String update (@RequestParam("id") long id, Model ui) { 
    Optional<Games> result = gamesRepo.findById(id);
if (result.isPresent()) {
ui.addAttribute("games", result.get());
ui.addAttribute("generos", generosRepo.findAll());
return "/games/update";
}
return "redirect:/games/list";
}
        
@RequestMapping(value = "/update", method = RequestMethod.POST)
public String update (@RequestParam("id") long id, @RequestParam("titulo") String titulo, @RequestParam("genero") long generoId) {
Optional<Games> result = gamesRepo.findById(id);
if (result.isPresent()) {
Optional<Genero> resultGenero = generosRepo.findById(generoId);
if (resultGenero.isPresent()) {
result.get().setTitulo (titulo);
result.get().setGenero (resultGenero.get());

gamesRepo.save(result.get());
}
}

return "redirect:/games/list";
}
@RequestMapping("/delete")
public String delete (Model ui, @RequestParam("id") long id) {
     Optional<Games> resultado = gamesRepo.findById(id);

if (resultado.isPresent()){ ui.addAttribute("games", resultado.get());
return "/games/delete";
}
return "redirect:/games/list";
}

@RequestMapping(value = "/delete", method = RequestMethod.POST)
public String delete (@RequestParam("id") long id) {
     gamesRepo.deleteById(id);
return "redirect:/games/list";


}
    
}
