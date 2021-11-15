package RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		String InputText; //Входная строка
		TheSieveOfEratosthenes number = new TheSieveOfEratosthenes();
		
		Scanner scan = new Scanner(System.in); //Считываем строку
		StringBuilder mid = new StringBuilder();
		mid.append(scan.nextLine());
		InputText = mid.toString();
		scan.close();
		
		number.GenerateSimpleNumber(); //Генерируем массив простых чисел
		BigInteger BigP = new BigInteger(Integer.toString ( number.ReturnSimpleNumber( number.ReturnSize() - 1 )) ); //Возвращаем последнее число из массива простых чисел
		number.GenerateSimpleNumber();
		BigInteger BigQ = new BigInteger(Integer.toString ( number.ReturnSimpleNumber( number.ReturnSize() - 1 )) );
		BigInteger Modulus = new BigInteger (BigP.multiply(BigQ).toString());
		BigInteger BigFi = new BigInteger ( BigP.subtract(BigInteger.ONE).multiply(BigQ.subtract(BigInteger.ONE)).toString() ); //Получаем fi как (p-1)*(q-1)
		
		System.out.print("P = " + BigP + "\n" + 
		                 "Q = " + BigQ + "\n" + 
				         "M = " + Modulus + "\n" + 
		                 "Fi = " + BigFi + "\n");
		
		number.GenerateSimpleNumber();
		BigInteger BigE = new BigInteger ( Integer.toString ( number.ReturnSimpleNumber( number.ReturnSize() - 1 )) ) ;
		int i = number.ReturnSize() - 1;
		
		while (BigFi.mod(BigE).compareTo( BigInteger.ZERO) == 0 ) { //Считаем E до тех пор, пока оно не станет взаимнопростым с числом Fi
			
			i --;
			BigInteger Middle = new BigInteger ( Integer.toString ( number.ReturnSimpleNumber( i )) );
			BigE = Middle;
			
		}
		
		System.out.print("E = " + BigE + "\n");
		
		BigInteger BigD = new BigInteger( BigE.modInverse(BigFi).toString() ); //Находим d такое, чтобы  (d * e) mod fi = 1
		
		System.out.print("D = " + BigD + "\n");
		
		//Начало шифрации
		ArrayList<Integer> Bytes = new ArrayList<>();
		
		for (int k = 0; k < InputText.length(); k++) Bytes.add( (int) InputText.charAt(k) ); //Вносим считанную строку в список в качесте Ascii кода
		
		for(int k = 0; k < Bytes.size(); k ++) { //Возводим в степень e и находим остаток от деления от модуля (шифруем текст)
			
			BigInteger Middle = new BigInteger( Integer.toString(Bytes.get(k)) );
			Middle = Middle.modPow(BigE, Modulus);
			Bytes.set(k, Middle.intValueExact());
			
		}
		
		System.out.print("Сообщение: " + InputText + "\n");
		System.out.print("Шифрованное сообщение: " + Bytes + "\n");
		
		//Начало дешифрации
		for(int k = 0; k < Bytes.size(); k ++) { //Возводим в степень d и находим остаток (дешифрируем текст)
			
			BigInteger Middle = new BigInteger( Integer.toString(Bytes.get(k)) );
			Middle = Middle.modPow(BigD, Modulus);
			Bytes.set(k, Middle.intValueExact());
			
		}
		
		StringBuilder DecryptedText = new StringBuilder();
		
		for (int k = 0; k < Bytes.size(); k++) { //Приводим от int к char 
			
			char character = (char) Bytes.get(k).intValue();
			DecryptedText.append(character);
			
			
			}
		
		System.out.print("Дешифрованное сообщение: " + DecryptedText + "\n");

	}

}
