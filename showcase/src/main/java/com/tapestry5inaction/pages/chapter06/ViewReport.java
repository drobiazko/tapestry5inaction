package com.tapestry5inaction.pages.chapter06;

import com.tapestry5inaction.entities.DateParameter;
import com.tapestry5inaction.entities.NumericParameter;
import com.tapestry5inaction.entities.ReportParameter;
import com.tapestry5inaction.services.ReportService;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ViewReport {
    @Inject
    @Property
    private ReportService reportService;

    @Property
    private ReportParameter currentParameter;

    private String reportName = "report";

    @Property
    private List<ReportParameter> parameters;

    @Inject
    private Block stringBlock;

    @Inject
    private Block dateBlock;

    @Inject
    private Block numericBlock;

    void pageAttached() {
        parameters = reportService.getReportParameters(reportName);
    }

    void onAfterSubmit() {
        for (ReportParameter next : parameters) {
            if (next.getName().equals(currentParameter.getName())) {
                next.setValue(currentParameter.getValue());
                return;
            }
        }
    }

    Object onSuccess() {
        return new StreamResponse() {

            public String getContentType() {
                return "application/pdf";
            }

            public InputStream getStream() throws IOException {
                return reportService.getReportData(reportName, parameters);
            }

            public void prepareResponse(final Response response) {
                response.setHeader("Content-disposition",
                        "attachment;filename=report.pdf");

            }

        };
    }

    public Block getActiveBlock() {
        if (currentParameter instanceof NumericParameter) {
            return numericBlock;
        } else if (currentParameter instanceof DateParameter) {
            return dateBlock;
        }
        return stringBlock;
    }

    public ValueEncoder getEncoder() {
        return new ValueEncoder<ReportParameter>() {

            public String toClient(ReportParameter clientValue) {
                return clientValue.getName();
            }

            public ReportParameter toValue(String clientValue) {
                return reportService.findReportParameter(reportName,
                        clientValue);
            }

        };
    }
}