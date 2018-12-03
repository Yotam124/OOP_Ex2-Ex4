package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Element extends MyCoords implements GIS_element {
	
	private String MAC = "";
	private String SSID = "";
	private String AuthMode = "";
	private String FirstSeen = "";
	private String Channel = "";
	private String RSSI = "";
	private double CurrentLatitude;
	private double CurrentLongitude;
	private double AltitudeMeters;
	private String AccuracyMeters = "";
	private String Type = "";
	private MetaData metaData;
	

	public Element (String[] elements) {
		this.MAC = elements[0];
		this.SSID = elements[1];
		this.AuthMode = elements[2];
		this.FirstSeen = elements[3];
		this.Channel = elements[4];
		this.CurrentLatitude = Double.parseDouble(elements[5]);
		this.CurrentLongitude = Double.parseDouble(elements[6]);
		this.AltitudeMeters = Double.parseDouble(elements[7]);
		this.AccuracyMeters = elements[8];
		this.Type = elements[9];
		this.metaData = new MetaData(FirstSeen, CurrentLatitude, CurrentLongitude, AltitudeMeters);
	}
	@Override
	public Geom_element getGeom() {
		Point3D gps = new Point3D(this.CurrentLatitude, this.CurrentLongitude,this.AltitudeMeters);
		return gps;
	}

	@Override
	public Meta_data getData() {
		return this.metaData;
	}

	@Override
	public void translate(Point3D vec) {
		Point3D gps = new Point3D(this.CurrentLatitude,this.CurrentLongitude,this.AltitudeMeters);
		Point3D ansGps = add(gps, vec);
		this.CurrentLatitude = ansGps.x();
		this.CurrentLongitude = ansGps.y();
		this.AltitudeMeters = ansGps.z();
		
		
		

	}
	public String getMAC() {
		return MAC;
	}
	public void setMAC(String mAC) {
		MAC = mAC;
	}
	public String getSSID() {
		return SSID;
	}
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	public String getAuthMode() {
		return AuthMode;
	}
	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}
	public String getFirstSeen() {
		return FirstSeen;
	}
	public void setFirstSeen(String firstSeen) {
		FirstSeen = firstSeen;
	}
	public String getChannel() {
		return Channel;
	}
	public void setChannel(String channel) {
		Channel = channel;
	}
	public String getRSSI() {
		return RSSI;
	}
	public void setRSSI(String rSSI) {
		RSSI = rSSI;
	}
	public double getCurrentLatitude() {
		return CurrentLatitude;
	}
	public void setCurrentLatitude(double currentLatitude) {
		CurrentLatitude = currentLatitude;
	}
	public double getCurrentLongitude() {
		return CurrentLongitude;
	}
	public void setCurrentLongitude(double currentLongitude) {
		CurrentLongitude = currentLongitude;
	}
	public double getAltitudeMeters() {
		return AltitudeMeters;
	}
	public void setAltitudeMeters(double altitudeMeters) {
		AltitudeMeters = altitudeMeters;
	}
	public String getAccuracyMeters() {
		return AccuracyMeters;
	}
	public void setAccuracyMeters(String accuracyMeters) {
		AccuracyMeters = accuracyMeters;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public MetaData getMetaData() {
		return metaData;
	}
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

}
