package coreservlets;

import static util.ConnectServer.PORT_CONFIGURE;
import static util.ConnectServer.SERVER_IP;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectServer;
import util.DefaultSocket;
import util.ServletUtilities;

/**
 * Servlet implementation class coreservlet.AutoServlet
 */
@WebServlet("/coreservlet.AutoServlet")
public class AutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectServer s1;

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() {
		socketProcess(3, null);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Configue a Car - Select a model";
		String css = "style.css";
		out.println(ServletUtilities.headWithTitle(title, css));
		out.println("<body>\n" + "<div class='main'><div class='header'><h1>Welcome to configure a car</h1></div>\n");
		Object a = s1.getFromServer();
		if (a == null) {
			out.println("<p>nullException</p>");
		} else {
			String list = (String) a;
			String autoList[] = list.split(",");
			out.println("<div class='middle'><h4>Select an Automobile from the following list to configure:</h4>");
			out.println("<form action='ConfigureServlet' method='post' id='form1'>");
			out.println("<label> Model: <select name=model>");
			for (int i = 0; i < autoList.length; i++) {
				out.println("<option>" + autoList[i] + "</option>");
			}
			out.println("</select></label></form>\n");
			out.println(
					"<button type='submit' form='form1' value='Submit'>submit</button><div class='space'></div></div>");
			out.println("<footer>2019 Spring 35B Lab6</footer>");
		}
		out.println("</div></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void socketProcess(int request, Object toServer) {
		s1 = new DefaultSocket(SERVER_IP, PORT_CONFIGURE, request, toServer);
		s1.establishConnection();
		s1.handleConnection();
		s1.closeConnection();
	}

}