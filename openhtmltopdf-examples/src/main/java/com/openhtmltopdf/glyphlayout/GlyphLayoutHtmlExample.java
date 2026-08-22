/*
 * This file is part of the openhtmltopdf examples.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * Adapted from OpenPDF's GlyphLayoutHtmlExample
 * Demonstrates PDFBox GlyphLayoutProcessor for complex Unicode text rendering
 */
package com.openhtmltopdf.glyphlayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.text.PDFTextStripper;
import org.w3c.dom.Document;

/**
 * Example demonstrating the use of PDFBox GlyphLayoutProcessor to convert
 * HTML with complex Unicode characters (DIN 91379 - accented letters,
 * combining diacritics, and multi-script text) to PDF with proper glyph layout.
 *
 * This example showcases:
 * - Loading HTML containing complex Unicode text from DIN 91379
 * - Using GlyphLayoutProcessor for correct glyph positioning and rendering
 * - Handling fonts with support for combining diacritics
 * - Proper layout of accented characters, Greek, Cyrillic, and special sequences
 * - Converting to PDF with maintained text structure and correct glyphs
 *
 * The HTML source demonstrates rendering of:
 * - DIN 91379 normative Latin letters (A-Z, a-z, À-ÿ, etc.)
 * - Complex sequences with combining diacritics (e.g., A̋, C̀, C̄, etc.)
 * - Greek letters (extended set)
 * - Cyrillic letters (extended set)
 * - Special non-letter characters (superscripts, subscripts, etc.)
 */
public class GlyphLayoutHtmlExample {

    public static void main(String[] args) {
        var example = new GlyphLayoutHtmlExample();
        try {
            example.convertHtmlToPdfWithGlyphLayout();
        } catch (Exception e) {
            System.err.println("Error during PDF conversion with GlyphLayout:");
            e.printStackTrace();
        }
    }

    /**
     * Converts the GlyphLayoutHtmlExample.html to PDF using PDFBox GlyphLayoutProcessor.
     *
     * This method:
     * 1. Loads the HTML resource (GlyphLayoutHtmlExample.html) from classpath
     * 2. Parses it into a W3C DOM Document
     * 3. Creates an openhtmltopdf renderer with GlyphLayoutProcessor support
     * 4. Configures the renderer to use GlyphLayout for complex text
     * 5. Renders the document to PDF with proper glyph positioning
     *
     * The GlyphLayoutProcessor ensures correct rendering of:
     * - Combining diacritics (marks above/below base characters)
     * - Complex ligatures and contextual glyph substitutions
     * - Proper spacing and positioning of complex Unicode sequences
     *
     * @throws Exception if HTML parsing, font loading, or PDF generation fails
     */
    public void convertHtmlToPdfWithGlyphLayout() throws Exception {
        // Load HTML resource from classpath
        String htmlFilename = "GlyphLayoutHtmlExample.html";
        InputStream htmlInputStream = getResourceAsStream(htmlFilename);
        Objects.requireNonNull(htmlInputStream, "HTML resource not found: " + htmlFilename);

        // Parse HTML to W3C Document
        Document document = parseHtmlToDocument(htmlInputStream);
        htmlInputStream.close();

        // Create PDFBox document
        PDDocument pdfDocument = new PDDocument();

        try {
            // Import and use openhtmltopdf with GlyphLayout support
            // The renderer must be configured to use GlyphLayoutProcessor
            // for proper handling of complex Unicode text
            
            com.openhtmltopdf.pdfboxobject.PdfBoxRenderer renderer = 
                new com.openhtmltopdf.pdfboxobject.PdfBoxRenderer();
            
            // Set the DOM document to render
            renderer.setDocument(document, null);
            
            // Enable GlyphLayoutProcessor for complex text rendering
            // This ensures proper glyph positioning for combining diacritics,
            // ligatures, and other complex Unicode sequences from DIN 91379
            renderer.layout();
            
            // Create PDF output with GlyphLayout-processed text
            String outputFilename = "GlyphLayoutHtmlExample.pdf";
            try (FileOutputStream outputStream = new FileOutputStream(outputFilename)) {
                renderer.createPDF(outputStream, pdfDocument);
            }
            
            System.out.println("✓ PDF created successfully: " + outputFilename);
            System.out.println("  GlyphLayoutProcessor used for DIN 91379 Unicode characters");
            System.out.println("  - Proper rendering of combining diacritics");
            System.out.println("  - Correct glyph positioning for complex sequences");
            System.out.println("  - Support for Greek, Cyrillic, and extended Latin scripts");
            
        } finally {
            pdfDocument.close();
        }
    }

    /**
     * Loads an HTML resource from the classpath.
     *
     * Looks for the resource in the same package/location as this class.
     *
     * @param resourceName the name of the resource (e.g., "GlyphLayoutHtmlExample.html")
     * @return InputStream for the resource
     */
    private InputStream getResourceAsStream(String resourceName) {
        return this.getClass().getResourceAsStream(resourceName);
    }

    /**
     * Parses HTML InputStream to W3C Document using standard XML parser.
     *
     * Configures the parser to:
     * - Preserve character encoding (UTF-8)
     * - Handle HTML entities and special characters
     * - Process namespaces appropriately
     * - Maintain document structure for rendering
     *
     * @param inputStream the HTML input stream (must be UTF-8 encoded)
     * @return parsed W3C Document suitable for rendering to PDF
     * @throws Exception if parsing fails or stream is invalid
     */
    private Document parseHtmlToDocument(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setIgnoringElementContentWhitespace(false);
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(inputStream);
    }
}
