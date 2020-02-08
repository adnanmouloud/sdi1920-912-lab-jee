package com.uniovi.sdi;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BorrarCarrito
 */
@WebServlet("/BorrarCarrito")
public class BorrarCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		ConcurrentHashMap<String, Integer> carrito = (ConcurrentHashMap<String, Integer>) request.getSession()
				.getAttribute("carrito");
	
		String producto = request.getParameter("producto");
		if (producto != null) {
			carrito = borrarDeCarrito(carrito, producto);
		}

		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}

	private ConcurrentHashMap<String, Integer> borrarDeCarrito(ConcurrentHashMap<String, Integer> carrito, String claveProducto) {
		
			int numeroArticulos = (Integer) carrito.get(claveProducto).intValue();
			
			if(numeroArticulos>1) {
				carrito.put(claveProducto, new Integer(numeroArticulos - 1));
			}
			
			else {
				carrito.remove(claveProducto);
			}
			
			return carrito;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
