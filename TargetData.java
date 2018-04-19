package com.example.a10580.pocpro;

import java.io.Serializable;

/**
 * 
 * It stores particular target device information.
 * 
 * @author Ramesh
 * 
 */
public class TargetData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id, name, imagePath, osType, deviceName, gender,licenseCode;
	public boolean targtStatus;

	public TargetData(String id, String name, String imagePath,
                      boolean targtStatus, String osType, String deviceName, String licenseCode) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.targtStatus = targtStatus;
		this.osType = osType;
		this.deviceName = deviceName;
		this.licenseCode = licenseCode;


	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}