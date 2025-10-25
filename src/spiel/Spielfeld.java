package spiel;

public class Spielfeld {

	public void erstelleSpielfeld(char[][] spielfeld) {
		for (int i = 0; i < spielfeld.length; i++) {
			for (int j = 0; j < spielfeld[i].length; j++) {
				spielfeld[i][j] = ' ';
			}
		}
	}

	public void printField(char[][] spielfeld) {
		System.out.print("  ");
		for (int i = 0; i < spielfeld[0].length; i++) {
			System.out.print((i + 1) + " ");
		}
		System.out.println();
		for (int i = 0; i < spielfeld.length; i++) {
			System.out.print((char) (i + 65) + " ");
			for (int j = 0; j < spielfeld[i].length; j++) {
				System.out.print(spielfeld[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void printFields(char[][] spielfeldGegner, char[][] spielfeldSpieler) {
		System.out.println("Gegner: \n");
		printField(spielfeldGegner);
		for (int i = 0; i < spielfeldGegner[0].length; i++) {
			System.out.print("---");
		}
		System.out.println();
		System.out.println("Spieler: \n");
		printField(spielfeldSpieler);

	}

}
