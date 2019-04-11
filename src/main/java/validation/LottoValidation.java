package validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LottoValidation 객체는 로또번호 데이터 검증을 담당한다.
 *
 * @author 조남균
 */
public class LottoValidation implements Validation<List<Integer>> {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 45;
	public static final int NUMBER_CNT = 6;
	private static final String REGULAR_EXPRESSION = "^([0-9]+)(,[0-9]+){5}$";
	private static final String SEPARATOR = ",";
	private List<Integer> numbers;

	@Override
	public boolean check(String value) {
		if (!checkList(value)) {
			return false;
		}

		numbers = convert(value);

		return checkCountNumbers() && checkReduplicationNumbers() && checkRangeNumber();
	}

	private boolean checkList(String value) {
		if (!value.matches(REGULAR_EXPRESSION)) {
			System.out.println("입력 형식을 맞춰주세요.\n");
			return false;
		}

		return true;
	}

	private boolean checkCountNumbers() {
		if (numbers.size() != NUMBER_CNT) {
			System.out.println("6개 숫자를 입력해야 합니다.\n");
			return false;
		}

		return true;
	}

	private boolean checkReduplicationNumbers() {
		long numbersCount = numbers.stream()
				.distinct()
				.count();

		if (numbersCount != numbers.size()) {
			System.out.println("중복된 숫자가 존재합니다.\n");
			return false;
		}

		return true;
	}

	private boolean checkRangeNumber() {
		long numbersCount = numbers.stream()
				.filter(n -> (MIN_NUMBER <= n && n <= MAX_NUMBER))
				.count();

		if (numbersCount != NUMBER_CNT) {
			System.out.println("숫자의 범위는 최소 1, 최대 45 입니다.\n");
			return false;
		}

		return true;
	}

	@Override
	public List<Integer> convert(String value) {
		return Arrays.stream(value.split(SEPARATOR))
				.map(n -> Integer.valueOf(n))
				.collect(Collectors.toList());
	}
}
