package testapp.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.Loop;

public class LoopTestPage {

    @Component(parameters = {"source=1..5", "value=var:currentElement"})
    private Loop loop;
}
