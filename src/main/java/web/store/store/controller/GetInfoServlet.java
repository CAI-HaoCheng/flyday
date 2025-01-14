package web.store.store.controller;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.store.store.entity.Store;

@WebServlet("/store/getinfo")
public class GetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1062017833925367218L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Store store = (Store)request.getAttribute("store");
		
		if (store == null) {
			store = new Store();
			store.setMessage("無會員資訊");
			store.setSuccessful(false);
		} else {
			store.setSuccessful(true);
		}
		writePojo2Json(response, store);
		
	}
}
