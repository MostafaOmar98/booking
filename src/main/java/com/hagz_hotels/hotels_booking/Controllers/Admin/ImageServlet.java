package com.hagz_hotels.hotels_booking.Controllers.Admin;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

    private String imagePath;

    public void init() throws ServletException {
        this.imagePath = "/home/bekh/IdeaProjects/hotels-booking/images";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestedImage = request.getPathInfo();

        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));

        String contentType = getServletContext().getMimeType(image.getName());

        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        System.out.println(image.toPath());
        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());
    }

}