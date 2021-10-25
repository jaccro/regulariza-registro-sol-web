package com.jaccro.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import com.jaccro.enums.AuthorizationType;
import com.jaccro.model.APIResponse;

public class APIUtil {

    public static APIResponse inkove(String endPoint, String method, AuthorizationType authorizationType,
            String userName, String password, String key, String keyValue, String token, int connectTimeOutInSecond,
            int readTimeOutInSecond, Map<String, String> properties, String inputString) throws Exception {

        try {
            URL url = new URL(endPoint);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod(method);
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            setAuthorization(httpConn, authorizationType, userName, password, key, keyValue, token);

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            bout.write(inputString.getBytes());
            byte[] inputBytes = bout.toByteArray();

            for (Map.Entry<String, String> entry : properties.entrySet()) {
                httpConn.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
            }

            OutputStream out = httpConn.getOutputStream();
            out.write(inputBytes);
            out.close();

            if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 299) {
                return getResponse(httpConn.getResponseCode(), getMessageResponse(httpConn));
            } else {
                return getResponse(httpConn.getResponseCode(), "");
            }
        } catch (MalformedURLException | UnsupportedEncodingException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(errors.toString());
        } catch (IOException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            throw new Exception(errors.toString());
        }

    }

    private static void setAuthorization(HttpURLConnection httpConn, AuthorizationType authorizationType,
            String userName, String password, String key, String keyValue, String token) {

        if (authorizationType == AuthorizationType.BASIC) {
            String encoded = Base64.getEncoder()
                    .encodeToString((userName + ":" + password).getBytes(StandardCharsets.UTF_8));
            httpConn.setRequestProperty("Authorization", "Basic " + encoded);
        } else if (authorizationType == AuthorizationType.APIKEY) {
            httpConn.setRequestProperty(key, keyValue);
        } else if (authorizationType == AuthorizationType.BEARER) {
            httpConn.setRequestProperty("Authorization", token);
        }
    }

    private static String getMessageResponse(HttpURLConnection httpConn)
            throws UnsupportedEncodingException, IOException {

        StringBuilder response = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"))) {

            response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        return response.toString();
    }

    private static APIResponse getResponse(Integer code, String data) {
        String message = "";
        switch (code) {
            case 200: {
                message = "OK, La solicitud ha tenido éxito.";
                break;
            }
            case 201: {
                message = "Created, La solicitud ha tenido éxito y se ha creado un nuevo recurso como resultado de ello.";
                break;
            }
            case 202: {
                message = "Accepted, La solicitud se ha recibido, pero aún no se ha actuado.";
                break;
            }
            case 203: {
                message = "Non-Authoritative Information";
                break;
            }
            case 400: {
                message = "El servidor no pudo interpretar la solicitud dada una sintaxis inválida.";
                break;
            }
            case 401: {
                message = "Es necesario autenticarse para poder acceder al recurso solicitado.";
                break;
            }
            case 403: {
                message = "No cuenta con los permisos necesarios para acceder al recurso solicitado.";
                break;
            }
            case 404: {
                message = "No se pudo encontrar el recurso solicitado.";
                break;
            }
            case 405: {
                message = "El método para obtener el recurso no está permitido.";
                break;
            }
            case 408: {
                message = "Request Timeout.";
                break;
            }
            case 411: {
                message = "El header 'Content-Length' es requerido.";
                break;
            }
            case 500: {
                message = "Hubo un error interno al procesar la solicitud.";
                break;
            }
            case 501: {
                message = "El método solicitado no está soportado por el servidor y no puede ser manejado.";
                break;
            }
            case 502: {
                message = "Bad Gateway.";
                break;
            }
            case 503: {
                message = "El servidor no está listo para manejar la petición.";
                break;
            }
            case 504: {
                message = "Gateway Timeout.";
                break;
            }
            case 998: {
                message = "Hubo un error interno en el proceso de petición.";
                break;
            }
            default: {
                message = "Error no manejado";
                break;
            }
        }

        return new APIResponse(code, message, data);
    }
}
