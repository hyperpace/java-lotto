package ui;

import validation.MoneyValidation;
import validation.Validation;

import java.util.Scanner;

public class GameUI {
	public void run() {
		try(Scanner sc = new Scanner(System.in)) {
			int money = askMoney(sc);
		}
	}
}
