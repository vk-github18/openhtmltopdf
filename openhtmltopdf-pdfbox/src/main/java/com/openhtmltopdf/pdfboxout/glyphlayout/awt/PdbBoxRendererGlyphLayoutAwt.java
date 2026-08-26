package com.openhtmltopdf.pdfboxout.glyphlayout.awt;

import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilderState;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public class PdbBoxRendererGlyphLayoutAwt extends PdfBoxRenderer {
    PdfRendererBuilderStateGlyphLayoutAwt state;



    protected PDPageContentStream initPage(
            PDDocument doc, float w, float h, int mainPageIndex, int shadowPageIndex) throws IOException {

        PDPage page = _pageSupplier.requestPage(doc, w, h, mainPageIndex, shadowPageIndex);

        PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, !_testMode);
        cs.setGlyphLayoutProcessor(state._glyphLayoutProcessor);

        _outputDevice.initializePage(cs, page, h);

        return cs;
    }


}
