package com.uniovi.sdi;

import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		ConcurrentHashMap<String, Integer> carrito = (ConcurrentHashMap<String, Integer>) request.getSession()
				.getAttribute("carrito");
		// No hay carrito, creamos uno y lo insertamos en sesión
		if (carrito == null) {
			carrito = new ConcurrentHashMap<String, Integer>();
			request.getSession().setAttribute("carrito", carrito);
		}
		String producto = request.getParameter("producto");
		if (producto != null) {
			insertarEnCarrito(carrito, producto);
		}

		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);

	}

	private void insertarEnCarrito(Map<String, Integer> carrito, String claveProducto) {
		if (carrito.get(claveProducto) == null) {
			carrito.put(claveProducto, new Integer(1));
		} else {
			int numeroArticulos = (Integer) carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer(numeroArticulos + 1));
		}
	}

	private String carritoEnHTML(Map<String, Integer> carrito) {
		String carritoEnHTML = "";

		for (String key : carrito.keySet()) {
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		}
		return carritoEnHTML;
	}
}
