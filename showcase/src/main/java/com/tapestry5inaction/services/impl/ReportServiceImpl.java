package com.tapestry5inaction.services.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.tapestry5inaction.entities.DateParameter;
import com.tapestry5inaction.entities.NumericParameter;
import com.tapestry5inaction.entities.ReportParameter;
import com.tapestry5inaction.entities.StringParameter;
import com.tapestry5inaction.services.ReportService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    public InputStream getReportData(String reportName,
                                     List<ReportParameter> parameters) {

        Document document = new Document();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, stream);
            document.open();

            document.add(new Paragraph(String.format("Report '%s'", reportName)));

            for (ReportParameter param : parameters) {
                document.add(new Paragraph(String.format("Parameter %s:%s", param.getName(), param.getValue())));
            }

        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
        return new ByteArrayInputStream(stream.toByteArray());
    }

    public List<ReportParameter> getReportParameters(String reportName) {

        ReportParameter date = new StringParameter("Cost Center");
        ReportParameter number = new NumericParameter("Account Number");
        ReportParameter text = new DateParameter("Date");

        return Arrays.asList(date, number, text);
    }

    public ReportParameter findReportParameter(String reportName, String name) {
        List<ReportParameter> parameters = getReportParameters(reportName);

        for (ReportParameter next : parameters) {
            if (next.getName().equals(name)) {
                return next;
            }
        }
        return null;
    }

}
