package com.accenture.avs.device.util.comparator;

import java.util.Comparator;

import com.accenture.avs.device.entity.UserLimits;
import com.accenture.avs.device.enums.DeviceProfiles;

/**
 * 
 * @author singh.saurabh
 *
 */
public class UserLimitsComparator implements Comparator<UserLimits> {

	@Override
	public int compare(UserLimits firstUserLimit, UserLimits secondUserLimit) {
		return DeviceProfiles.fromContentQuality(secondUserLimit.getContentQuality()).getPriority()
				.compareTo(DeviceProfiles.fromContentQuality(firstUserLimit.getContentQuality())
						.getPriority());

	}

}
