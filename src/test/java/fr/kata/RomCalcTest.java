package fr.kata;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public final class RomCalcTest {

    private RomanCalculator romCalc;

    @Before
    public void setUp() {
        romCalc = new RomanCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_for_incorrect_number() {
        assertThat(romCalc.add("OWI", "III")).isEqualTo("VI");
    }

    @Test
    public void should_add_one_and_one() {
        assertThat(romCalc.add("I", "I")).isEqualTo("II");
    }

    @Test
    public void should_group_same_letters() {
        assertThat(romCalc.add("XXII", "XI")).isEqualTo("XXXIII");
        assertThat(romCalc.add("XXII", "XVI")).isEqualTo("XXXVIII");
        assertThat(romCalc.add("XVI", "XXII")).isEqualTo("XXXVIII");
    }

    @Test
    public void should_regroup_romans_in_correct_order() {
        assertThat(romCalc.add("MVI", "CX")).isEqualTo("MCXVI");
    }

    @Test
    public void should_regroup_romans_in_correct_order_even_first_one() {
        assertThat(romCalc.add("XVI", "C")).isEqualTo("CXVI");
    }

    @Test
    public void should_transform_four_of_a_kind_into_substractive() {
        assertThat(romCalc.add("II", "II")).isEqualTo("IV");
        assertThat(romCalc.add("VIII", "I")).isEqualTo("IX");
        assertThat(romCalc.add("XXX", "X")).isEqualTo("XL");
        assertThat(romCalc.add("LXX", "XX")).isEqualTo("XC");
        assertThat(romCalc.add("C", "CCC")).isEqualTo("CD");
        assertThat(romCalc.add("DC", "CCC")).isEqualTo("CM");
    }

    @Test
    public void should_merge_substractives_plus_one() {
        assertThat(romCalc.add("IV", "I")).isEqualTo("V");
        assertThat(romCalc.add("IX", "I")).isEqualTo("X");
        assertThat(romCalc.add("XL", "X")).isEqualTo("L");
        assertThat(romCalc.add("XC", "X")).isEqualTo("C");
        assertThat(romCalc.add("CD", "C")).isEqualTo("D");
        assertThat(romCalc.add("CM", "C")).isEqualTo("M");
    }

    @Test
    public void should_transform_limit_case() {
        assertThat(romCalc.add("III", "III")).isEqualTo("VI");
        assertThat(romCalc.add("CLIII", "III")).isEqualTo("CLVI");
        assertThat(romCalc.add("CCCVI", "CXXII")).isEqualTo("CDXXVIII");
        assertThat(romCalc.add("CCCVI", "CCXXII")).isEqualTo("DXXVIII");
    }

    @Test
    public void should_add_substractive() {
        assertThat(romCalc.add("IV", "VI")).isEqualTo("X");
        assertThat(romCalc.add("IV", "IV")).isEqualTo("VIII");
        assertThat(romCalc.add("XIV", "III")).isEqualTo("XVII");
        assertThat(romCalc.add("IX", "V")).isEqualTo("XIV");

        assertThat(romCalc.add("XC", "XC")).isEqualTo("CLXXX");
        assertThat(romCalc.add("CM", "MMM")).isEqualTo("MMMCM");

        assertThat(romCalc.add("CM", "L")).isEqualTo("CML");
    }

    @Test
    public void should_add_even_if_first_number_is_inferior() {
        assertThat(romCalc.add("XC", "MMM")).isEqualTo("MMMXC");
        assertThat(romCalc.add("MMM", "CM")).isEqualTo("MMMCM");
    }

}
