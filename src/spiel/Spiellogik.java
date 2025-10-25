package spiel;

import java.util.Random;
import java.util.Scanner;

public class Spiellogik {

	private Spieler spielerA = new Spieler();
	private Spieler spielerB = new Spieler();
	private Spielfeld spielfeld = new Spielfeld();
	Scanner sc = new Scanner(System.in);

	public boolean gueltigeKoordinaten(int posX, int posY) {
		if (posX >= spielerA.getZEILE() || posX < 0 || posY >= spielerA.getSPALTE() || posY < 0) {
			return false;
		}
		return true;
	}

	public int bestimmeLaenge(int startPosX, int startPosY, int endPosX, int endPosY) {

		if (startPosX == endPosX) {

			if (startPosY < endPosY) {
				return (endPosY + 1) - startPosY;
			} else if (startPosY > endPosY) {
				return (startPosY + 1) - endPosY;
			}

		} else if (startPosY == endPosY) {
			if (startPosX < endPosX) {
				return (endPosX + 1) - startPosX;
			} else if (startPosX > endPosX) {
				return (startPosX + 1) - endPosX;
			}
		}
		return -1;
	}

	public boolean setShip(Spieler spieler, int schiffLaenge, int startPosX, int startPosY, int endPosX, int endPosY) {

		if (gueltigeKoordinaten(startPosX, startPosY) && gueltigeKoordinaten(endPosX, endPosY)) {
			if (schiffLaenge == bestimmeLaenge(startPosX, startPosY, endPosX, endPosY)) {

				if (startPosX == endPosX) {
					if (startPosY < endPosY) {
						for (int i = 0; i < schiffLaenge; i++) {
							if (spieler.getFeld()[startPosX][startPosY - 1 + i] != 'S') {
								spieler.getFeld()[startPosX][startPosY - 1 + i] = 'S';
							} else {
								System.out.println("Dort ist schon ein Schiff");
								return false;
							}

						}
						return true;
					} else if (startPosY > endPosY) {
						for (int i = 0; i < schiffLaenge; i++) {
							if (spieler.getFeld()[startPosX][startPosY - 1 - i] != 'S') {
								spieler.getFeld()[startPosX][startPosY - 1 - i] = 'S';
							} else {
								System.out.println("Dort ist schon ein Schiff");
								return false;
							}

						}
						return true;
					}
				} else if (startPosY == endPosY) {
					if (startPosX < endPosX) {
						for (int i = 0; i < schiffLaenge; i++) {
							if (spieler.getFeld()[startPosX + i][startPosY - 1] != 'S') {
								spieler.getFeld()[startPosX + i][startPosY - 1] = 'S';
							} else {
								System.out.println("Dort ist schon ein Schiff");
								return false;
							}

						}
						return true;
					} else if (startPosX > endPosX) {
						for (int i = 0; i < schiffLaenge; i++) {
							if (spieler.getFeld()[startPosX - i][startPosY - 1] != 'S') {
								spieler.getFeld()[startPosX - i][startPosY - 1] = 'S';
							} else {
								System.out.println("Dort ist schon ein Schiff");
								return false;
							}

						}
						return true;
					}
				}

			} else {
				System.out.println("Schiffslaenge stimmt nicht mit den Koordinaten überein");
			}
		}
		return false;
	}

	public void setshipRandomly(Spieler spieler) {
		Random r = new Random();

		for (int i = 0; i < 7; i++) {
			boolean gesetzt = false;

			while (!gesetzt) {

				int posX = r.nextInt(10);
				int posY = r.nextInt(9) + 1;
				int vertikalOderHorizontal = r.nextInt(2);

				if (i > 3) {
					if (vertikalOderHorizontal == 0) {
						if (gueltigeKoordinaten(posX, +1)) {
							if (setShip(spieler, spieler.getUboote().getLAENGE(), posX, posY, posX, posY + 1)) {
								gesetzt = true;
							}
						}

					} else {
						if (gueltigeKoordinaten(posX + 1, posY)) {
							if (setShip(spieler, spieler.getUboote().getLAENGE(), posX, posY, posX + 1, posY)) {
								gesetzt = true;
							}
						}

					}
				} else if (i >= 3 && i < 5) {

					if (vertikalOderHorizontal == 0) {
						if (gueltigeKoordinaten(posX, posY + 2)) {
							if (setShip(spieler, spieler.getZerstoerer().getLAENGE(), posX, posY, posX, posY + 2)) {
								gesetzt = true;
							}
						}
					} else {
						if (gueltigeKoordinaten(posX + 2, posY)) {
							if (setShip(spieler, spieler.getZerstoerer().getLAENGE(), posX, posY, posX + 2, posY)) {
								gesetzt = true;
							}
						}

					}
				} else if (i == 5) {
					if (vertikalOderHorizontal == 0) {
						if (gueltigeKoordinaten(posX, posY + 3)) {
							if (setShip(spieler, spieler.getKreuzer().getLAENGE(), posX, posY, posX, posY + 3)) {
								gesetzt = true;
							}
						}

					} else {
						if (gueltigeKoordinaten(posX + 3, posY)) {
							if (setShip(spieler, spieler.getKreuzer().getLAENGE(), posX, posY, posX + 3, posY)) {
								gesetzt = true;
							}
						}

					}
				} else {
					if (vertikalOderHorizontal == 0) {
						if (gueltigeKoordinaten(posX, posY + 4)) {
							if (setShip(spieler, spieler.getSchlachtschiff().getLAENGE(), posX, posY, posX, posY + 4)) {
								gesetzt = true;
							}
						}

					} else {
						if (gueltigeKoordinaten(posX + 4, posY)) {
							if (setShip(spieler, spieler.getSchlachtschiff().getLAENGE(), posX, posY, posX + 4, posY)) {
								gesetzt = true;
							}
						}

					}
				}
			}
		}
	}

	public boolean shoot(Spieler anvisierterSpieler, int posX, int posY) {

		if (gueltigeKoordinaten(posX, posY - 1)) {
			if (anvisierterSpieler.getFeld()[posX][posY - 1] == 'S') {
				System.out.println("Getroffen\n");
				anvisierterSpieler.getFeld()[posX][posY - 1] = '*';
				anvisierterSpieler.getFeldVerdeckt()[posX][posY - 1] = '*';
				anvisierterSpieler.lebenVerloren();
				return true;
			} else if (anvisierterSpieler.getFeld()[posX][posY - 1] == ' ') {
				System.out.println("Das Wasser getroffen");
				anvisierterSpieler.getFeld()[posX][posY-1] = 'O';
				anvisierterSpieler.getFeldVerdeckt()[posX][posY - 1] = 'O';
				return true;
			} else if (anvisierterSpieler.getFeld()[posX][posY - 1] == '*') {
				System.out.println("Das getroffene Feld nochmal getroffen");
				return false;
			}

		}
		System.out.println("Ungueltige Koordinaten");
		return false;

	}

	public void start(Spieler spieler) {

		for (int i = 0; i < 7; i++) {
			spielfeld.printField(spieler.getFeld());
			boolean richitgeEingabe = false;
			while (!richitgeEingabe) {

				if (i < 3) {
					System.out.println("Schiff der Laenge " + spieler.getUboote().getLAENGE() + " setzen\n");
					System.out.println("Startpunkt Zeile angeben\n");
					int startPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Startpunkt Spalte angeben\n");
					int startPosY = sc.nextInt();
					System.out.println("Endpunkt Zeile angeben\n");
					int endPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Endpunkt Spalte angeben\n");
					int endPosY = sc.nextInt();
					if (setShip(spieler, spieler.getUboote().getLAENGE(), startPosX, startPosY, endPosX, endPosY)) {
						richitgeEingabe = true;
					}

				} else if (i >= 3 && i < 5) {
					System.out.println("Schiff der Laenge " + spieler.getZerstoerer().getLAENGE() + " setzen\n");
					System.out.println("Startpunkt Zeile angeben\n");
					int startPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Startpunkt Spalte angeben\n");
					int startPosY = sc.nextInt();
					System.out.println("Endpunkt Zeile angeben\n");
					int endPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Endpunkt Spalte angeben\n");
					int endPosY = sc.nextInt();
					if (setShip(spieler, spieler.getZerstoerer().getLAENGE(), startPosX, startPosY, endPosX, endPosY)) {
						richitgeEingabe = true;
					}
				} else if (i == 5) {
					System.out.println("Schiff der Laenge " + spieler.getKreuzer().getLAENGE() + " setzen\n");
					System.out.println("Startpunkt Zeile angeben\n");
					int startPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Startpunkt Spalte angeben\n");
					int startPosY = sc.nextInt();
					System.out.println("Endpunkt Zeile angeben\n");
					int endPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Endpunkt Spalte angeben\n");
					int endPosY = sc.nextInt();
					if (setShip(spieler, spieler.getKreuzer().getLAENGE(), startPosX, startPosY, endPosX, endPosY)) {
						richitgeEingabe = true;
					}
				} else {
					System.out.println("Schiff der Laenge " + spieler.getSchlachtschiff().getLAENGE() + " setzen\n");
					System.out.println("Startpunkt Zeile angeben\n");
					int startPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Startpunkt Spalte angeben\n");
					int startPosY = sc.nextInt();
					System.out.println("Endpunkt Zeile angeben\n");
					int endPosX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
					System.out.println("Endpunkt Spalte angeben\n");
					int endPosY = sc.nextInt();
					if (setShip(spieler, spieler.getSchlachtschiff().getLAENGE(), startPosX, startPosY, endPosX,
							endPosY)) {
						richitgeEingabe = true;
					}
				}

			}

		}
	}

	public void update() {
		spielfeld.erstelleSpielfeld(spielerA.getFeld());
		spielfeld.erstelleSpielfeld(spielerA.getFeldVerdeckt());
		spielfeld.erstelleSpielfeld(spielerB.getFeld());
		spielfeld.erstelleSpielfeld(spielerB.getFeldVerdeckt());
		// start(spielerA);
		// start(spielerB);
		setshipRandomly(spielerA);
		setshipRandomly(spielerB);
		boolean istDran = false;
		boolean gewonnen = false;

		do {
			if (!istDran) {
				System.out.println("Spieler 1\n");
				spielfeld.printFields(spielerB.getFeldVerdeckt(), spielerA.getFeld());
				System.out.println("Zeile: \n");
				int posX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
				System.out.println("Spalte: \n");
				int posY = sc.nextInt();
				if (shoot(spielerB, posX, posY)) {
					istDran = true;
				}

			} else {
				System.out.println("Spieler 2\n");
				spielfeld.printFields(spielerA.getFeldVerdeckt(), spielerB.getFeld());
				System.out.println("Zeile: \n");
				int posX = (int) (Character.toLowerCase((sc.next().charAt(0))) - 97);
				System.out.println("Spalte: \n");
				int posY = sc.nextInt();
				if (shoot(spielerA, posX, posY)) {
					istDran = false;
				}

			}
			if (spielerA.getLeben() == 0) {
				System.out.println("Spieler 2 hat gewonnen");
				gewonnen = true;
			} else if (spielerB.getLeben() == 0) {
				System.out.println("Spieler 1 hat gewonnen");
				gewonnen = true;
			}

		} while (!gewonnen);
	}

}
