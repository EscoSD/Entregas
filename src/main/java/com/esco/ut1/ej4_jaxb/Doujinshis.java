package com.esco.ut1.ej4_jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "doujinshis")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doujinshis {

	@XmlElement(name = "doujinshi")
	public List<Doujinshi> list;

	public Doujinshis() {

	}

	public Doujinshis(List<Doujinshi> list) {
		this.list = list;
	}
}
