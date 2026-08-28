package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public class PdbBoxRendererGlyphLayoutAwt extends PdfBoxRenderer {
    private PdfRendererBuilderStateGlyphLayoutAwt state;

    protected PDPageContentStream initPage(
            PDDocument doc, float w, float h, int mainPageIndex, int shadowPageIndex) throws IOException {

        PDPageContentStream cs = super.initPage(doc, w, h, mainPageIndex, shadowPageIndex);
        cs.setGlyphLayoutProcessor(state._glyphLayoutProcessor);
        return cs;
    }


}
