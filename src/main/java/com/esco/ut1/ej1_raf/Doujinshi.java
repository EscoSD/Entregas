package com.esco.ut1.ej1_raf;

// Doujinshi: Dícese de mangas autopublicados.
public class Doujinshi {
	private int code;        // 4B
	private String name;        // 32+2B
	private int pages;        // 4B
	private double prize;    // 8B
	public static final int DOUJIN_SIZE = 50;	//50B

	public Doujinshi(int code, String name, int pages, double prize) {
		this.code = code;
		this.name = name;
		this.pages = pages;
		this.prize = prize;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getPages() {
		return pages;
	}

	public double getPrize() {
		return prize;
	}

	@Override
	public String toString() {
		return code + ".- " + name + " \nNúmero de Páginas: " + pages + " \nPrecio: " + prize + "€ \n";
	}
}
