package az.maqa.network.pg.socket;

public class ValidateIp {

	public static void main(String[] args) {
		String ipAddress = "192.168.1.101";
		boolean isValid = validateIpAddress(ipAddress);

		if (isValid) {
			System.out.println(ipAddress + " is valid.");
		} else {
			System.out.println(ipAddress + " isn't valid.");
		}

	}

	public static boolean validateIpAddress(String ipAddress) {
		String[] ipNumbers = ipAddress.split("\\.");
		if (ipNumbers.length != 4) {
			return false;
		}

		for (String str : ipNumbers) {
			System.out.println(str);
			int i = Integer.parseInt(str);
			if (i < 0 || i > 256) {
				return false;
			}
		}

		return true;
	}

}
