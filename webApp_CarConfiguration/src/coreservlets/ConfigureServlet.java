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

import model.Automobile;
import util.ConnectServer;
import util.DefaultSocket;
import util.ServletUtilities;

/**
 * Servlet implementation class ConfigureServlet
 */
@WebServlet("/ConfigureServlet")
public class ConfigureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectServer s1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() {
		// TODO Auto-generated constructor stub
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
		String title = "Configue a Car - Configure your car";
		String css = "style.css";
		out.println(ServletUtilities.headWithTitle(title, css));
		out.println("<body>\n <div class='main'><div class='header'><H1>Configure your car!</H1>\n</div>");
		String modelName = request.getParameter("model");
		if (modelName == null) {
			modelName = "";
		}
		Object a = getObjectFromServer(4, modelName);
		if (a != null && a instanceof Automobile) {
			Automobile auto = (Automobile) a;
			out.println("<div class='middle'><form action='index.jsp' method='post' id='form2'>");
			out.println("<table><thead>\n <tr>\n <th colspan='2'>" + modelName + "</th>\n </tr>\n </thead>");
			out.println("<tbody>");
			out.println("<tr>\n <td>Base Price</td><td>$" + auto.getBasePrice() + "</td></tr>\n");
			StringBuffer optSetName = new StringBuffer();
			for (int i = 0; i < auto.getOptSetSize(); i++) {
				String name = auto.getOptionSetName(i);
				optSetName.append(name + ',');
				out.println("<tr>\n");
				out.println("<td>" + name + "</td>\n");
				out.println("<td><select name='" + name + "'>");
				for (int j = 0; j < auto.getOptionListSize(i); j++) {
					out.println("<option>" + auto.getOptionName(i, j) + "</option>");
				}
				out.println("</select></td>\n </tr>\n");
			}
			out.println("</tbody></table></form>\n");
			out.println("<button type='submit' form='form2' value='Submit'>submit</button></div>");
			out.println("<footer>2019 Spring 35B Lab6</footer>");

			request.getSession().setAttribute("modelName", modelName);
			request.getSession().setAttribute("optSetName", optSetName.toString());

		} else {
			out.println("<h4>Sorry! You entered a wrong page.</h4>");
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

	public Object getObjectFromServer(int request, String modelName) {
		socketProcess(request, modelName);
		return s1.getFromServer();
	}

}
