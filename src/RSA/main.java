package RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		String InputText; //������� ������
		TheSieveOfEratosthenes number = new TheSieveOfEratosthenes();
		
		Scanner scan = new Scanner(System.in); //��������� ������
		StringBuilder mid = new StringBuilder();
		mid.append(scan.nextLine());
		InputText = mid.toString();
		scan.close();
		
		number.GenerateSimpleNumber(); //���������� ������ ������� �����
		BigInteger BigP = new BigInteger(Integer.toString ( number.ReturnSimpleNumber( number.ReturnSize() - 1 )) ); //���������� ��������� ����� �� ������� ������� �����
		number.GenerateSimpleNumber();
		BigInteger BigQ = new BigInteger(Integer.toString ( number.ReturnSimpleNumber( number.ReturnSize() - 1 )) );
		BigInteger Modulus = new BigInteger (BigP.multiply(BigQ).toString());
		BigInteger BigFi = new BigInteger ( BigP.subtract(BigInteger.ONE).multiply(BigQ.subtract(BigInteger.ONE)).toString() ); //�������� fi ��� (p-1)*(q-1)
		
		System.out.print("P = " + BigP + "\n" + 
		                 "Q = " + BigQ + "\n" + 
				         "M = " + Modulus + "\n" + 
		                 "Fi = " + BigFi + "\n");
		
		number.GenerateSimpleNumber();
		BigInteger BigE = new BigInteger ( Integer.toString ( number.ReturnSimpleNumber( number.ReturnSize() - 1 )) ) ;
		int i = number.ReturnSize() - 1;
		
		while (BigFi.mod(BigE).compareTo( BigInteger.ZERO) == 0 ) { //������� E �� ��� ���, ���� ��� �� ������ �������������� � ������ Fi
			
			i --;
			BigInteger Middle = new BigInteger ( Integer.toString ( number.ReturnSimpleNumber( i )) );
			BigE = Middle;
			
		}
		
		System.out.print("E = " + BigE + "\n");
		
		BigInteger BigD = new BigInteger( BigE.modInverse(BigFi).toString() ); //������� d �����, �����  (d * e) mod fi = 1
		
		System.out.print("D = " + BigD + "\n");
		
		//������ ��������
		ArrayList<Integer> Bytes = new ArrayList<>();
		
		for (int k = 0; k < InputText.length(); k++) Bytes.add( (int) InputText.charAt(k) ); //������ ��������� ������ � ������ � ������� Ascii ����
		
		for(int k = 0; k < Bytes.size(); k ++) { //�������� � ������� e � ������� ������� �� ������� �� ������ (������� �����)
			
			BigInteger Middle = new BigInteger( Integer.toString(Bytes.get(k)) );
			Middle = Middle.modPow(BigE, Modulus);
			Bytes.set(k, Middle.intValueExact());
			
		}
		
		System.out.print("���������: " + InputText + "\n");
		System.out.print("����������� ���������: " + Bytes + "\n");
		
		//������ ����������
		for(int k = 0; k < Bytes.size(); k ++) { //�������� � ������� d � ������� ������� (����������� �����)
			
			BigInteger Middle = new BigInteger( Integer.toString(Bytes.get(k)) );
			Middle = Middle.modPow(BigD, Modulus);
			Bytes.set(k, Middle.intValueExact());
			
		}
		
		StringBuilder DecryptedText = new StringBuilder();
		
		for (int k = 0; k < Bytes.size(); k++) { //�������� �� int � char 
			
			char character = (char) Bytes.get(k).intValue();
			DecryptedText.append(character);
			
			
			}
		
		System.out.print("������������� ���������: " + DecryptedText + "\n");

	}

}
