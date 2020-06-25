import java.util.List;

import lombok.Data;

@Data
public class Coin {

	private String name;
	private String code;
	private String year;
	private String diameter;
	private String shape;
	private String inscription;
	private String circulation;
	private String metal;
	private String fineness;
	private String weight;
	private String stamp;
	private String border;
	private String comment;
	private String photo;
	
	public Coin(List<String> attributes) {
		setName(attributes.get(0));

		String subAttributes = attributes.get(1);
		String[] parts = subAttributes.split("Kod: |Rok: |Średnica: |Kształt: |Napis: |Nakład: ");
		setCode(parts[1]);
		setYear(parts[2]);
		setDiameter(parts[3]);
		setShape(parts[4]);
		setInscription(parts[5]);
		setCirculation(parts[6]);
		
		subAttributes = attributes.get(2);
		parts = subAttributes.split("Metal: |Próba: |Waga: |Stempel: ");
		setMetal(parts[1]);
		setFineness(parts[2]);
		setWeight(parts[3]);
		setStamp(parts[4]);
		
		setBorder(attributes.get(3).split("Rant:")[1]);
		
		String comment = "";
		if(attributes.get(4).split("Komentarz:").length > 1)
			comment = attributes.get(4).split("Komentarz:")[1];
		setComment(comment);
		
		setPhoto(attributes.get(5));
	}

}
