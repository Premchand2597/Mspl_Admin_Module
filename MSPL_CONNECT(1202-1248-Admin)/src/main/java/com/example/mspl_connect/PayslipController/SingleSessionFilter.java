package com.example.mspl_connect.PayslipController;

import com.itextpdf.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class SingleSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, java.io.IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        Boolean isApplicationRunning = (Boolean) session.getAttribute("isApplicationRunning");
        if (isApplicationRunning != null && isApplicationRunning) {
            httpResponse.getWriter().write("Application is already running in another tab");
            return;
        }

        session.setAttribute("isApplicationRunning", true);
        chain.doFilter(request, response);
        session.setAttribute("isApplicationRunning", false);
    }

    @Override
    public void destroy() {
    }
}
