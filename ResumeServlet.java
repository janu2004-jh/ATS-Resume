package com.resume;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;

//@WebServlet("/generate")
public class ResumeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Collect inputs from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String linkedin = request.getParameter("linkedin");
        String github = request.getParameter("github");
        String summary = request.getParameter("summary");
        String education = request.getParameter("education");
        String skills = request.getParameter("skills");
        String experience = request.getParameter("experience");
        String projects = request.getParameter("projects");
        String certifications = request.getParameter("certifications");

        String format = request.getParameter("format"); // "pdf" or "docx"

        try (OutputStream out = response.getOutputStream()) {

            if ("pdf".equalsIgnoreCase(format)) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=resume.pdf");

                PdfGenerator.generate(out, name, email, phone, linkedin, github,
                        summary, education, skills, experience, projects, certifications);

            } else if ("docx".equalsIgnoreCase(format)) {
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                response.setHeader("Content-Disposition", "attachment; filename=resume.docx");

                DocxGenerator.generate(out, name, email, phone, linkedin, github,
                        summary, education, skills, experience, projects, certifications);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid format selected.");
            }

            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Resume generation failed.");
        }
    }
}
