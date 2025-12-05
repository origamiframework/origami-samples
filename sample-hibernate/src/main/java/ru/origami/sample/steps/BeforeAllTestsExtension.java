package ru.origami.sample.steps;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.atomic.AtomicBoolean;

public class BeforeAllTestsExtension implements BeforeAllCallback {

    private static final AtomicBoolean FIRST_EXECUTE = new AtomicBoolean(true);

    @Override
    public void beforeAll(ExtensionContext context) {
        if (FIRST_EXECUTE.getAndSet(false)) {
            new MyServiceFixtureSteps().createRequestTable();
        }
    }
}
