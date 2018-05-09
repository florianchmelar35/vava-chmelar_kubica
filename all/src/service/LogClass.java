package service;

import java.util.logging.*;

/**
 * trieda, na spravovanie logovania pre server cast
 */

public class LogClass {
    private final static Logger logr = Logger.getLogger("OIServer");

    /**
     * metoda na vratenie spravneho loggeru
     * @return
     */

    /**
     * staticka metoda, ktora vrati nastaveny loger pre nejaku metodu
     * @return
     */
    static Logger getLog(){
        if(logr.getHandlers().length > 0)
            return logr;

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try{
            FileHandler fh = new FileHandler("C:\\DATA\\School\\4. semester\\VAVA\\project_OrganizeIT\\src\\ServerLogger.log", true);
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
