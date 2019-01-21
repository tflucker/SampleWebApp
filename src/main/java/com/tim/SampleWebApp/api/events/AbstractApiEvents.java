package com.tim.SampleWebApp.api.events;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tim.SampleWebApp.common.CommonConstants;
import com.tim.SampleWebApp.error.Message;

public abstract class AbstractApiEvents {

	public static boolean containsErrors(List<Message> msgList) {
		long errorCount = msgList.stream()
				.filter(msg -> StringUtils.equalsIgnoreCase(msg.getType(), CommonConstants.ERROR)).count();
		if (errorCount == 0L) {
			return false;
		}
		return true;
	}

}
