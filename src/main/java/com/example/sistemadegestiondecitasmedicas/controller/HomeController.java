package com.example.sistemadegestiondecitasmedicas.controller;


import com.example.sistemadegestiondecitasmedicas.model.Usuario;
import com.example.sistemadegestiondecitasmedicas.service.CitaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*
    Inyeccion de dependencia
    Spring crea automaticamente una instancia de CitaService y la conecta al controlador
    pudiendo utilizar los metodos de guardas y obtener citas en el controler
    */
    @Autowired
    private CitaService citaService;

    /*Metodo get para obtener la pagina inicial o raiz del proyecto (ventana de login o registro)
    Devuelve la vista index.html
    * */
    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    /*Metodo get para obtener la pagina o dashboard de la aplicacion. A su vez solicita la lista de
    citas por medio del servicio y las inyecta en el html con el modelo.
    Devuelve la vista dashboard.html
    * */
    @GetMapping("/dashboard")
    public String home(Model model, HttpSession session){
        //Obtenemos los datos de session del usuario
        Usuario usuario = (Usuario) session.getAttribute("usuariologueado");
        //Usamos el model para enviar los datos del backend al html por medio del id citas y usuario
        model.addAttribute("usuario",usuario);
        model.addAttribute("citas", citaService.obtenerCitas(usuario));
        return "dashboard";
    }


}