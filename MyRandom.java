import java.util.Random;
import java.math.BigInteger;

public class MyRandom extends Random {
	private byte[] S = new byte[256];
	private int i = 0;
	private int j = 0;

	public MyRandom(BigInteger key){
		byte[] keyBytes = key.toByteArray();
		byte[] ks = new byte[256];
		for(int z = 0; z< 256; z++){
			S[z] = (byte)z;
			ks[z] = keyBytes[z % keyBytes.length];
		}
		j =0;
		for(int z = 0; z<256; z++){
			j =(j + (int)(S[z] & 0xff) + (int)(ks[z] & 0xff))% 256;
			byte temp = S[z];
			S[z] = S[j];
			S[j] = temp;
		}
		j = 0;
	}

	public int next(int bits){
		i = (i + 1) % 256;
		j = (j + (int) (S[i] & 0xff))% 256;
		byte temp = S[i];
			S[i] = S[j];
			S[j] = temp;
		
		int ksb = S[((int)(S[i] & 0xff) + (int)(S[j] & 0xff)) %256];
		return ksb;

	}
	
}
