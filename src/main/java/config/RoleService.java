package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RoleService {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    // role - urls for that role
    private static final Map<String, List<String>> mapConfig = new HashMap<>();
    private static final List<String> notSecuredUrls = new ArrayList<>();

    static {
        init();
    }

    private static void init() {

        notSecuredUrls.add("/index");
        notSecuredUrls.add("/login");
        notSecuredUrls.add("/logout");
        notSecuredUrls.add("/register");
        notSecuredUrls.add("/servlet_app_war_exploded");

        List<String> urlPatternsUser = new ArrayList<>();
        urlPatternsUser.add("/userInfo");
        urlPatternsUser.add("/user");

        mapConfig.put(ROLE_USER, urlPatternsUser);

        List<String> urlPatternsAdmin = new ArrayList<>();
        urlPatternsAdmin.add("/createUser");
        urlPatternsAdmin.add("/deleteUser");
        urlPatternsAdmin.add("/editUser");
        urlPatternsAdmin.add("/userInfo");
        urlPatternsAdmin.add("/admin");

        mapConfig.put(ROLE_ADMIN, urlPatternsAdmin);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

    public static boolean urlsAccessibleToAll(String servletPath) {
        for (String url : notSecuredUrls) {
            if (url.equals(servletPath)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<String> allRoles = RoleService.getAllAppRoles();

        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = RoleService.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static String getUrlPattern(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();

        String urlPattern;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
            return urlPattern;
        }
        urlPattern = servletPath;

        boolean has = hasUrlPattern(servletContext, urlPattern);
        if (has) {
            return urlPattern;
        }
        int i = servletPath.lastIndexOf('.');
        if (i != -1) {
            String ext = servletPath.substring(i + 1);
            urlPattern = "*." + ext;
            has = hasUrlPattern(servletContext, urlPattern);

            if (has) {
                return urlPattern;
            }
        }
        return "/";
    }

    private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {

        Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();

        for (String servletName : map.keySet()) {
            ServletRegistration sr = map.get(servletName);

            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }

        }
        return false;
    }

}
