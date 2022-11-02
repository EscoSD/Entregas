package com.esco.ut1.ej4_jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Doujinshi implements Serializable {
	private int code;
	private String name;
	private int pages;
	private double prize;

	public Doujinshi(int code, String name, int pages, double prize) {
		this.code = code;
		this.name = name;
		this.pages = pages;
		this.prize = prize;
	}

	public Doujinshi() {
	}

	@Override
	public String toString() {
		return code + ".- " + name + " \nNúmero de Páginas: " + pages + " \nPrecio: " + prize + "€ \n";
	}
}
