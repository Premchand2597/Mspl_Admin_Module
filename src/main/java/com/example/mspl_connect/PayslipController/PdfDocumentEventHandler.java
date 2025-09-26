package com.example.mspl_connect.PayslipController;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class PdfDocumentEventHandler implements IEventHandler {

    // Static variable to keep track of the count
    private static int executionCount = 0;

    @Override
    public void handleEvent(Event event) {

        executionCount++;

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
        Rectangle pageSize = pdfDoc.getDefaultPageSize();

        float x = pageSize.getX() + 20;
        float y = pageSize.getY() + (pageSize.getHeight() / 2) + 6; // Move the bottom line up

        float width = pageSize.getWidth() - 40;
        float height = (pageSize.getHeight() / 2.11f) + 2; // Adjust height to reduce top space in the bottom half

        // Adjust the position of the bottom line by moving it upwards by at least 20px
        float adjustedY = y + 20;

        // Print the execution count
        System.out.println("handleEvent executed " + executionCount + " times");

        // Draw the left, top, and right lines of the rectangle
        canvas.moveTo(x, y);
        canvas.lineTo(x + width, y); // Top line
        canvas.lineTo(x + width, y + height); // Right line
        canvas.lineTo(x, y + height); // Left line
        canvas.lineTo(x, adjustedY); // Bottom line moved up by 20px
        canvas.closePath();
        canvas.stroke();
    }
}


