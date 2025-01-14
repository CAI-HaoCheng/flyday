package web.act.controller.Act;

import core.entity.Core;
import core.util.CommonUtil;
import web.act.entity.Act;
import web.act.service.ActService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

@WebServlet("/Act/remove")
public class removeAct extends HttpServlet {
    private ActService service;

    public void init() throws ServletException {
        service = CommonUtil.getBean(getServletContext(), ActService.class);

    }
// 移除揪團資訊
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer id = json2Pojo(req, Act.class).getActno();
        final Core core = new Core();
        if(id == null){
            core.setMessage("查無此id");
            core.setSuccessful(false);
        }else {
            core.setSuccessful(service.deleteAct(id));
        }
        writePojo2Json(resp,core);
    }
}
