package model.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class QytetariFilter {
    private String nrPersonal;
    private String emri;
    private String mbiemri;
    private Date ditelindja;
    private String formatPattern = "yyyy-MM-dd";

    public QytetariFilter(String nrPersonal, String emri, String mbiemri, Date ditelindja) {
        this.nrPersonal = nrPersonal;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.ditelindja = ditelindja;
    }

    public String buildQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        StringJoiner query = new StringJoiner(" AND ", " WHERE ", "");

        if (nrPersonal != null && !nrPersonal.isEmpty()) {
            query.add("nrPersonal = '" + nrPersonal + "'");
        }
        if (emri != null && !emri.isEmpty()) {
            query.add("emri LIKE '" + emri + "%'");
        }
        if (mbiemri != null && !mbiemri.isEmpty()) {
            query.add("mbiemri LIKE '" + mbiemri + "%'");
        }
        if (ditelindja != null) {
            query.add("ditelindja = '" + sdf.format(ditelindja) + "'");
        }

        return query.toString();
    }
}