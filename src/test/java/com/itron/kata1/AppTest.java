package com.itron.kata1;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class AppTest {


	@Test
	void whenEmptyStringReturnsZero() {
		int actual = App.add("");		
		assertThat(actual, is(0));
	}
	
	@Test
	void whenStringInputIsOneReturnsOne() {
		int actual = App.add("1");		
		assertThat(actual, is(1));
	}
	
	@Test
	void whenStringInputHasTwoNumbersReturnsSum() {
		int actual = App.add("1,2");		
		assertThat(actual, is(3));
	}
	
	@Test
	void whenStringInputHasUnknownNumbersReturnsSum() {
		int actual = App.add("1,2,3");		
		assertThat(actual, is(6));
	}
	
	@Test
	void whenStringInputHasNewLineSeparatorReturnsSum() {
		int actual = App.add("1,2\n3");		
		assertThat(actual, is(6));
	}
	
	@Test
	void whenStringInputHasCustomSpecialRegexDelimiterReturnsSum() {
		int actual = App.add("//[\n1[2");
		assertThat(actual, is(3));
	}
	@Test
	void whenStringInputHasCustomDelimiterReturnsSum() {
		int actual = App.add("//;\n1;2");
		assertThat(actual, is(3));
	}

	@Test
	void whenStringInputHasNegativeThrowsException() {
		IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> App.add("-1"));
		assertEquals("negatives not allowed: [-1]", expectedException.getMessage());
	}

	@Test
	void whenStringInputHasMultipleNegativesThrowsException() {
		IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> App.add("-1,-2"));
		assertEquals("negatives not allowed: [-1, -2]", expectedException.getMessage());
	}

	@Test
	void whenStringInputHasNumberGreaterThan1000IgnoreIt() {
		int actual = App.add("1001,2");
		assertThat(actual, is(2));
	}

	@Test
	void whenDelimiterHasAnyLengthShouldReturnSum() {
		int actual = App.add("//[***]\n1***2***3");
		assertThat(actual, is(6));
	}

}
