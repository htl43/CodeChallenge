package codesignal.main;

import java.util.List;

import codesignal.setsOfFactors.SetsOfFactors;

public class Main {

	public static void main(String[] args) {
		
		SetsOfFactors setFactors = new SetsOfFactors();
		List result = setFactors.setsOfFactors(32);
		setFactors.setsOfFactors(297);
		
	}

}
