package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import com.openhtmltopdf.pdfboxout.PDFontSupplier;
import org.apache.pdfbox.glyphlayout.awt.GlyphLayoutProcessorAwt;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 *
 */
public class PdfBoxGlyphLayoutExample {
    public static void main(String[] args) throws Exception {
        new PdfBoxGlyphLayoutExample().run();
    }

    public void run() throws IOException, FontFormatException {
        File out = new File("target/GlyphLayoutHtmlExample.pdf");
        out.getParentFile().mkdirs();

        try (PDDocument doc = new PDDocument()) {
            GlyphLayoutProcessorAwt glyphLayoutProcessor = new GlyphLayoutProcessorAwt();

            PDFont arimo = glyphLayoutProcessor.loadFont(doc, this.getClass().getResourceAsStream("src/main/resources/fonts/arimo/Arimo-Regular.ttf"));

            PdfBoxRendererBuilderGlyphLayoutAwt builder = new PdfBoxRendererBuilderGlyphLayoutAwt();
            builder.useGlyphLayoutProcessor(glyphLayoutProcessor);

            // Load example HTML from resources
            InputStream is = PdfBoxGlyphLayoutExample.class.getResourceAsStream("/org/openpdf/pdf/GlyphLayoutExample.html");
            Objects.requireNonNull(is, "Could not find GlyphLayoutHtmlExample.html resource");


            String html = new String(IOUtils.toByteArray(is), StandardCharsets.UTF_8);

            builder.withProducer("openhtmltopdf-pdfbox-glyphlayout-example");
            builder.toStream(new FileOutputStream(out));
            builder.usePDDocument(doc);
            builder.useFont(new PDFontSupplier(arimo), "Arimo");
            builder.withHtmlContent(html, "");
            builder.run();
        }
    }
}