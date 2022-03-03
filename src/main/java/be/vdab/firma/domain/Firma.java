package be.vdab.firma.domain;

import be.vdab.firma.constraints.OndernemingsNummer;

public class Firma {
    @OndernemingsNummer
    private long ondernemingsNummer;

    public long getOndernemingsNummer() {
        return ondernemingsNummer;
    }

    public void setOndernemingsNummer(long ondernemingsNummer) {
        this.ondernemingsNummer = ondernemingsNummer;
    }
}
