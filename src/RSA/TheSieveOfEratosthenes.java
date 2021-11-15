package RSA;
import java.util.ArrayList;

public class TheSieveOfEratosthenes { //Простое число методом решета эратосфена
		
	int max = 10000; int min = 500; //Границы для генерации простого числа
	ArrayList <Integer> Numbers = new ArrayList<>(); //Список всех чисел
	ArrayList <Integer> SimpleNumbers = new ArrayList<>(); //Список всех простых список
	ArrayList <Boolean> NumbersBoolean = new ArrayList<>(); 
	
	public void GenerateSimpleNumber () {
		
		int quantity = 0; //Количество самих чисел в массиве
		Numbers.clear();
		NumbersBoolean.clear();
		SimpleNumbers.clear();
		
		for (int i = 10; i > 0; i --) quantity = (int) (Math.random() * ( (max - min) + 1 )) + min; //10 раз генерируем рандомное число, увеличиваем случайность
		for (int i = 0; i <= quantity; i ++) { //Заполняем массив до n числа
			
			NumbersBoolean.add(true);
			Numbers.add(i);
			
		}
		
		
		for (int i = 2; i*i <= Numbers.get(Numbers.size() - 1); i ++ ) { //Цикл отсеивания делящихся чисел
			
			if (NumbersBoolean.get(i)) {
				
				for (int j = i*i; j <= Numbers.get(Numbers.size() - 1); j ++) {
					
					if ( Numbers.get(j) % i == 0 ) { NumbersBoolean.set(j, false); } //Если число делится нацело, то в массиве устанавливаем false
					
				}
				
			}
			
		}
		
		
		for (int i = 0; i < Numbers.size(); i ++) { //Создаем массив только из простых чисел
			
			if(NumbersBoolean.get(i)) SimpleNumbers.add(Numbers.get(i));
			
		}
		
	}
	
	public int ReturnSize() {return SimpleNumbers.size();} //Возвращаем размер массива простых чисел
	
	public int ReturnSimpleNumber(int position) { //Возвращаем само простое число по позиции в массиве
		
		return SimpleNumbers.get(position);
		
		
	}
	
}
