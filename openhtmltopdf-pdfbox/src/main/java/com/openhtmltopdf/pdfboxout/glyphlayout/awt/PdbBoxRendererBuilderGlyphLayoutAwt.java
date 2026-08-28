package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class PdbBoxRendererBuilderGlyphLayoutAwt extends PdfRendererBuilder {

    /**
          * Supply a PDFBox glyph layout processor to be used when rendering text.
          *
          * <p>Example:
          * <pre>
          *   builder.useGlyphLayoutProcessor(new org.apache.pdfbox.glyphlayout.awt.GlyphLayoutProcessorAwt());
          * </pre>
          *
          * <p>When present, the renderer sets this processor on each {@link org.apache.pdfbox.pdmodel.PDPageContentStream}
          * created while generating the PDF. This enables using advanced glyph shaping / glyph positioning
          * backends (AWT, FOP, Harfbuzz-based, etc.) that implement PDFBox's {@code GlyphLayoutProcessorInterface}.</p>
          *
          * @param glyphLayoutProcessor an implementation of {@code org.apache.pdfbox.pdmodel.GlyphLayoutProcessorInterface}
          *                             used for complex glyph layout (may be {@code null} to disable)
          * @return this builder for fluent chaining
          */
    public PdfRendererBuilder useGlyphLayoutProcessor(org.apache.pdfbox.pdmodel.GlyphLayoutProcessorInterface glyphLayoutProcessor) {
                state._glyphLayoutProcessor = glyphLayoutProcessor;
                return this;
            }
}
