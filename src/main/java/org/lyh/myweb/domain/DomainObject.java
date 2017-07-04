/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年6月6日
 */
package org.lyh.myweb.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DomainObject")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class DomainObject {

	@XmlElement(name = "ID")
	String Id;

	@XmlElementWrapper(name="VALUES")
	@XmlElement(name = "VALUE")
	List<String> values;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public void addValue(String value) {
		if (this.values == null) {
			this.values = new ArrayList<String>();
		}
		this.values.add(value);
	}
}
