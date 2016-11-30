package com.example.twins.nicolinska.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceModel {
    @JsonProperty("rozniza")
    private Rozniza rozniza;

    @JsonProperty("opt")
    private Opt opt;

    public Rozniza getRozniza() {
        return rozniza;
    }

    public Opt getOpt() {
        return opt;
    }

    public String toJSON() {
        return "{\n" +
                "  \"rozniza\": {\n" +
                "    \"ballon\": " + getRozniza().getBallon() + ",\n" +
                "\"tara\": " + getRozniza().getTara() + ",\n" +
                "\"water5\": " + getRozniza().getWater5() + ",\n" +
                "\"water175g\": " + getRozniza().getWater175g() + ",\n" +
                "\"water175\": " + getRozniza().getWater175() + ",\n" +
                "\"water05g\": " + getRozniza().getWater05g() + ",\n" +
                "\"water05\": " + getRozniza().getWater05() + ",\n" +
                "\"water05Sport\": " + getRozniza().getWater05Sport() + ",\n" +
                "\"pompa\": " + getRozniza().getPompa() + ",\n" +
                "\"bookKakProdlitJizn\": " + getRozniza().getBookKakProdlitJizn() + ",\n" +
                "\"bookAtereSkleroza\": " + getRozniza().getBookAtereSkleroza() + ",\n" +
                "\"bookVodaZdorovya\": " + getRozniza().getBookVodaZdorovya() + ",\n" +
                "\"bookZdoveMateriRebenka\": " + getRozniza().getBookZdoveMateriRebenka() + ",\n" +
                "\"bookKakRoditZdorovogoBaby\": " + getRozniza().getBookKakRoditZdorovogoBaby() + ",\n" +
                "\"bookPochemyMiPolneem\": " + getRozniza().getBookPochemyMiPolneem() + ",\n" +
                "\"bookPravilnoePitanie\": " + getRozniza().getBookPravilnoePitanie() + "\n" +
                "  },\n" +
                "  \"opt\": {\n" +
                "    \"ballon\": " + getOpt().getBallon() + ",\n" +
                "\"water5\": " + getOpt().getWater5() + ",\n" +
                "\"water175g\": " + getOpt().getWater175g() + ",\n" +
                "\"water175\": " + getOpt().getWater175() + ",\n" +
                "\"water05g\": " + getOpt().getWater05g() + ",\n" +
                "\"water05\": " + getOpt().getWater05() + ",\n" +
                "\"water05Sport\": " + getOpt().getWater05Sport() + "\n" +
                "  }\n" +
                "}";
    }


    public class Rozniza {
        private double ballon;

        private double tara;

        private double water5;

        private double water175g;

        private double water175;

        private double water05g;

        private double water05;

        private double water05Sport;

        private double pompa;

        private double bookKakProdlitJizn;

        private double bookAtereSkleroza;

        private double bookVodaZdorovya;

        private double bookZdoveMateriRebenka;

        private double bookKakRoditZdorovogoBaby;

        private double bookPochemyMiPolneem;

        private double bookPravilnoePitanie;

        public void setBallon(double ballon) {
            this.ballon = ballon;
        }

        public double getBallon() {
            return this.ballon;
        }

        public void setTara(double tara) {
            this.tara = tara;
        }

        public double getTara() {
            return this.tara;
        }

        public void setWater5(double water5) {
            this.water5 = water5;
        }

        public double getWater5() {
            return this.water5;
        }

        public void setWater175g(double water175g) {
            this.water175g = water175g;
        }

        public double getWater175g() {
            return this.water175g;
        }

        public void setWater175(double water175) {
            this.water175 = water175;
        }

        public double getWater175() {
            return this.water175;
        }

        public void setWater05g(double water05g) {
            this.water05g = water05g;
        }

        public double getWater05g() {
            return this.water05g;
        }

        public void setWater05(double water05) {
            this.water05 = water05;
        }

        public double getWater05() {
            return this.water05;
        }

        public void setWater05Sport(double water05Sport) {
            this.water05Sport = water05Sport;
        }

        public double getWater05Sport() {
            return this.water05Sport;
        }

        public void setPompa(double pompa) {
            this.pompa = pompa;
        }

        public double getPompa() {
            return this.pompa;
        }

        public void setBookKakProdlitJizn(double bookKakProdlitJizn) {
            this.bookKakProdlitJizn = bookKakProdlitJizn;
        }

        public double getBookKakProdlitJizn() {
            return this.bookKakProdlitJizn;
        }

        public void setBookAtereSkleroza(double bookAtereSkleroza) {
            this.bookAtereSkleroza = bookAtereSkleroza;
        }

        public double getBookAtereSkleroza() {
            return this.bookAtereSkleroza;
        }

        public void setBookVodaZdorovya(double bookVodaZdorovya) {
            this.bookVodaZdorovya = bookVodaZdorovya;
        }

        public double getBookVodaZdorovya() {
            return this.bookVodaZdorovya;
        }

        public void setBookZdoveMateriRebenka(double bookZdoveMateriRebenka) {
            this.bookZdoveMateriRebenka = bookZdoveMateriRebenka;
        }

        public double getBookZdoveMateriRebenka() {
            return this.bookZdoveMateriRebenka;
        }

        public void setBookKakRoditZdorovogoBaby(double bookKakRoditZdorovogoBaby) {
            this.bookKakRoditZdorovogoBaby = bookKakRoditZdorovogoBaby;
        }

        public double getBookKakRoditZdorovogoBaby() {
            return this.bookKakRoditZdorovogoBaby;
        }

        public void setBookPochemyMiPolneem(double bookPochemyMiPolneem) {
            this.bookPochemyMiPolneem = bookPochemyMiPolneem;
        }

        public double getBookPochemyMiPolneem() {
            return this.bookPochemyMiPolneem;
        }

        public void setBookPravilnoePitanie(double bookPravilnoePitanie) {
            this.bookPravilnoePitanie = bookPravilnoePitanie;
        }

        public double getBookPravilnoePitanie() {
            return this.bookPravilnoePitanie;
        }
    }

    public class Opt {
        private double ballon;

        private double water5;

        private double water175g;

        private double water175;

        private double water05g;

        private double water05;

        private double water05Sport;

        public void setBallon(double ballon) {
            this.ballon = ballon;
        }

        public double getBallon() {
            return this.ballon;
        }

        public void setWater5(double water5) {
            this.water5 = water5;
        }

        public double getWater5() {
            return this.water5;
        }

        public void setWater175g(double water175g) {
            this.water175g = water175g;
        }

        public double getWater175g() {
            return this.water175g;
        }

        public void setWater175(double water175) {
            this.water175 = water175;
        }

        public double getWater175() {
            return this.water175;
        }

        public void setWater05g(double water05g) {
            this.water05g = water05g;
        }

        public double getWater05g() {
            return this.water05g;
        }

        public void setWater05(double water05) {
            this.water05 = water05;
        }

        public double getWater05() {
            return this.water05;
        }

        public void setWater05Sport(int water05Sport) {
            this.water05Sport = water05Sport;
        }

        public double getWater05Sport() {
            return this.water05Sport;
        }
    }
}