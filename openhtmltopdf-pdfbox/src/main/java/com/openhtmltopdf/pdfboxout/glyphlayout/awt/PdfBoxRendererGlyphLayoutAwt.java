package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import com.openhtmltopdf.outputdevice.helper.BaseDocument;
import com.openhtmltopdf.outputdevice.helper.PageDimensions;
import com.openhtmltopdf.outputdevice.helper.UnicodeImplementation;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 */
public class PdfBoxRendererGlyphLayoutAwt extends PdfBoxRenderer {
    PdfRendererBuilderStateGlyphLayoutAwt state;

    public  PdfBoxRendererGlyphLayoutAwt(BaseDocument doc, UnicodeImplementation unicode, PageDimensions pageSize, PdfRendererBuilderStateGlyphLayoutAwt state, Closeable diagnosticConsumer) {
        super(doc, unicode, pageSize, state, diagnosticConsumer);
        this.state = state;
    }


    @Override
    protected PDPageContentStream initPage(
            PDDocument doc, float w, float h, int mainPageIndex, int shadowPageIndex) throws IOException {

        PDPageContentStream cs = super.initPage(doc, w, h, mainPageIndex, shadowPageIndex);
        cs.setGlyphLayoutProcessor(state._glyphLayoutProcessor);
        return cs;
    }
}
