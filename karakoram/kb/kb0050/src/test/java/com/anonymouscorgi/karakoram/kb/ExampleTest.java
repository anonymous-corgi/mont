package com.anonymouscorgi.karakoram.kb;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ExampleTest {

    @Test
    public void isEven_with1_returnsFalse() {
        Example example = new Example();

        boolean isEven = example.isEven(1);

        assertThat(isEven).isFalse();
    }

    @Test
    public void isEven_with2_returnsTrue() {
        Example example = new Example();

        boolean isEven = example.isEven(2);

        assertThat(isEven).isTrue();
    }
}