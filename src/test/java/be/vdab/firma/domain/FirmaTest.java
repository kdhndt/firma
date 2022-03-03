package be.vdab.firma.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class FirmaTest {
    //neem interface uit de juiste package
    private Validator validator;
    private Firma firma;

    @BeforeEach
    void beforeEach() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        firma = new Firma();
    }

    @Test
    void correctNummer() {
        firma.setOndernemingsNummer(426388541);
        assertThat(validator.validate(firma)).isEmpty();
    }

    @Test
    void foutiefNummer() {
        firma.setOndernemingsNummer(111222333);
        assertThat(validator.validate(firma)).isNotEmpty();
    }
}