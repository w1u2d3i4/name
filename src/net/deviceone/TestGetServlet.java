package net.deviceone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import net.deviceone.helper.HttpHelper;

public class TestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestGetServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		JSONObject obj = new JSONObject();

		JSONObject requestO = new JSONObject();
		JSONObject responseO = new JSONObject();
		try {
			JSONObject header = HttpHelper.getRequestHeader(request);
			header.put("method", request.getMethod());
			requestO.put("header", header);
			requestO.put("parameters", HttpHelper.getRequestParameters(request));
			obj.put("request", requestO);

			responseO.put("header", HttpHelper.getResponseHeader(response));
			responseO.put("data", "GET成功!!!");
			obj.put("response", responseO);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().print(obj.toString());
	}
}