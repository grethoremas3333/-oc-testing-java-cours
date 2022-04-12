package com.openclassrooms.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

	private com.openclassrooms.testing.Calculator calculatorUnderTest;
	private static Instant startedAT;

	@BeforeEach
	public void initCalculator() {
		calculatorUnderTest = new com.openclassrooms.testing.Calculator();
		System.out.println("Appel avant chaque Test ==> Initialisation d'un nouveau Calculator!!!");
	}

	@AfterEach
	public void undefCalculator() {
		calculatorUnderTest = null;
		System.out.println("Appel après chaque Test ==> Désafecte la variable calculatorUnderTest pour empecher toute réutilisation!!!");
	}

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests ==> Stocke le moment où l'on a démarré le Test!!!");
		startedAT = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tous les tests ==> Va afficher la période ou durée entre le debut des Tests et la Fin des Tests!!!");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAT,endedAt).toMillisPart();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms",duration));
	}

	@Test
	public void testAddTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;

		// Act
		int somme = calculatorUnderTest.add(a, b);

		// Assert
		assertEquals(5, somme);
	}


	@Test
	public void testMultipyTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;

		// Act
		int produit = calculatorUnderTest.multiply(a, b);

		// Assert
		assertEquals(6, produit);
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = { 1, 2, 42, 1011, 5089 })
	public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
		// Arrange -- Tout est prêt !

		// Act -- Multiplier par zéro
		int actualResult = calculatorUnderTest.multiply(arg, 0);

		// Assert -- ça vaut toujours zéro !
		assertEquals(0, actualResult);
	}

	@ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
		// Arrange -- Tout est prêt !

		// Act
		int actualResult = calculatorUnderTest.add(arg1, arg2);

		// Assert
		assertEquals(expectResult, actualResult);
	}

	@Timeout(1)
	@Test
	public void longCalcul_shouldComputeInLessThan1Second() {
		// Arrange

		// Act
		calculatorUnderTest.longCalculation();

		// Assert
		// ...
	}


}
