package com.accenture.avs.device.util.comparator;

import java.util.Comparator;

import com.accenture.avs.device.config.model.VTP;

/**
 * 
 * @author singh.saurabh
 *
 */
public class VideoTypeBirateComparator implements Comparator<VTP> {

	@Override
	public int compare(VTP firstVtp, VTP secondVtp) {
		return secondVtp.getBitRate().compareTo(firstVtp.getBitRate());
	}

}
