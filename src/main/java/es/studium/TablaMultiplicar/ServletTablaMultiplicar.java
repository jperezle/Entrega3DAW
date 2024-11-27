package es.studium.TablaMultiplicar;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TablaMultiplicar")
public class ServletTablaMultiplicar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Modelo modelo = new Modelo();

    public ServletTablaMultiplicar() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración de la respuesta
        response.setContentType("text/html; charset=UTF-8");

        // Recibir la lista de números
        String numeros = request.getParameter("numeros");
        if (numeros == null || numeros.trim().isEmpty()) {
            response.getWriter().append("<html><head>")
                .append("<style>body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }")
                .append(".container { max-width: 600px; margin: 50px auto; padding: 20px; background: #ffffff; border-radius: 8px; box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1); }")
                .append("h1 { text-align: center; color: #333; } a { text-decoration: none; color: #007bff; }</style>")
                .append("</head><body>")
                .append("<div class='container'><h1>Error</h1>")
                .append("<p>No se proporcionaron números válidos.</p>")
                .append("<br/><a href='index.html'>Volver</a></div></body></html>");
            return;
        }

        ArrayList<Integer> listaNumeros = new ArrayList<>();
        String[] arrayNumeros = numeros.split(",");
        try {
            for (String numero : arrayNumeros) {
                listaNumeros.add(Integer.parseInt(numero.trim()));
            }
        } catch (NumberFormatException e) {
            response.getWriter().append("<html><head>")
                .append("<style>body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }")
                .append(".container { max-width: 600px; margin: 50px auto; padding: 20px; background: #ffffff; border-radius: 8px; box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1); }")
                .append("h1 { text-align: center; color: #333; } a { text-decoration: none; color: #007bff; }</style>")
                .append("</head><body>")
                .append("<div class='container'><h1>Error</h1>")
                .append("<p>Se detectaron valores no numéricos. Por favor, corrige la entrada.</p>")
                .append("<br/><a href='index.html'>Volver</a></div></body></html>");
            return;
        }

        // Generar las tablas de multiplicar
        ArrayList<String> resultado = modelo.calcularTablas(listaNumeros);

        // Mostrar los resultados
        response.getWriter().append("<!DOCTYPE html><html lang='es'><head>")
            .append("<meta charset='UTF-8'>")
            .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>")
            .append("<title>Resultados - Tablas de Multiplicar</title>")
            .append("<style>")
            .append("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 20px; }")
            .append(".container { max-width: 800px; margin: 50px auto; padding: 20px; background: #ffffff; border-radius: 8px; box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1); }")
            .append("h1 { text-align: center; color: #333; }")
            .append(".tabla { margin: 20px 0; padding: 10px; background: #f9f9f9; border: 1px solid #dddddd; border-radius: 4px; }")
            .append(".tabla p { margin: 5px 0; font-size: 16px; color: #555; }")
            .append(".volver { display: block; text-align: center; margin: 20px auto; text-decoration: none; color: #ffffff; background-color: #007bff; padding: 10px 20px; border-radius: 4px; font-size: 16px; }")
            .append(".volver:hover { background-color: #0056b3; }")
            .append("</style></head><body>")
            .append("<div class='container'>")
            .append("<h1>Resultados de las Tablas de Multiplicar</h1>");

        for (String tabla : resultado) {
            response.getWriter().append("<div class='tabla'><p>").append(tabla).append("</p></div>");
        }

        response.getWriter().append("<a class='volver' href='index.html'>Volver al formulario</a>");
        response.getWriter().append("</div></body></html>");
    }
}
