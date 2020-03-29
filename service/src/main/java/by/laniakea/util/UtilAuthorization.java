package by.laniakea.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UtilAuthorization {

    public String urlPage(SimpleGrantedAuthority simpleGrantedAuthority){
        String pageUrl;

        String role = simpleGrantedAuthority.getAuthority();

        if(role.equals("READER")){
            pageUrl = "redirect:reader/room";

        }
        else if(role.equals("ADMIN")){
            pageUrl = "redirect:admin/room";

        }
        else if(role.equals("LIBRA")){
            pageUrl = "redirect:libra/room";

        }
        else {
            pageUrl = "redirect:login";
        }

        return pageUrl;
    }
}
