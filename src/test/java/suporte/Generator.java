package suporte;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class Generator {
    public static String dataHoraParaArquivo(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMddhhmmss").format(ts);

    }
}
