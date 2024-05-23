package model.filter;

import java.util.StringJoiner;

public class AdresaFilter {
    private String komuna;
    private String kodiPostar;
    private String fshati;
    private String rruga;
    private String numriNderteses;
    private String llojiVendbanimit;

    public AdresaFilter(String komuna, String kodiPostar, String fshati, String rruga, String numriNderteses, String llojiVendbanimit) {
        this.komuna = komuna;
        this.kodiPostar = kodiPostar;
        this.fshati = fshati;
        this.rruga = rruga;
        this.numriNderteses = numriNderteses;
        this.llojiVendbanimit = llojiVendbanimit;
    }

    public String buildQuery() {
        StringJoiner query = new StringJoiner(" AND ", " WHERE ", "");

        if (komuna != null && !komuna.isEmpty()) {
            query.add("komuna LIKE '" + komuna + "%'");
        }
        if (kodiPostar != null && !kodiPostar.isEmpty()) {
            query.add("kodiPostar = '" + kodiPostar + "'");
        }
        if (fshati != null && !fshati.isEmpty()) {
            query.add("fshati LIKE '" + fshati + "%'");
        }
        if (rruga != null && !rruga.isEmpty()) {
            query.add("rruga LIKE '" + rruga + "%'");
        }
        if (numriNderteses != null && !numriNderteses.isEmpty()) {
            query.add("numriNderteses = '" + numriNderteses + "'");
        }
        if (llojiVendbanimit != null && !llojiVendbanimit.isEmpty()) {
            query.add("llojiVendbanimit LIKE '" + llojiVendbanimit + "%'");
        }

        return query.toString();
    }
}
