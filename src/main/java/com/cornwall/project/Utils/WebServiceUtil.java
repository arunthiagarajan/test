package com.cornwall.project.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.json.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class WebServiceUtil {
    List<String> value = new ArrayList<>();

    public String getResponse(String soapRequest, String endpoint) {
        HttpResponse response = null;
        String result = "";
        try {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
            HttpPost request = new HttpPost(endpoint);
            StringEntity params = new StringEntity(soapRequest);
            request.setEntity(params);
            response = httpClient.execute(request);

            result = EntityUtils.toString(response.getEntity());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Experimental code to be commentted during normal run
        //TCPClient.main(endpoint + "#" + soapRequest + "#" + result);
        return result;
    }

    public String getResponse(String soapRequest, String endpoint, String SOAPAction) {
        HttpResponse response = null;
        String result = "";
        try {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
            HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
            HttpPost request = new HttpPost(endpoint);
            request.addHeader("SOAPAction", SOAPAction);
            StringEntity params = new StringEntity(soapRequest);
            request.setEntity(params);
            response = httpClient.execute(request);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Experimental code to be commentted during normal run
        //TCPClient.main(endpoint + "#" + SOAPAction + "#" + soapRequest + "#" + result);
        return result;
    }

    public List<String> getJSonValue(String jSOnResponse, String keyName) {
        JsonReader jsonReader = Json.createReader(new StringReader(jSOnResponse));
        JsonObject jsonObject = jsonReader.readObject();
        value.clear();
        return navigateTree(jsonObject, "", keyName);
    }

    public List<String> navigateTree(JsonValue tree, String key, String keyName) {
        if (key != null)
            switch (tree.getValueType()) {
                case OBJECT:
                    JsonObject object = (JsonObject) tree;
                    for (String name : object.keySet()) {
                        navigateTree(object.get(name), name, keyName);
                    }
                    break;
                case ARRAY:
                    JsonArray array = (JsonArray) tree;
                    if (key.contains(keyName)) {
                        value.add(array.toString());
                    }
                    for (JsonValue val : array)
                        navigateTree(val, null, keyName);
                    break;
                case STRING:
                    JsonString st = (JsonString) tree;
                    if (key.contains(keyName)) {
                        value.add(st.getString());
                    }
                    break;
                case NUMBER:
                    JsonNumber num = (JsonNumber) tree;
                    if (key.contains(keyName)) {
                        value.add(num.toString());
                    }
                    break;
                case TRUE:
                case FALSE:
                case NULL:
                    break;
            }
        return value;
    }

    public String SOAPResponse(String soapRequest) {
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://claimcenterproxy.scor.dlg/cc/soap/IClaimAPI");
            HttpEntity entity = new ByteArrayEntity(soapRequest.getBytes("UTF-8"));
            request.setEntity(entity);
            request.addHeader("SOAPAction", "");
            HttpResponse response = httpClient.execute(request);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getNodeValue(String xml, String node) {
        String value = "";
        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(xml));
            doc = builder.parse(src);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        value = doc.getElementsByTagName(node).item(0).getTextContent();
        return value;
    }

    public void getResponse()
    {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("https://6qi59dv7a7.execute-api.eu-west-1.amazonaws.com/dev/login");
            String jSonRequest = new String(Files.readAllBytes(Paths.get("C:\\Users\\arunt\\Desktop\\Test.txt")));
            StringEntity params = new StringEntity(jSonRequest);
            request.addHeader("content-type", "application/json");
            //request.addHeader("Authorization", "Basic dXBzdHJlYW06cGFzc3dvcmQ=");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            //Log.info("ssssssssstttttttttttttttaaaaaaaaaaaaaaaaaaatttttttttttttttttuuuuuuuuuuuusssssssss" + response.getStatusLine());
            System.out.println("ssssssssstttttttttttttttaaaaaaaaaaaaaaaaaaatttttttttttttttttuuuuuuuuuuuusssssssss" + response.getStatusLine());
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("result ====================== " + result);
            // Log.info("result ====================== " + result);
            // handle response here...
        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
        }
    }
    }

