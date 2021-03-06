package main.java.servlet;

import com.alibaba.fastjson.JSON;
import main.java.model.UserDev;
import main.java.model.UserPreference;
import main.java.service.UserDevService;
import main.java.service.UserPreferenceService;
import main.java.util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yxy on 9/7/17.
 */
public class UserDPServlet extends HttpServlet{


    protected void getJson(HttpServletRequest request,HttpServletResponse response,Object object){
        response.setContentType("text/html;charset=UTF-8");
        //禁用缓存，确保网页信息是最新数据
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", -10);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String jsonStr= JSON.toJSONString(object);
            out.print(jsonStr);
            out.flush();
            System.out.println("out");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            out.close();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException ,IOException{
        doPost(request,response);
        //response.getWriter().write("111");
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws  ServletException , IOException{
        request.setCharacterEncoding("utf-8");

        String phone = request.getParameter("phone");
        String preference = request.getParameter("preference");
        UserDev ud  = null;
        List<UserPreference> upflist=null;
        List<UserDev> udlist = new ArrayList<UserDev>();
        List<String> phonelist = new ArrayList<String>();
        try{
            UserDevService uds = new UserDevService();
            UserPreferenceService ups = new UserPreferenceService();
            request.setAttribute("message",true);
            if(phone == null || phone.equals("")){
                if(preference == "")
                {
                    request.setAttribute("message",false);
                }
                else
                {
                    upflist = ups.SelectByLabelname(preference);
                    for(int i = 0;i < upflist.size();i++)
                    {
                        String temPhone = upflist.get(i).getPhone();
                        phonelist.add(temPhone);
                    }
                    udlist = uds.SelectByPhoneList(phonelist);
                    request.setAttribute("userdevicelist",udlist);
                    request.setAttribute("preferencelist",upflist);
                    request.setAttribute("preference",preference);
                    request.setAttribute("phonelist",phonelist);
                    request.setAttribute("device",null);
                }

            }
            else
            {
                ud = uds.SelectByPhone(phone);
                upflist = ups.SelectByUser(phone);
                request.setAttribute("device",ud);
                request.setAttribute("preferencelist",upflist);
                request.setAttribute("phone",phone);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        /*java.sql.Date date= java.sql.Date.valueOf("2010-12-01");
        CityRateData cd=new CityRateData();
        cd.setCityRateData(date,"南京",10,20,0.5);
        crdlist.add(cd);*/
//        getJson(request,response,crdlist);
       request.getRequestDispatcher("personas2.jsp").forward(request,response);
    }


//    public static void main(String args[]){
//        List<CityRateData> crdlist=null;
//        java.sql.Date date= java.sql.Date.valueOf("2017-01-01");
//        CityRateDataService crds=new CityRateDataService();
//        crdlist=crds.SelectByDate(date);
//        String jsonStr= JSON.toJSONString(crdlist);
//        System.out.println(jsonStr);
//    }
}
