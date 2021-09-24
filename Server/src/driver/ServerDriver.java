package driver;

import a​dapter.*;
import server.Servable;
import static server.Servable.PORT_LOADANDCONFIGURE;

public class ServerDriver {
    public static void main(String[] args) {
        Servable a1 = new BuildAuto();
        a1.serve(PORT_LOADANDCONFIGURE);
    }


}
