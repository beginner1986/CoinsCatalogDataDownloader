import java.util.List;

import lombok.Data;

@Data
public class Coin {

	public Coin(List<String> attributes) {
		setName(attributes.get(0));

		String subAttributes = attributes.get(1);
		String[] parts = subAttributes.split("Kod: |Rok: |Œrednica: |Kszta³t: |Napis: |Nak³ad: ");
		setCode(parts[1]);
		setYear(parts[2]);
		setDiameter(parts[3]);
		setShape(parts[4]);
		setInscription(parts[5]);
		setCirculation(parts[6]);
		
		subAttributes = attributes.get(2);
		parts = subAttributes.split("Metal: |Próba: |Waga: |Stempel: ");
		setMetal(parts[0]);
		setFineness(parts[1]);
		setWeight(parts[2]);
		setWeight(parts[3]);
		setStamp(parts[4]);
		
		setBorder(attributes.get(3));
		setComment(attributes.get(4));
		setPhoto(attributes.get(5));
	}
	
	private String Name;
	private String Code;
	private String Year;
	private String Diameter;
	private String Shape;
	private String Inscription;
	private String Circulation;
	private String Metal;
	private String Fineness;
	private String Weight;
	private String Stamp;
	private String Border;
	private String Comment;
	private String Photo;
}
