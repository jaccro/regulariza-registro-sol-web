package com.jaccro.Service;

import java.util.HashMap;
import java.util.Map;

import com.jaccro.enums.AuthorizationType;
import com.jaccro.model.APIResponse;
import com.jaccro.util.APIUtil;

public class SolicitudWebService {

    public String registar(String entry) {

        try {
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("Content-Type", "text/xml");
            properties.put("Username", "Uws0001");
            properties.put("Password", "uTdp$1%2o18");

            APIResponse response = APIUtil.inkove(
                    "https://gclientes.toyotaperu.com.pe/WebServiceSolCotS/WServiceSolCotS", "POST",
                    AuthorizationType.BASIC, "Uws0001", "uTdp$1%2o18", null, null, null, 15, 15, properties, entry);

            if (response.getCode() >= 200 && response.getCode() < 300)
                return response.getData();
            else
                return response.getCode() + ": " + response.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
