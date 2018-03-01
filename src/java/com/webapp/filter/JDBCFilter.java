/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.webapp.conn.ConnectionUntils;
import com.webapp.untils.MyUntils;

/**
 *
 * @author viquy
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
 
    public JDBCFilter() {
    }
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }
 
    @Override
    public void destroy() {
 
    }

    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = servletPath;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
        }
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException { 
        HttpServletRequest req = (HttpServletRequest) request;
        if (this.needJDBC(req)) {
            System.out.println("Open Connection for: " + req.getServletPath());
            Connection conn = null;
            try {
                conn = ConnectionUntils.getConnection();
                conn.setAutoCommit(false);
                MyUntils.storeConnection(request, conn);
                chain.doFilter(request, response);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                ConnectionUntils.rollbackQuietly(conn);
                throw new ServletException();
            } finally {
                ConnectionUntils.closeQuietly(conn);
            }
        }
        else {
            chain.doFilter(request, response);
        }
 
    }
 
}