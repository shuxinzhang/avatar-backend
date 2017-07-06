package katalyst;

import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;

public class SortModel {
	
	Avatar[] source;
	
	SortModel(Avatar[] source) {
		this.source = source;
	}
	public Avatar[] sortAvatar(String param) {
		Avatar[] list = source.clone();
		switch(param) {

			case "eyes": Arrays.sort(list, eyeComparator); return list;
			case "nose": Arrays.sort(list, noseComparator ); return list;
			case "mouth": Arrays.sort(list, mouthComparator); return list;
			default:return list;
		}
	}
	
	public static Comparator<Avatar> eyeComparator = new Comparator<Avatar>() {
		@Override
		public int compare(Avatar o1, Avatar o2) {
			return o1.eye - o2.eye;
		}
		
	};
	public static Comparator<Avatar> noseComparator = new Comparator<Avatar>() {
		@Override
		public int compare(Avatar o1, Avatar o2) {
			return o1.nose - o2.nose;
		}
	};
	public static Comparator<Avatar> mouthComparator = new Comparator<Avatar>() {
		@Override
		public int compare(Avatar o1, Avatar o2) {
			return o1.mouth - o2.mouth;
		}
	};
	
	public static void main(String[] args) {
		Avatar a1 = new Avatar(1,2,3,"");
		Avatar a2 = new Avatar(2,3,1,"");
		Avatar a3 = new Avatar(3,1,2,"");
		Avatar[] array = {a1,a2,a3};
		SortModel model = new SortModel(array);
	}
	
	
	public static void printArray(Object[] array) {
		for (int i = 0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	

}
