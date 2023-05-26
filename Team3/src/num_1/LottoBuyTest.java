package num_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LottoBuyTest extends JFrame {
	private JPanel pnl;
	private JLabel lbl;

	public LottoBuyTest() {
		pnl = new JPanel();
		lbl = new JLabel("가격: ");

		pnl.add(lbl);

		addLabelNumber(); // 체크박스 호출

		add(pnl);

		setSize(500, 530);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void addLabelNumber() { // 레이블 추가
		JLabel[] JLabelNumber = new JLabel[45]; // 레이블 배열 생성
		for (int i = 0; i < JLabelNumber.length; i++) { // 1 ~ 45 까지의 이름을 가진 레이블 생성
			JLabelNumber[i] = new JLabel(String.valueOf(i + 1));
			pnl.add(JLabelNumber[i]);
		}
		
		labelNumberMouseListener(JLabelNumber); // 레이블 마우스액션 (체크)
		// getPrice(checkBoxes);
		// addButton(checkBoxes); // 자동/반자동 버튼
	}

	public void labelNumberMouseListener(JLabel[] JLabelNumber) { // 마우스액션

		for (JLabel number : JLabelNumber) {
			number.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(number.getText());
				}
			});
		}
	}
	// 숫자를 저장했다가
	// 다시 같은 숫자를 받으면
	// 원소가 삭제
	
	
	/*
	 * ItemListener itemListener = new ItemListener() { int count = 0; // select되면
	 * 카운트
	 * 
	 * @Override public void itemStateChanged(ItemEvent e) { if (e.getStateChange()
	 * == ItemEvent.SELECTED) { count++; } else if (e.getStateChange() ==
	 * ItemEvent.DESELECTED) { count--; }
	 * 
	 * if (count > 6) { ((JCheckBox) e.getItem()).setSelected(false); // 선택 상태 변경 취소
	 * } int price = getPrice(checkBoxes); lbl.setText(("가격: " + price)); } };
	 * 
	 * for (JLabel number : JLabelNumber) { number.addItemListener(itemListener); //
	 * 각 체크박스에 ItemListener 설정 }
	 */

	// 버튼 비활성화?
	public void addButton(JCheckBox[] checkBoxes) { // 버튼추가
		JButton btn1 = new JButton("자동반자동테스트");
		pnl.add(btn1);

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				autoNumber(checkBoxes);
			}
		});
	}

	public int getPrice(JCheckBox[] checkBoxes) { // 총 구매 금액 출력
		int count = 0;
		int price = 0;

		for (JCheckBox checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				count++;
			}
		}
		if (count == 6) {
			price += 1000;
		}
		return price;
	}

	public void purchaseRules() {
		// 구매 규칙(안내메시지)
		// 6개가 SELECTED된 게 하나도 없을 때 "최소 1개 이상의 게임이 선택되어야 합니다."
		// SELECTED가 완료된 복권이 있고, 1 ~ 5 SELECTED 된 복권이 있을 때 "[B] 복권의 번호 입력이 3개 부족합니다."
		// 테스트를 위해 배열 하나 더 생성
	}

	public void autoNumber(JCheckBox[] checkBoxes) { // 랜덤 번호 생성, 자동/반자동 설정
		Set<UserSelectNum> testSet = new HashSet<>(); // 임시생성
		Set<Integer> set = new HashSet<>(); // 숫자배열
		Random random = new Random(); // 랜덤생성
		int count = 0;

		for (JCheckBox checkBox : checkBoxes) { // 수동 번호 배열
			if (checkBox.isSelected()) {
				count++;
				int test = Integer.parseInt(checkBox.getText());
				set.add(test);
				testSet.add(new UserSelectNum(test, false));
			}
		}

		while (true) { // 자동 번호 배열
			if (set.size() == 6) {
				break;
			}
			int ran = random.nextInt(45) + 1;
			set.add(ran);
			testSet.add(new UserSelectNum(ran, true));

			for (JCheckBox checkBox : checkBoxes) { // 자동 번호 체크박스 체크
				if (Integer.parseInt(checkBox.getText()) == ran) {
					checkBox.setSelected(true);
				}
			}
		}

		for (UserSelectNum qwe : testSet) { // 테스트용 출력
			System.out.println(qwe.getLotteryNum());
		}
	}

	public void resetMarking() { // 복권 번호 선택 초기화

		// 체크
		// 배열
		// index = select number
		// mouse acction = 배열 생성 , 수동
		// 이미지 add

		// 체크해제
		// 원소 remove
		// 이미지 remove
	}

	public static void main(String[] args) {
		new LottoBuyTest();
	}
}
