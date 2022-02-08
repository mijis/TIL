package day0124;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Homework0124 {

	private Map<String, Integer> map;//��ü�� ������ �� �� ���� �����ǵ��� �ν��Ͻ�ȭ
	
	public String overCharge(String transit, int distance) {
		map = new HashMap<String,Integer>(); //��ü�� ������ �� �ѹ��� �����ǵ��� �����ڿ� ���� �� �ڵ�
		map.put("��������", 800);
		map.put("����", 1250);
		map.put("����ö", 1300);

		
		String result = "���߱����� �ƴմϴ�";
		if (map.containsKey(transit)) {
			int fee = map.get(transit);
			int overFee = ((distance-10)/5)*100+100;
			if (distance <= 10) {
				overFee = 0;
			}
			result= String.format
					("�Է� �������: %s\t �̵��Ÿ�: %dkm\t �⺻���: %d\t �ʰ����: %d��\t �Ѵ�20�ϱ��ر����: %d", 
					transit,distance,fee,overFee,((fee+overFee))*20);
		} 
		return result;
	}
	
	
	public void useOverCharge() {
		String[] transArr = {"����","����ö","�ý�","������"};
		List<String> list = new ArrayList<String>();
		Random r = new Random();
		int days = r.nextInt(3)+21;
		for (int i = 1; i < days; i++) {
			list.add(overCharge(transArr[r.nextInt(4)], r.nextInt(30)+1));
		}
		
		for(String s : list) {
			System.out.println(s);
		}
	}

	
	public static void main(String[] args) {
		Homework0124 hm= new Homework0124();
		//1
//		System.out.println(hm.overCharge("����", 12));
		System.out.println("------------------------------------------------------------------");
		//2
		hm.useOverCharge();
		
		System.out.println("----------------------------------------------");

	}

}
