package com.intland.eurocup.service.date;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * Date Service to get current DateTime. 
 *
 */
@Service
public class DateService {
	public DateTime getNow() {
		return DateTime.now();
	}
}
