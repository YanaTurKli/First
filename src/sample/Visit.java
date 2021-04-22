package sample;

import java.sql.Date;
import java.time.LocalDate;

public class Visit {
    private int id;
    private String pac;
    private String doc;
    private String diag;
    private LocalDate data;
    private String perv;
    private String vtor;
    private boolean isPerv;
    private boolean isVtor;
    private String info;
    private int id_pac;
    private int id_doc;
    private int id_diag;
    public Visit(int id, String pac,
                 String doc, String diag,
                 LocalDate data,
                 boolean isPerv, boolean isVtor,
                 String info,
                 int id_pac, int id_doc, int id_diag){
        this.id = id;
        this.pac = pac;
        this.doc = doc;
        this.diag = diag;
        this.data = data;
        this.isPerv = isPerv;
        this.isVtor = isVtor;
        this.info = info;
        this.perv = isPerv ? "Да" : "Нет";
        this.vtor = isVtor ? "Да" : "Нет";
        this.id_pac = id_pac;
        this.id_doc = id_doc;
        this.id_diag = id_diag;
    }

    public boolean isPerv() {
        return isPerv;
    }
    public String getPerv() {
        return perv;
    }

    public void setPerv(boolean perv) {
        isPerv = perv;
        this.perv = perv ? "Да" : "Нет";
    }
    public boolean isVtor() {
        return isVtor;
    }

    public String getVtor() {
        return vtor;
    }

    public void setVtor(boolean vtor) {
        isVtor = vtor;
        this.vtor = vtor ? "Да" : "Нет";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPac() {
        return id_pac;
    }

    public void setIdPac(int id_pac) {
        this.id_pac = id_pac;
    }

    public int getIdDoc() {
        return id_doc;
    }

    public void setIdDoc(int id_doc) {
        this.id_doc = id_doc;
    }

    public int getIdDiag() {
        return id_diag;
    }

    public void setIdDiag(int id_diag) {
        this.id_diag = id_diag;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }



}
