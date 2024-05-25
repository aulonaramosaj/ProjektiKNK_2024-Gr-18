package model.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class QytetariFilter {
    private String nrPersonal;
    private String emri;
    private String mbiemri;
    private Date ditelindja;
    private Integer adresaId;  // Add an address ID field
    private String formatPattern = "yyyy-MM-dd";

    // Add adresaId to the constructor
    public QytetariFilter(String nrPersonal, String emri, String mbiemri, Date ditelindja, Integer adresaId) {
        this.nrPersonal = nrPersonal;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.ditelindja = ditelindja;
        this.adresaId = adresaId;
    }

    public String buildQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        StringJoiner query = new StringJoiner(" AND ", " WHERE ", "");

        if (nrPersonal != null && !nrPersonal.isEmpty()) {
            query.add("NrPersonal = '" + nrPersonal + "'");
        }
        if (emri != null && !emri.isEmpty()) {
            query.add("Emri LIKE '" + emri + "%'");
        }
        if (mbiemri != null && !mbiemri.isEmpty()) {
            query.add("Mbiemri LIKE '" + mbiemri + "%'");
        }
        if (ditelindja != null) {
            query.add("Ditelindja = '" + sdf.format(ditelindja) + "'");
        }
        if (adresaId != null) {
            query.add("Adresa = " + adresaId);  // Add condition for address ID
        }

        return query.toString();
    }
}
