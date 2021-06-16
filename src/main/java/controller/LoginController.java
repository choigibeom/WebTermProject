package controller;

import service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.IOException;
import service.LoginService;

public class LoginController implements Controller {
    private final LoginService loginService = new LoginService();

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(url.equals("/Login/login")) {
            HttpSession session = request.getSession();
            String id = "";
            String pw = "";
            int loginResult;
            modelAndView.setViewName("Login/login");
            if(request.getMethod().equals("POST"))
            {
                id = request.getParameter("id");
                pw = request.getParameter("pw");
                loginResult = this.loginService.Login(id, pw);
                if(loginResult == 1)
                {
                    session.setAttribute("userID", id);
                    session.setAttribute("userName", "회원");
                    System.out.println("로그인 성공");
                    modelAndView.setViewName("main");
                }
            }
        }
        else {
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return modelAndView;
    }
}
