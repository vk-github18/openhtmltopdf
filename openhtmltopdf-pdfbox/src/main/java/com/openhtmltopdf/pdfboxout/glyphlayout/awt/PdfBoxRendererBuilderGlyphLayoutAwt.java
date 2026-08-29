package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import com.openhtmltopdf.css.constants.IdentValue;
import com.openhtmltopdf.extend.impl.FSNoOpCacheStore;
import com.openhtmltopdf.outputdevice.helper.AddedFont;
import com.openhtmltopdf.outputdevice.helper.BaseDocument;
import com.openhtmltopdf.outputdevice.helper.PageDimensions;
import com.openhtmltopdf.outputdevice.helper.UnicodeImplementation;
import com.openhtmltopdf.pdfboxout.PDFontSupplier;
import com.openhtmltopdf.pdfboxout.PdfBoxFontResolver;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilderState;
import com.openhtmltopdf.util.LogMessageId;
import com.openhtmltopdf.util.OpenUtil;
import com.openhtmltopdf.util.XRLog;

import java.io.Closeable;
import java.util.logging.Level;

public class PdfBoxRendererBuilderGlyphLayoutAwt extends PdfRendererBuilder {
    private PdfRendererBuilderStateGlyphLayoutAwt state;

    public PdfBoxRendererBuilderGlyphLayoutAwt() {
        this(new PdfRendererBuilderStateGlyphLayoutAwt());
    }

    public PdfBoxRendererBuilderGlyphLayoutAwt(PdfRendererBuilderStateGlyphLayoutAwt state) {
        super(state);
        this.state = state;
        }


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

    public PdfBoxRenderer buildPdfRenderer(Closeable diagnosticConsumer) {
        UnicodeImplementation unicode = new UnicodeImplementation(state._reorderer, state._splitter, state._lineBreaker,
                state._unicodeToLowerTransformer, state._unicodeToUpperTransformer, state._unicodeToTitleTransformer, state._textDirection,
                state._charBreaker);

        PageDimensions pageSize = new PageDimensions(state._pageWidth, state._pageHeight, state._isPageSizeInches);

        BaseDocument doc = new BaseDocument(state._baseUri, state._html, state._document, state._file, state._uri);


        PdfBoxRendererGlyphLayoutAwt glyphLayoutRenderer = new PdfBoxRendererGlyphLayoutAwt(doc, unicode, pageSize, state, diagnosticConsumer);
        PdfBoxRenderer ignore = super.buildPdfRenderer(diagnosticConsumer);

        return glyphLayoutRenderer;
    }

}
