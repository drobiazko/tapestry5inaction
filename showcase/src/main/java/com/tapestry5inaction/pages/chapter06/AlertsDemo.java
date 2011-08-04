package com.tapestry5inaction.pages.chapter06;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.AlertStorage;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.ReorderProperties;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.EnumSelectModel;

@ReorderProperties("severity,duration,message")
public class AlertsDemo {
    @Inject
    private AlertManager alertManager;

    @Inject
    private Messages messages;

    @Property
    @Validate("required")
    private Duration duration;

    @Property
    @Validate("required")
    private Severity severity;

    @Property
    @Validate("required")
    private String message;

    @SessionState
    private AlertStorage storage;

    public SelectModel getSeverityModel() {
        return new EnumSelectModel(Severity.class, messages);
    }


    public SelectModel getDurationModel() {
        return new EnumSelectModel(Duration.class, messages);
    }

    void onSuccess() {
        alertManager.alert(duration, severity, message);
    }

    void onActionFromReset() {
        storage.dismissAll();
    }
}