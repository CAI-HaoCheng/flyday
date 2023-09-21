package web.act.controller.Act;

import ecpay.payment.integration.domain.AioCheckOutALL;
import web.act.service.EcPayService;
import web.act.service.impl.EcPayServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.SERVER_URL;;


@WebServlet("/Act/ActECPay")
public class ActECPayServlet extends HttpServlet {
//    public static final String SERVER_URL = " https://55c3-36-226-8-232.ngrok.io";
    private static final long serialVersionUID = -3935509715372119008L;
    //    public static final ECPayCheckoutService SERVICE = new ECPayCheckoutService();
    public static final EcPayService SERVICE = new EcPayServiceImpl();
//呼叫綠界介面並將資料帶進
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        AioCheckOutALL obj = json2Pojo(req, AioCheckOutALL.class); // 前端網頁JSON資料傳後端
//        String reurl = SERVER_URL + req.getContextPath() + "/Act/pay?actno=" + obj.getCustomField1() + "&memno=" + obj.getCustomField2() + "&Payment=" + obj.getCustomField3();//Servlet路徑
        String reurl = SERVER_URL + req.getContextPath() + "/Act/PayAct?actno=" + obj.getCustomField1() + "&payment=" + obj.getCustomField2();//Servlet路徑，設定API路徑
        String form = SERVICE.ecpayCheckout(obj, reurl); // 將資料往service傳送
        System.out.println(form);
        req.getSession().setAttribute("ECPayForm", form);

//        String url = "/Act/ECPayform.jsp";
//        writePojo2Json(resp,form);
        writePojo2Json(resp, form);
//        req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + url


    }

}
