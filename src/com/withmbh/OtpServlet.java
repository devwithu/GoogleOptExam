package com.withmbh;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.codec.binary.Base32;
 
public class OtpServlet extends HttpServlet {
	 
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
    	int secretSize  = 20;
    	
        int numOfScratchCodes = 0;
        int scratchCodeSize = 0;
        
        // Allocating the buffer
        byte[] buffer = new byte[secretSize + numOfScratchCodes * scratchCodeSize];
        //byte[] buffer = new byte[5 + 5 * 5];
         
        // Filling the buffer with random numbers.
        // Notice: you want to reuse the same random generator
        // while generating larger random number sequences.
        new Random().nextBytes(buffer);
 
        // Getting the key and converting it to Base32
        Base32 codec = new Base32();
        byte[] secretKey = Arrays.copyOf(buffer, secretSize);
        //byte[] secretKey = Arrays.copyOf(buffer, 5);
        byte[] bEncodedKey = codec.encode(secretKey);
         
        String encodedKey = new String(bEncodedKey);
         
        System.out.println("encodedKey : " + encodedKey);
         
        // userName과 hostName은 변수로 받아서 넣어야 하지만, 여기선 테스트를 위해 하드코딩 해줬다.
        //String url = getQRBarcodeURL(userName, hostName, encodedKey);
        String url = getQRBarcodeURL("john.doe", "gmail.com", encodedKey); 
        
        System.out.println("URL : " + url);
         
        String view = "/WEB-INF/view/otpTest.jsp";
         
        req.setAttribute("encodedKey", encodedKey);
        req.setAttribute("url", url);
         
        req.getRequestDispatcher(view).forward(req, res);
         
    }
     
    public static String getQRBarcodeURL(String user, String host, String secret) {

        String format = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chld=H|0&chl=otpauth%%3A%%2F%%2Ftotp%%2FHoldem%%2520Korea%%3A%s%%40%s%%3Fsecret%%3D%s%%26issuer%%3DHoldem%%2520Korea";
        return String.format(format, user, host, secret);
    	
    }
     
}