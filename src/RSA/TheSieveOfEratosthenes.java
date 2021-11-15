package RSA;
import java.util.ArrayList;

public class TheSieveOfEratosthenes { //������� ����� ������� ������ ����������
		
	int max = 10000; int min = 500; //������� ��� ��������� �������� �����
	ArrayList <Integer> Numbers = new ArrayList<>(); //������ ���� �����
	ArrayList <Integer> SimpleNumbers = new ArrayList<>(); //������ ���� ������� ������
	ArrayList <Boolean> NumbersBoolean = new ArrayList<>(); 
	
	public void GenerateSimpleNumber () {
		
		int quantity = 0; //���������� ����� ����� � �������
		Numbers.clear();
		NumbersBoolean.clear();
		SimpleNumbers.clear();
		
		for (int i = 10; i > 0; i --) quantity = (int) (Math.random() * ( (max - min) + 1 )) + min; //10 ��� ���������� ��������� �����, ����������� �����������
		for (int i = 0; i <= quantity; i ++) { //��������� ������ �� n �����
			
			NumbersBoolean.add(true);
			Numbers.add(i);
			
		}
		
		
		for (int i = 2; i*i <= Numbers.get(Numbers.size() - 1); i ++ ) { //���� ���������� ��������� �����
			
			if (NumbersBoolean.get(i)) {
				
				for (int j = i*i; j <= Numbers.get(Numbers.size() - 1); j ++) {
					
					if ( Numbers.get(j) % i == 0 ) { NumbersBoolean.set(j, false); } //���� ����� ������� ������, �� � ������� ������������� false
					
				}
				
			}
			
		}
		
		
		for (int i = 0; i < Numbers.size(); i ++) { //������� ������ ������ �� ������� �����
			
			if(NumbersBoolean.get(i)) SimpleNumbers.add(Numbers.get(i));
			
		}
		
	}
	
	public int ReturnSize() {return SimpleNumbers.size();} //���������� ������ ������� ������� �����
	
	public int ReturnSimpleNumber(int position) { //���������� ���� ������� ����� �� ������� � �������
		
		return SimpleNumbers.get(position);
		
		
	}
	
}
