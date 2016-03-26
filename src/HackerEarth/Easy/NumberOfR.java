package Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author priya
 *         https://www.hackerearth.com/tracks/pledge-2015-easy/number-of-rs-1/
 *
 */
public class NumberOfR {

	public static void main(String args []) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String line = null;
		int count = Integer.parseInt(br.readLine());
		//System.out.println("count=" + count);
		String [] line= new String[count];
		for (int i=0; i < count; i++) 
		{
			line[i] = br.readLine().toUpperCase();		
		}
		for (int i=0; i < count; i++) 
		{
		char[] input = line[i].toCharArray();
		//System.out.println("Length =" + input.length);
		int forwardIndex = getForwardIndex(input);
		int backwardIndex = getBackwardIndex(input, forwardIndex);
		int rCount = convertAndCountR(input, forwardIndex, backwardIndex);
		System.out.println(rCount);		
		}
	}
	
	static int convertAndCountR(final char [] input, int startIndex, int endIndex) {
		//char [] output = Arrays.copyOf(input, input.length);
		int rcount = 0;
		for (int i=0; i <= input.length-1; i++)
			if('K' == input [i] && i>= startIndex && i<=endIndex) {
				//output[i] = 'R';
				rcount++;
			} else if ('R' == input[i]) {
				rcount++;
			}
				
		return rcount;
	}
	
	static int getForwardIndex (final char [] input) {
		int rcount = 0;
		int kcount = 0;
		int maxDiff = 0;
		int forwardIndex = 0;
		char r = 'R';
		for (int i = 0; i < input.length - 1; i++) {			
			if ('R' == input[i]) {
				rcount++;
			} else if ('K' == input[i]) {
				kcount++;
				for (int j = i + 1; j < input.length - 1; j++) {
					int tempRCount = 0;
					int tempKCount = 0;
					if ('R' == input[j]) {
						tempKCount++;
					} else if ('K' == input[j]) {
						tempRCount++;
					}
					if (rcount+tempRCount > kcount + tempKCount) {
						int diff = (rcount+tempRCount) - (kcount + tempKCount);
						//System.out.println("diff =" + diff);
						if (diff > maxDiff) {
							maxDiff = diff;
							forwardIndex = i;
						}
					}
				}
				//System.out.println("maxDiff =" + maxDiff + "forwardIndex =" + forwardIndex);
			}
		}
		//System.out.println("ForwardIndex = " + forwardIndex);
		
		return forwardIndex;
		
	}
	
	static int getBackwardIndex (final char [] input, final int forwardIndex) {
		int rcount = 0;
		int kcount = 0;
		int maxDiff = 0;
		int backwardIndex = input.length-1;
		for (int i = input.length-1; i >=forwardIndex; i--) {			
			if ('R' == input[i]) {
				rcount++;
			} else if ('K' == input[i]) {
				kcount++;
				for (int j = i-1; j >= forwardIndex; j--) {
					int tempRCount = 0;
					int tempKCount = 0;
					if ('R' == input[j]) {
						tempKCount++;
					} else if ('K' == input[j]) {
						tempRCount++;
					}
					if (rcount+tempRCount > kcount + tempKCount) {
						int diff = (rcount+tempRCount) - (kcount + tempKCount);
						//System.out.println("diff =" + diff);
						if (diff > maxDiff) {
							maxDiff = diff;
							backwardIndex = i;
						}
					}
				}
				//System.out.println("maxDiff =" + maxDiff + "BackwardIndex =" + backwardIndex);
			}
		}
		//System.out.println("BackwardIndex = " + backwardIndex);
				
		return backwardIndex;
	}
}
