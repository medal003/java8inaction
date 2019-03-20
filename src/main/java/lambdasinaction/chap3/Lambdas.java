package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambdas {
	public static void main(String ...args){
		//1. lambda表达式有个限制，那就是只能引用 final 或 final 局部变
		/*List<Integer> primes = Arrays.asList(new Integer[]{2, 3,5,7});
		int factor = 2;
		primes.forEach(element -> { factor++; });*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 2.lambda简单例子，开启多线程
		Runnable r = () -> System.out.println("Hello!");
		r.run();

		// 原始例子
		Runnable rold = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello!");
			}
		};
		rold.run();
		// 隐藏匿名类
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Before Java8, too much code for too little to do");
			}
		}).start();

		new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 3.lamdba表达式进行筛选
		// Filtering with lambdas
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]	--获取绿苹果
		List<Apple> greenApples = filter(inventory,  a -> "green".equals(a.getColor()));
		System.out.println(greenApples);


		// 自定义比较器，可以明确指定，也可以简化
		Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		// [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
		inventory.sort(c);
		System.out.println(inventory);
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}   

	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}

	// 函数式接口
	@FunctionalInterface  // 注解仅仅是为了保证当前接口只有一个函数，否则会报错
	interface ApplePredicate{
		public boolean test(Apple a);
		//public boolean test2(Apple a);
	}
}