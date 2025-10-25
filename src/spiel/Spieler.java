package spiel;

public class Spieler {

	private UBoote uboote = new UBoote();
	private Zerstoerer zerstoerer = new Zerstoerer();
	private Kreuzer kreuzer = new Kreuzer();
	private Schlachtschiff schlachtschiff = new Schlachtschiff();
	private final int ZEILE = 10;
	private final int SPALTE = 10;
	private int leben = 21;

	private char[][] feld = new char[ZEILE][SPALTE];
	private char[][] feldVerdeckt = new char[ZEILE][SPALTE];

	public UBoote getUboote() {
		return uboote;
	}

	public void setUboote(UBoote uboote) {
		this.uboote = uboote;
	}

	public Zerstoerer getZerstoerer() {
		return zerstoerer;
	}

	public void setZerstoerer(Zerstoerer zerstoerer) {
		this.zerstoerer = zerstoerer;
	}

	public Kreuzer getKreuzer() {
		return kreuzer;
	}

	public void setKreuzer(Kreuzer kreuzer) {
		this.kreuzer = kreuzer;
	}

	public Schlachtschiff getSchlachtschiff() {
		return schlachtschiff;
	}

	public void setSchlachtschiff(Schlachtschiff schlachtschiff) {
		this.schlachtschiff = schlachtschiff;
	}

	public char[][] getFeld() {
		return feld;
	}

	public void setFeld(char[][] feld) {
		this.feld = feld;
	}

	public int getZEILE() {
		return ZEILE;
	}

	public int getSPALTE() {
		return SPALTE;
	}

	public char[][] getFeldVerdeckt() {
		return feldVerdeckt;
	}

	public void setFeldVerdeckt(char[][] feldVerdeckt) {
		this.feldVerdeckt = feldVerdeckt;
	}

	public int getLeben() {
		return leben;
	}

	public void lebenVerloren() {
		this.leben--;
	}

}
