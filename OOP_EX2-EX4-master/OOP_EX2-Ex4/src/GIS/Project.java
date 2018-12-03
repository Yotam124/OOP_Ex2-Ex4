package GIS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class Project {
	public Layer layer;
	
	public Project (Layer layer) {
		this.layer=layer;
	}
	
	public void project2csv() throws IOException {
		
		
		Iterator<Element> iter = this.layer.iterator();
		

			FileWriter writer = new FileWriter(this.layer.getName()+".csv");
			writer.append("MAC");
			writer.append(',');
			writer.append("SSID");
			writer.append(',');
			writer.append("AuthMode");
			writer.append(',');
			writer.append("FirstSeen");
			writer.append(',');
			writer.append("Channel");
			writer.append(',');
			writer.append("RSSI");
			writer.append(',');
			writer.append("CurrentLatitude");
			writer.append(',');
			writer.append("CurrentLongitude");
			writer.append(',');
			writer.append("AltitudeMeters");
			writer.append(',');
			writer.append("AccuracyMeters");
			writer.append(',');
			writer.append("Type");
			writer.append("\n");
			
			
			while (iter.hasNext()) {
				Element e = iter.next();
				writer.append(e.getMAC());
				writer.append(',');
				writer.append(e.getSSID());
				writer.append(',');
				writer.append(e.getAuthMode());
				writer.append(',');
				writer.append(e.getFirstSeen());
				writer.append(',');
				writer.append(e.getChannel());
				writer.append(',');
				writer.append(e.getRSSI());
				writer.append(',');
				writer.append(e.getCurrentLatitude()+"");
				writer.append(',');
				writer.append(e.getCurrentLongitude()+"");
				writer.append(',');
				writer.append(e.getAltitudeMeters()+"");
				writer.append(',');
				writer.append(e.getAccuracyMeters());
				writer.append(',');
				writer.append(e.getType());
				writer.append("\n");
			}
			writer.flush();
			writer.close();
				}

}
