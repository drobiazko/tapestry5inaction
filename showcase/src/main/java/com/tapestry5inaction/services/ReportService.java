package com.tapestry5inaction.services;

import com.tapestry5inaction.entities.ReportParameter;

import java.io.InputStream;
import java.util.List;

public interface ReportService {

    List<ReportParameter> getReportParameters(String reportName);

    ReportParameter findReportParameter(String reportName,
                                        String name);

    InputStream getReportData(String reportName,
                              List<ReportParameter> parameters);
}
