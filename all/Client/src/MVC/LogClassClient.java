package MVC;

import java.util.logging.*;

public class LogClassClient {
    private static final Logger logr = Logger.getLogger("OIClient");

    static Logger getLog(){
        if(logr.getHandlers().length > 0)
            return logr;

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try{
            FileHandler fh = new FileHandler("C:\\Users\\Trolo\\Desktop\\Vjeci\\Škola\\4.semester\\VAVA\\project_OrganizeIT\\Client\\src\\MVC\\ClientLogger.log", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        }
        catch (Exception e){
            logr.log(Level.SEVERE, "File logger not working ", e);
        }

        return logr;

    }
}
