package com.resume;

import org.apache.poi.xwpf.usermodel.*;
import java.io.OutputStream;
import java.math.BigInteger;

public class DocxGenerator {

    public static void generate(OutputStream out,
                                String fullName, String email, String phone, String linkedin, String github,
                                String summary, String education, String skills, String experience,
                                String projects, String certifications) {
        try (XWPFDocument doc = new XWPFDocument()) {

            // Name - Centered, Bold, Large Font
            XWPFParagraph namePara = doc.createParagraph();
            namePara.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun nameRun = namePara.createRun();
            nameRun.setText(fullName);
            nameRun.setBold(true);
            nameRun.setFontSize(20);

            // Contact Info - Centered with clickable hyperlinks
            XWPFParagraph contactPara = doc.createParagraph();
            contactPara.setAlignment(ParagraphAlignment.CENTER);

            boolean hasPrevious = false;

            if (phone != null && !phone.trim().isEmpty()) {
                XWPFRun phoneRun = contactPara.createRun();
                phoneRun.setText("☎ " + phone);
                phoneRun.setFontSize(10);
                hasPrevious = true;
            }

            if (email != null && !email.trim().isEmpty()) {
                if (hasPrevious) contactPara.createRun().setText("   |   ");
                XWPFHyperlinkRun emailLink = contactPara.createHyperlinkRun("mailto:" + email);
                emailLink.setText("✉ " + email);
                emailLink.setFontSize(10);
                emailLink.setColor("0000FF");
                emailLink.setUnderline(UnderlinePatterns.SINGLE);
                hasPrevious = true;
            }

            if (linkedin != null && !linkedin.trim().isEmpty()) {
                if (hasPrevious) contactPara.createRun().setText("   |   ");
                String linkedinURL = linkedin.startsWith("http") ? linkedin : "https://" + linkedin;
                XWPFHyperlinkRun linkedinLink = contactPara.createHyperlinkRun(linkedinURL);
                linkedinLink.setText(linkedin);
                linkedinLink.setFontSize(10);
                linkedinLink.setColor("0000FF");
                linkedinLink.setUnderline(UnderlinePatterns.SINGLE);
                hasPrevious = true;
            }

            if (github != null && !github.trim().isEmpty()) {
                if (hasPrevious) contactPara.createRun().setText("   |   ");
                String githubURL = github.startsWith("http") ? github : "https://" + github;
                XWPFHyperlinkRun githubLink = contactPara.createHyperlinkRun(githubURL);
                githubLink.setText(github);
                githubLink.setFontSize(10);
                githubLink.setColor("0000FF");
                githubLink.setUnderline(UnderlinePatterns.SINGLE);
            }

            // Horizontal line after contact info
            addLineBreak(doc);

            // Sections
            addSection(doc, "Professional Summary", summary);
            addSection(doc, "Education", education);
            addSection(doc, "Technical Skills", skills);

            if (experience != null && !experience.trim().isEmpty()) {
                addSection(doc, "Experience", experience);
            }

            addSection(doc, "Projects", projects);
            addSection(doc, "Certifications", certifications);

            // Write to output stream
            doc.write(out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addSection(XWPFDocument doc, String title, String content) {
        if (content == null || content.trim().isEmpty()) return;

        // Section Title
        XWPFParagraph titlePara = doc.createParagraph();
        titlePara.setSpacingBefore(200);
        titlePara.setSpacingAfter(50);
        XWPFRun titleRun = titlePara.createRun();
        titleRun.setText(title);
        titleRun.setBold(true);
        titleRun.setFontSize(12);
        titleRun.setColor("0066CC");  // Blue section title

        // Section Content
        XWPFParagraph contentPara = doc.createParagraph();
        XWPFRun contentRun = contentPara.createRun();
        contentRun.setText(content);
        contentRun.setFontSize(11);
    }

    private static void addLineBreak(XWPFDocument doc) {
        XWPFParagraph linePara = doc.createParagraph();
        linePara.setBorderBottom(Borders.SINGLE);
    }
}
