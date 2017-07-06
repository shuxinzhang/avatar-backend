package katalyst;

import java.awt.Color;

import org.json.JSONObject;

public class Avatar {
	public final int eye;
	public final int nose;
	public final int mouth;
	public final String color;
	
	private String colorToHex(Color c) {
		return Integer.toHexString(c.getRGB()).substring(2);
	}
	
	public JSONObject generateJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("eye", "eyes"+eye);
		jsonObject.put("nose", "nose"+nose);
		jsonObject.put("mouth", "mouth"+mouth);
		jsonObject.put("color",color);
		return jsonObject;
	}
	
	public Avatar(int eyeNum, int noseNum, int mouthNum, String color) {
		this.eye = eyeNum;
		this.nose = noseNum;
		this.mouth = mouthNum;
		this.color = color;
	}
	
	public String toString() {
		return "[" + eye + "," + nose + "," + mouth + "," + color + "];";
	}

}
