package calculator;

public class CalcTest {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setN1(2);
		calc.setOp("+");
		calc.setN2(3);
		
		System.out.println(calc.calc());
	}

}
