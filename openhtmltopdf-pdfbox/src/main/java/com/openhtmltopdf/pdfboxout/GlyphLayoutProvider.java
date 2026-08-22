package com.openhtmltopdf.pdfboxout;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Adapter interface that allows clients to provide glyph layout shaping for
 * PDFBox-backed rendering. Implementations should return an array compatible
 * with PDContentStreamAdapter.showTextWithPositioning / showTextWithPositioning
 * (an Object[] containing Strings and Floats alternately) or null to indicate
 * no special shaping.
 */
public interface GlyphLayoutProvider {
    /**
     * Layout the given text for the supplied PDFont and font size.
     *
     * @param font the PDFont that will be used for drawing
     * @param text the text to layout
     * @param info justification/letter-spacing info (may be null)
     * @param desc internal font description for additional font metadata
     * @param fontSize font size in PDFBox units (as passed into drawStringFast)
     * @param device the PdfBoxFastOutputDevice performing the drawing (may be used to access PDDocument)
     * @return an Object[] suitable for PdfContentStreamAdapter.drawStringWithPositioning or null to fall back
     */
    Object[] layout(PDFont font, String text, com.openhtmltopdf.render.JustificationInfo info,
                    com.openhtmltopdf.pdfboxout.PdfBoxFontResolver.FontDescription desc,
                    float fontSize, PdfBoxFastOutputDevice device);
}
