package com.mohamad.CieserCipher;

public class Decript {

	public char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public String CeiserCipher(int KEY, String encreptedText) {
		encreptedText = encreptedText.toUpperCase();
		char[] ptChar = encreptedText.toCharArray();
		String out = "";

		for (char c : ptChar) {
			int index = GetIndexOfChar(c);
			if (index == -1)
				return "Error Character not in Alphabet!..\n";
			int ind = (index - KEY) % 26;
			if(ind < 0){
				ind += 26;
			}
			char cip_c = alphabet[ind];
			out += cip_c;
		}

		return out;
	}

	private int GetIndexOfChar(char c) {
		for (int i = 0; i < 26; i++) {
			if (alphabet[i] == c) {
				return i;
			}
		}
		return -1;
	}
}
