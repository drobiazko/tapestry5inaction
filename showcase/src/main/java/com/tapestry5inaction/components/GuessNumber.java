package com.tapestry5inaction.components;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Random;

/**
 * Component that renders an entire UI for the Guess Number game, known from the Java EE tutorial.
 *
 * @since 1.0
 *
 * @tapestrydoc
 */
public class GuessNumber {

    /**
     * Used to configure the minimal number to guess.
     *
     * @since 1.0
     */
    @Parameter("0")
    @Property
    private int min;

    /**
     * Used to configure the maximal number to guess.
     *
     * @since 1.0
     */
    @Parameter("10")
    @Property
    private int max;

    @Property
    private Integer answer;

    @Persist
    private Integer number;

    @Inject
    private AlertManager alertManager;

    @Inject
    private Messages messages;

    void setupRender() {
        if (number == null) {
            Random random = new Random();
            number = random.nextInt((max - min) + 1) + min;
        }
    }

    void onSuccessFromForm() {
        final int result = number.compareTo(answer);

        if (result == 0) {
            alertManager.info(messages.format("correct-answer", answer));
            number = null;
        } else {
            String key = result < 0 ? "try-smaller" : "try-larger";

            alertManager.error(messages.format(key, answer));
        }
    }
}