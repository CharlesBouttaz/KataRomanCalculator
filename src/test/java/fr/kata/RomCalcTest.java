package fr.kata;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public final class RomCalcTest {

//    I : 1
//    V : 5
//    X : 10
//    L : 50
//    C : 100
//    D : 500
//    M : 1000

    RomCalc romCalc;

    @Before
    public void setUp() {
        romCalc = new RomCalc();
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
    }

    @Test
    public void should_insert_at_correct_position() {
        assertThat(romCalc.add("MVI", "CX")).isEqualTo("MCXVI");
    }

    @Test
    public void should_insert_at_correct_position_and_index_zero() {
        assertThat(romCalc.add("XVI", "C")).isEqualTo("CXVI");
    }

    @Test
    public void should_group_same_letters_() {
        assertThat(romCalc.add("XXII", "XVI")).isEqualTo("XXXVIII");
        assertThat(romCalc.add("XVI", "XXII")).isEqualTo("XXXVIII");
    }

    @Test
    public void should_transform_more_thant_tree_I_into_new_number() {
        assertThat(romCalc.add("XII", "II")).isEqualTo("XIV");
        assertThat(romCalc.add("II", "XII")).isEqualTo("XIV");
    }

    @Test
    public void should_transform_limit_case() {
        assertThat(romCalc.add("III", "III")).isEqualTo("VI");
        assertThat(romCalc.add("CLIII", "III")).isEqualTo("CLVI");
        //Assertions.assertThat(romCalc.add("CXXXI", "XXXIV")).isEqualTo("CLXV");
        assertThat(romCalc.add("CCCVI", "CXXII")).isEqualTo("CDXXVIII");
        assertThat(romCalc.add("CCCVI", "CCXXII")).isEqualTo("DXXVIII");
    }

    @Test
    public void should_merge_substractives() {
        assertThat(romCalc.add("IV", "I")).isEqualTo("V");
        assertThat(romCalc.add("IX", "I")).isEqualTo("X");
        assertThat(romCalc.add("XL", "X")).isEqualTo("L");
        assertThat(romCalc.add("XC", "X")).isEqualTo("C");
        assertThat(romCalc.add("CD", "C")).isEqualTo("D");
        assertThat(romCalc.add("CM", "C")).isEqualTo("M");
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
