package ui;

import domain.Game;
import validation.BonusNoValidation;
import validation.LottoValidation;
import validation.MoneyValidation;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class GameUI {
	private Game game;

	public GameUI() {
		game = new Game();
	}

	public void run() {
		try(Scanner sc = new Scanner(System.in)) {
			int money = askMoney(sc);
			game.setMoney(money);
			System.out.println(game.getLotto());

			List<Integer> numbers = askNumbers(sc);
			int bonusNo = askBonusNo(sc, numbers);
		}
	}

	private int askMoney(Scanner sc) {
		String message = "구입금액을 입력해 주세요.";
		Validation<Integer> validation = new MoneyValidation();

		Input<Integer> input = new Input<>(sc, message, validation);

		return input.ask();
	}

	private List<Integer> askNumbers(Scanner sc) {
		String message = "지난 주 당첨번호를 입력해 주세요.";
		Validation<List<Integer>> validation = new LottoValidation();

		Input<List<Integer>> input = new Input<>(sc, message, validation);

		return input.ask();
	}

	private int askBonusNo(Scanner sc, List<Integer> numbers) {
		String message = "보너스 볼을 입력해 주세요";
		Validation<Integer> validation = new BonusNoValidation(numbers);

		Input<Integer> input = new Input<>(sc, message, validation);

		return input.ask();
	}
}
