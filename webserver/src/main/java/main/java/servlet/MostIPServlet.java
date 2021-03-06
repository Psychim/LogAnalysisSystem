package main.java.servlet;
import main.java.model.MostIP;
import main.java.service.MostIPService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MostIPServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws  ServletException , IOException{
        request.setCharacterEncoding("utf-8");

        int num=Integer.parseInt(request.getParameter("number"));

        List<MostIP> mIPlist=null;
        try{
            MostIPService mip=new MostIPService();
            mIPlist=mip.GetMostIP(num);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
