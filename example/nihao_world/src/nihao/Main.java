package nihao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticMDCBinder;

class Main {
    private String hello() {
        return "";
    }
    Logger logger = LoggerFactory.getLogger(Main.class);
    StaticMDCBinder binder = StaticMDCBinder.getSingleton();
}

