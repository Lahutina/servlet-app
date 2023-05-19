package config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class WrappedRequest extends HttpServletRequestWrapper {
 
    private final String user;
    private final String role;
    private final HttpServletRequest realRequest;
 
    public WrappedRequest(String user, String role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.realRequest = request;
    }
 
    @Override
    public boolean isUserInRole(String role) {
        if (this.role == null) {
            return this.realRequest.isUserInRole(role);
        }
        return this.role.equals(role);
    }
 
    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }
        return () -> user;
    }
}
