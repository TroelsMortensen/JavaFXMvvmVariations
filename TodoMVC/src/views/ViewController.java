package views;

import core.VMFactory;
import core.ViewHandler;

public interface ViewController {
    void init(ViewHandler viewHandler, VMFactory vmFactory, Object argForView);
}
