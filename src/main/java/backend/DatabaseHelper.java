package backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;


public class DatabaseHelper {
	Random random = new Random();
	private String[] colors = {
			"DAD7CD",
			"A3B18A",
			"6D9F71",
			"337357",
			"3A5A40"
	};
	
	private Integer[] eyes = {1,2,3,4,5,6,7,9,10};
	private Integer[] noses = {2,3,4,5,6,7,8,9};
	private Integer[] mouths = {1,10,11,3,5,6,7,9};
	
	public Object randomPickFromArray(Object[] array) {
		int randIndex = random.nextInt(array.length);
		return array[randIndex];
	}
	
	public Avatar generateRandomAvatar() {
		int eye = (int) randomPickFromArray(eyes);
		int nose = (int) randomPickFromArray(noses);
		int mouth = (int) randomPickFromArray(mouths);
		String s = (String) randomPickFromArray(colors);
		return new Avatar(eye,nose,mouth,s);
	}
	
	public Avatar[] generateData() {
		Avatar[] avatars = new Avatar[100];
		for (int i = 0; i < 100; i ++) {
			avatars[i] = generateRandomAvatar();
			System.out.println(avatars[i]);
		}
		return avatars;
		
	}
	
	public static void main(String[] args) {
		DatabaseHelper dh = new DatabaseHelper();
		String text = ""; 
		for (int i = 0;i<100; i++) {
			text = text + dh.generateRandomAvatar().toString();
		}
		try {
			Files.write(Paths.get("./src/main/resources/AvatarData.txt"), text.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Avatar[] readData(String text) {
		String[] textavatar = text.split(";");
		Avatar[] avatars = new Avatar[100];
		for (int i = 0; i < 100; i++) {
			avatars[i] = readSingleData(textavatar[i]);
		}
		return avatars;
	}

	public Avatar readSingleData(String avatarStr) {
		
		avatarStr = avatarStr.replaceAll("\\[", "");
		avatarStr = avatarStr.replaceAll("\\]", "");
		String[] data = avatarStr.split(",");
		if (data.length >3) {
			int eye = Integer.parseInt(data[0]);
			int nose = Integer.parseInt(data[1]);
			int mouth = Integer.parseInt(data[2]);
			String color = data[3];
			return new Avatar(eye,nose,mouth,color);
		} else return null;
	}
	

}
