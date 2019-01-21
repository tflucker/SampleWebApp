package com.tim.SampleWebApp.api.validators.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.tim.SampleWebApp.error.Message;

public class ValidatorImpl implements Validator<Object, List<Message>> {

	private Map<String, Validator<Object, Message>> validators;

	@Override
	public List<Message> validate(Object toValidate) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(toValidate);
		List<Message> messageList = new ArrayList<>();
		for (Field field : ArrayUtils.addAll(beanWrapper.getWrappedClass().getDeclaredFields(),
				((Class<?>) beanWrapper.getWrappedClass().getGenericSuperclass()).getDeclaredFields())) {
			if (validators.containsKey(field.getName())) {
				Message msg = validators.get(field.getName()).validate(beanWrapper.getPropertyValue(field.getName()));
				if (msg != null && msg.getCode() != 0) {
					messageList.add(msg);
				}
			}
		}
		return messageList;
	}

	public Map<String, Validator<Object, Message>> getValidators() {
		return validators;
	}

	public void setValidators(Map<String, Validator<Object, Message>> validators) {
		this.validators = validators;
	}

}
