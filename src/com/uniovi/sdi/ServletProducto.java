package com.uniovi.sdi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/productos")
public class ServletProducto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		 throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	
    	List<Producto> productos = new ArrayList<Producto>();

		ProductosService ps = new ProductosService();
		productos = ps.getProductos();
	

		// Retornar la vista con parámetro "carrito"
		request.setAttribute("productosTienda", productos);
		getServletContext().getRequestDispatcher("/vista-productos.jsp").forward(request, response);
    		
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException {
     doGet( request,response );
    }

}
