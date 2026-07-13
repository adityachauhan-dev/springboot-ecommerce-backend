package in.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressResponse {

	private int id;
	private String fullName;
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	private String pincode;
	private int userId;
	
}
