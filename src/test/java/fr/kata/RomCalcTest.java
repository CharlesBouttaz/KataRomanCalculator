package fr.kata;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;

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
    public void setUp(){
        romCalc = new RomCalc();
    }

    @Test
    public void should_add_one_and_one() {
        Assertions.assertThat(romCalc.add("I", "I")).isEqualTo("II");
    }

    @Test
    public void should_group_same_letters() {
        Assertions.assertThat(romCalc.add("XXII", "XI")).isEqualTo("XXXIII");
    }

    @Test
    public void should_insert_at_correct_position() {
        Assertions.assertThat(romCalc.add("MVI", "CX")).isEqualTo("MCXVI");
    }

    @Test
    public void should_insert_at_correct_position_and_index_zero() {
        Assertions.assertThat(romCalc.add("XVI", "C")).isEqualTo("CXVI");
    }

    @Test
    public void should_group_same_letters_() {
        Assertions.assertThat(romCalc.add("XXII", "XVI")).isEqualTo("XXXVIII");
        Assertions.assertThat(romCalc.add("XVI", "XXII")).isEqualTo("XXXVIII");
    }

    @Test
    public void should_transform_more_thant_tree_I_into_new_number() {
        Assertions.assertThat(romCalc.add("XII", "II")).isEqualTo("XIV");
        Assertions.assertThat(romCalc.add("II", "XII")).isEqualTo("XIV");
    }

    @Test
    public void should_transform_limit_case() {
        Assertions.assertThat(romCalc.add("III", "III")).isEqualTo("VI");
        Assertions.assertThat(romCalc.add("CLIII", "III")).isEqualTo("CLVI");
        //Assertions.assertThat(romCalc.add("CXXXI", "XXXIV")).isEqualTo("CLXV");
        Assertions.assertThat(romCalc.add("CCCVI", "CXXII")).isEqualTo("CDXXVIII");
        Assertions.assertThat(romCalc.add("CCCVI", "CCXXII")).isEqualTo("DXXVIII");
    }

    @Test
    public void should_add_substractive() {
        Assertions.assertThat(romCalc.add("IV", "VI")).isEqualTo("X");
//        Assertions.assertThat(romCalc.add("IV", "IV")).isEqualTo("VIII");
//        Assertions.assertThat(romCalc.add("XIV", "III")).isEqualTo("XVII");
//        Assertions.assertThat(romCalc.add("IX", "V")).isEqualTo("XIV");
//        Assertions.assertThat(romCalc.add("VM", "II")).isEqualTo("VIIM");
    }
}
