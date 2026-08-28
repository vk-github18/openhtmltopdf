package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import org.apache.pdfbox.glyphlayout.awt.GlyphLayoutProcessorAwt;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class PdfBoxGlyphLayoutHtmlExample {
    public static void main(String[] args) throws Exception {
        File out = new File("target/GlyphLayoutHtmlExample.pdf");
        out.getParentFile().mkdirs();

        try (PDDocument doc = new PDDocument()) {
            GlyphLayoutProcessorAwt glyphLayoutProcessor = new GlyphLayoutProcessorAwt();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useGlyphLayoutProcessor(glyphLayoutProcessor);

            // Load example HTML from resources
            InputStream is = PdfBoxGlyphLayoutHtmlExample.class.getResourceAsStream("/org/openpdf/pdf/GlyphLayoutHtmlExample.html");
            if (is == null) {
                System.err.println("Could not find GlyphLayoutHtmlExample.html resource");
                return;
            }

            String html = new String(is.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);

            builder.withProducer("openhtmltopdf-glyphlayout-example");
            builder.toStream(new FileOutputStream(out));
            builder.usePDDocument(doc);
            builder.useFont(new PdfRendererBuilder.PDFontSupplier() {
                @Override
                public org.apache.pdfbox.pdmodel.font.PDFont get() throws IOException {
                    // Let the renderer resolve fonts from CSS and local fonts.
                    return null;
                }
            }, "Arial");
            builder.withHtml(html);

            builder.run();
        }
    }
}