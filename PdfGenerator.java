package com.resume;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import java.awt.Color;
import java.io.OutputStream;

public class PdfGenerator {

    public static void generate(OutputStream out,
                                String name, String email, String phone,
                                String linkedin, String github,
                                String summary, String education, String skills,
                                String experience, String projects, String certifications) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Fonts
            Font nameFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font contactFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
            Font linkFont = new Font(Font.HELVETICA, 10, Font.UNDERLINE, new Color(0, 0, 255)); // Blue & underlined
            Font sectionFont = new Font(Font.HELVETICA, 12, Font.BOLD, new Color(0, 102, 204)); // Blue section headers
            Font contentFont = new Font(Font.HELVETICA, 11, Font.NORMAL);

            // Name (centered)
            Paragraph namePara = new Paragraph(name, nameFont);
            namePara.setAlignment(Element.ALIGN_CENTER);
            namePara.setSpacingAfter(2);
            document.add(namePara);

            // Contact Info with Hyperlinks
            Paragraph contact = new Paragraph();
            contact.setAlignment(Element.ALIGN_LEFT);

            if (!phone.isEmpty()) {
                contact.add(new Chunk("☎ " + phone + "  |  ", contactFont));
            }

            if (!email.isEmpty()) {
                Anchor emailLink = new Anchor("✉ " + email, linkFont);
                emailLink.setReference("mailto:" + email);
                contact.add(emailLink);
                contact.add(new Chunk("  |  ", contactFont));
            }

            if (!linkedin.isEmpty()) {
                String linkedInURL = linkedin.startsWith("http") ? linkedin : "https://" + linkedin;
                Anchor linkedinLink = new Anchor(linkedin, linkFont);
                linkedinLink.setReference(linkedInURL);
                contact.add(linkedinLink);
                contact.add(new Chunk("  |  ", contactFont));
            }

            if (!github.isEmpty()) {
                String githubURL = github.startsWith("http") ? github : "https://" + github;
                Anchor githubLink = new Anchor(github, linkFont);
                githubLink.setReference(githubURL);
                contact.add(githubLink);
            }

            document.add(contact);
            document.add(Chunk.NEWLINE); // spacing after contact

            // Horizontal line
            LineSeparator line = new LineSeparator(1f, 100f, Color.BLACK, Element.ALIGN_CENTER, -2);
            document.add(new Chunk(line));

            // Sections
            addSection(document, "Professional Summary", summary, sectionFont, contentFont);
            addSection(document, "Education", education, sectionFont, contentFont);
            addSection(document, "Technical Skills", skills, sectionFont, contentFont);

            if (experience != null && !experience.trim().isEmpty()) {
                addSection(document, "Experience", experience, sectionFont, contentFont);
            }

            addSection(document, "Projects", projects, sectionFont, contentFont);
            addSection(document, "Certifications", certifications, sectionFont, contentFont);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addSection(Document doc, String title, String content, Font sectionFont, Font contentFont)
            throws DocumentException {
        if (content == null || content.trim().isEmpty()) return;

        Paragraph titlePara = new Paragraph(title, sectionFont);
        titlePara.setSpacingBefore(10);
        titlePara.setSpacingAfter(2);
        doc.add(titlePara);

        Paragraph contentPara = new Paragraph(content, contentFont);
        contentPara.setSpacingAfter(8);
        doc.add(contentPara);
    }
}
